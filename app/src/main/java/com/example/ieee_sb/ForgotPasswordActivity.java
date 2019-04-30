package com.example.ieee_sb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText email;
    private Button reset;
    private TextView register;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        email = findViewById(R.id.forgot_email);
        reset = findViewById(R.id.forgot_btn_reset);
        register = findViewById(R.id.forgot_register);

        firebaseAuth = FirebaseAuth.getInstance();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_email = email.getText().toString().trim();

                if(user_email.isEmpty()){
                    Toast.makeText(ForgotPasswordActivity.this,"Please enter your registered email",Toast.LENGTH_SHORT).show();
                }
                else{
                    firebaseAuth.sendPasswordResetEmail(user_email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ForgotPasswordActivity.this,"Reset Link Sent",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(ForgotPasswordActivity.this,SignInActivity.class));
                            }
                            else{
                                Toast.makeText(ForgotPasswordActivity.this,"Error sending Password Link",Toast.LENGTH_SHORT);
                            }
                        }
                    });
                }
//                startActivity(new Intent(ForgotPasswordActivity.this,SignInActivity.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotPasswordActivity.this, RegistrationMethodActivity.class));
            }
        });
    }
}
