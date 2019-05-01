package com.example.ieee_sb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class RegistrationActivity extends AppCompatActivity {

    private EditText name,email,password,confirmpassword,id;
    private Button register;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        name = findViewById(R.id.register_name);
        password = findViewById(R.id.register_password);
        confirmpassword = findViewById(R.id.register_password_confirm);
        id = findViewById(R.id.register_memberid);
        register = findViewById(R.id.register_btn_register);
        radioGroup = findViewById(R.id.register_buttongrp);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.register_radio_yes){
                    id.setVisibility(View.VISIBLE);
                }
                else{
                    id.setVisibility(View.GONE);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this,HomeActivity.class));
            }
        });
    }
}
