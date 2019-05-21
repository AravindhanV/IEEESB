package com.example.ieee_sb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {

    private ImageView logo;
    private TextView welcome,text,register,forgot;
    private Animation smalltobig,btta1,btta2;
    private Button signin;
    private EditText id,password;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private ProgressDialog progressDialog;
    public RegistrationInfo info;
    private String profileName="";

    public static boolean IS_FIRST_START = true;
    private SQLiteDatabase db;

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

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user!=null){
//            firebaseDatabase = FirebaseDatabase.getInstance();
//            DatabaseReference databaseReference = firebaseDatabase.getReference("/userinfo");
//            databaseReference = databaseReference.child(firebaseAuth.getUid());
//            databaseReference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    info = dataSnapshot.getValue(RegistrationInfo.class);
//                    Log.v("DataGot",""+info);
//                    if(info==null){
//                        startActivity(new Intent(SignInActivity.this,RegistrationActivity.class));
//                    }
//                    else{
//                        finish();
//                        Intent i = new Intent(SignInActivity.this,HomeRootActivity.class);
//                        i.putExtra("id",info.id);
//                        i.putExtra("name",info.name);
//                        i.putExtra("sem",info.sem);
//                        i.putExtra("usn",info.usn);
//                        startActivity(i);
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//            });
            boolean x = retrieveSQL();
            Log.v("XVal",""+x);
            if(x){
                Intent i = new Intent(SignInActivity.this,HomeRootActivity.class);
                i.putExtra("name",profileName);
                startActivity(i);
            }
            else{
                startActivity(new Intent(SignInActivity.this,RegistrationActivity.class));
            }

//            finish();
//            startActivity(new Intent(SignInActivity.this,HomeRootActivity.class));
        }

        else {

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(SignInActivity.this, RegistrationMethodActivity.class));
                }
            });

            forgot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(SignInActivity.this, ForgotPasswordActivity.class));
                }
            });

            signin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    validate(id.getText().toString().trim(), password.getText().toString().trim());
                }
            });

            if (IS_FIRST_START) {
                IS_FIRST_START = false;
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

    private void validate(String username,String password){

        progressDialog.setMessage("Preparing Your Dashboard");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    checkEmailVerification();
                }
                else{
                    progressDialog.dismiss();
                    Toast.makeText(SignInActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean retrieveSQL(){
        String uid = firebaseAuth.getUid();
//            db.execSQL("INSERT INTO users VALUES ('1234','abcd')");
//            db.execSQL("INSERT INTO users VALUES ('"+uid+"','xyz')");
        db = this.openOrCreateDatabase("Users",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users (uid VARCHAR, name VARCHAR)");
        Cursor c = db.rawQuery("SELECT * FROM users WHERE uid=\""+uid+"\"",null);
        Log.v("UID",uid);
        int index = c.getColumnIndex("name");
        boolean x = c.moveToFirst();
        if(x) {
            profileName = c.getString(index);
        }
        return x;
    }

    private void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        boolean emailflag = firebaseUser.isEmailVerified();

        if(emailflag){
            boolean x = retrieveSQL();
            if(x){
                Intent i = new Intent(SignInActivity.this,HomeRootActivity.class);
                i.putExtra("name",profileName);
                startActivity(i);
            }
            else {
                firebaseAuth = FirebaseAuth.getInstance();
                firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference("/userinfo");
                databaseReference = databaseReference.child(firebaseAuth.getUid());
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        info = dataSnapshot.getValue(RegistrationInfo.class);
                        if (info == null) {
                            startActivity(new Intent(SignInActivity.this, RegistrationActivity.class));
                        } else {
                            db.execSQL("INSERT INTO users VALUES ('"+firebaseAuth.getUid()+"','"+info.name+"')");
                            finish();
                            Intent i = new Intent(SignInActivity.this, HomeRootActivity.class);
                            i.putExtra("name", info.name);
                            startActivity(i);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        }
        else{
            Toast.makeText(this,"Verify your email",Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }
}
