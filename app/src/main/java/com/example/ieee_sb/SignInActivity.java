package com.example.ieee_sb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity {

    private ImageView logo;
    private TextView welcome,text,register,forgot;
    private Animation smalltobig,btta1,btta2;
    private Button signin;
    private EditText id,password;

    public static boolean IS_FIRST_START = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        logo = findViewById(R.id.signin_logo);
        welcome = findViewById(R.id.signin_welcome);
        text = findViewById(R.id.signin_text);
        signin = findViewById(R.id.signin_btn_signin);
        id = findViewById(R.id.signin_edit_id);
        password = findViewById(R.id.signin_edit_password);
        register = findViewById(R.id.signin_register);
        forgot = findViewById(R.id.signin_forgot);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, RegistrationMethodActivity.class));
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this,ForgotPasswordActivity.class));
            }
        });

        if(IS_FIRST_START) {
            IS_FIRST_START=false;
            smalltobig = AnimationUtils.loadAnimation(this, R.anim.smalltobig);
            btta1 = AnimationUtils.loadAnimation(this, R.anim.btta);
            btta2 = AnimationUtils.loadAnimation(this, R.anim.btta2);

            logo.startAnimation(smalltobig);
            welcome.startAnimation(btta1);
            text.startAnimation(btta1);
            id.startAnimation(btta2);
            password.startAnimation(btta2);
            signin.startAnimation(btta2);
        }
    }
}
