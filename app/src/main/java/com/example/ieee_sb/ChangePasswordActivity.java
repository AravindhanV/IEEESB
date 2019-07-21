package com.example.ieee_sb;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordActivity extends AppCompatActivity {
    private TextView email,current,changed,confirm;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        email = findViewById(R.id.change_email);
        current = findViewById(R.id.change_current);
        changed = findViewById(R.id.change_new);
        confirm = findViewById(R.id.change_new_confirm);
        submit = findViewById(R.id.change_btn_change);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
    }

    public void validate(){
        String emailt = email.getText().toString().trim().toLowerCase();
        String currentt = current.getText().toString().trim();
        String newp = changed.getText().toString().trim();
        final String confirmp = confirm.getText().toString().trim();

        if(!(emailt.isEmpty()||currentt.isEmpty()||newp.isEmpty()||confirmp.isEmpty())){
            if(newp.equals(confirmp)){
                final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                AuthCredential credential = EmailAuthProvider.getCredential(emailt,currentt);
                firebaseUser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            firebaseUser.updatePassword(confirmp).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(ChangePasswordActivity.this, "Password Changed", Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Please Try Again Later", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        else{
                            Toast.makeText(ChangePasswordActivity.this,"Oops! Operation Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            else{
                Toast.makeText(this,"Passwords Don't Match",Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this,"Enter all the details",Toast.LENGTH_SHORT).show();
        }
    }
}
