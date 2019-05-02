package com.example.ieee_sb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTPActivity extends AppCompatActivity {
    private PinView pinView;
    private EditText phone,password;
    private Button send, signup;
    private TextView change,disclaimer;
    public String verificationId;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        pinView = findViewById(R.id.otp_edit_pin);
        send = findViewById(R.id.otp_send);
        signup = findViewById(R.id.otp_next);
        phone = findViewById(R.id.otp_edit_phone);
        change = findViewById(R.id.otp_change);
        disclaimer = findViewById(R.id.otp_disclaimer);
        password = findViewById(R.id.otp_edit_password);

        pinView.setVisibility(View.GONE);
        signup.setVisibility(View.GONE);
        change.setVisibility(View.GONE);
        password.setVisibility(View.GONE);

        firebaseAuth = FirebaseAuth.getInstance();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getCurrentFocus()!=null) {
                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }

                if(validate()){
                    phone.setVisibility(View.GONE);
                    send.setVisibility(View.GONE);
                    disclaimer.setVisibility(View.GONE);
                    pinView.setVisibility(View.VISIBLE);
                    signup.setVisibility(View.VISIBLE);
                    change.setVisibility(View.VISIBLE);
//                    password.setVisibility(View.VISIBLE);
                    sendVerificationCode("+91"+phone.getText().toString().trim());
                    phone.setText("");
                }
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone.setVisibility(View.VISIBLE);
                send.setVisibility(View.VISIBLE);
                disclaimer.setVisibility(View.VISIBLE);
                pinView.setVisibility(View.GONE);
                signup.setVisibility(View.GONE);
                change.setVisibility(View.GONE);
                password.setVisibility(View.GONE);
            }
        });

        pinView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==6){
                    if(getCurrentFocus()!=null) {
                        InputMethodManager inputManager = (InputMethodManager)
                                getSystemService(Context.INPUT_METHOD_SERVICE);

                        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyCode(pinView.getText().toString().trim());
//                startActivity(new Intent(OTPActivity.this,SignInActivity.class));
            }
        });
    }

    private boolean validate(){
        boolean result=false;

        if(phone.getText().toString().trim().length()==10){
            result=true;
        }
        else{
            Toast.makeText(this,"Please Enter a Valid Number",Toast.LENGTH_SHORT).show();
        }

        return result;
    }

    private void sendVerificationCode(String number){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(number,60,TimeUnit.SECONDS,this,mCallBack);
    }

    private void verifyCode(String code){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId,code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseAuth.getInstance().signOut();
                    Intent i = new Intent(OTPActivity.this,SignInActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);

                }
                else{
                    Toast.makeText(OTPActivity.this,"Verification Unsuccessful",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
            Log.v("onCodeSent:ID",verificationId);
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if(code != null){
//                verifyCode(code);
                pinView.setText(code);
            }
            firebaseAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        FirebaseAuth.getInstance().signOut();
                        Intent i = new Intent(OTPActivity.this,HomeActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);

                    }
                    else{
                        Toast.makeText(OTPActivity.this,"Verification Unsuccessful",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(OTPActivity.this, "Verification Failed", Toast.LENGTH_SHORT).show();
            Log.v("Error from callback: ",e.getMessage());
        }
    };
}
