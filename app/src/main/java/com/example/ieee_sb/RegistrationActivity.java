package com.example.ieee_sb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    private EditText name,semester,usn,id;
    private Button register;
    private RadioGroup radioGroup;
    private String memberId="";
    private String user_name,user_sem,user_usn;
    private FirebaseAuth firebaseAuth;
    private RadioButton haveID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        name = findViewById(R.id.register_name);
        semester = findViewById(R.id.register_semester);
        usn = findViewById(R.id.register_usn);
        id = findViewById(R.id.register_memberid);
        register = findViewById(R.id.register_btn_register);
        radioGroup = findViewById(R.id.register_buttongrp);
        haveID = findViewById(radioGroup.getCheckedRadioButtonId());
        firebaseAuth = FirebaseAuth.getInstance();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.register_radio_yes){
                    id.setVisibility(View.VISIBLE);
                }
                else{
                    if(getCurrentFocus()!=null) {
                        InputMethodManager inputManager = (InputMethodManager)
                                getSystemService(Context.INPUT_METHOD_SERVICE);

                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                    id.setVisibility(View.GONE);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    private void validate(){
        user_name = this.name.getText().toString().trim();
        user_sem = semester.getText().toString().trim();
        user_usn = this.usn.getText().toString().trim().toUpperCase();
        if(haveID.isSelected()){
            memberId=id.getText().toString().trim();
        }
        else{
            memberId="";
        }
        Log.v("Stuff",""+haveID.isChecked()+","+memberId);
        if(user_name.isEmpty()||user_usn.isEmpty()||user_sem.isEmpty()||(haveID.isChecked()&&memberId.isEmpty())){
            Toast.makeText(this,"Please Enter all Details",Toast.LENGTH_SHORT).show();
        }
        else{
            Pattern p = Pattern.compile("1PE\\d\\d[A-Z][A-Z]\\d\\d\\d");
            Matcher m = p.matcher(user_usn);
            boolean b = m.matches();

            if(b){
                sendUserData();
            }
            else{
                Toast.makeText(this,"Please Enter Valid Data",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("/userinfo");
        databaseReference = databaseReference.child(firebaseAuth.getUid());
        RegistrationInfo x = new RegistrationInfo(user_name,user_sem,user_usn,memberId);
        databaseReference.setValue(x);

        startActivity(new Intent(RegistrationActivity.this,HomeRootActivity.class));
    }
}
