package com.example.ieee_sb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class EmailActivity extends AppCompatActivity {

    private EditText email,password;
    private Button next;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_email);

        next = findViewById(R.id.email_next);
        email = findViewById(R.id.email_edit_email);
        password = findViewById(R.id.email_edit_password);

        Log.v("Instanceup","Hello");
//        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();
        Log.v("Instance",""+firebaseAuth);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getCurrentFocus()!=null) {
                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }

                if(validate()) {

                    String user_email = email.getText().toString().trim();
                    String user_password = password.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(EmailActivity.this, "Regitration Done", Toast.LENGTH_SHORT).show();
                                FirebaseAuth.getInstance().signOut();
                                startActivity(new Intent(EmailActivity.this, SignInActivity.class));
                            }
                            else{
                                Toast.makeText(EmailActivity.this, "Regitration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private boolean validate(){
        boolean result = false;
        if(!(email.getText().toString().trim().isEmpty()&&password.getText().toString().trim().isEmpty())){
            result=true;
        }
        else{
            Toast.makeText(this,"Please Enter All the details",Toast.LENGTH_SHORT).show();
        }
        return result;
    }
}
