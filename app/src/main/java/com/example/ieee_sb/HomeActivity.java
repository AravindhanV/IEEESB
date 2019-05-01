package com.example.ieee_sb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private Button logout;
    private ImageView backdrop;
    private LinearLayout splash,home;
    private Animation frombottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        logout = findViewById(R.id.home_logout);
        backdrop = findViewById(R.id.backdrop);
        splash = findViewById(R.id.home_textsplash);
        home = findViewById(R.id.home_texthome);
        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);


        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        backdrop.getLayoutParams().width = (int)(1.31*width);
        backdrop.getLayoutParams().height = (int)(1.27*height);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(HomeActivity.this,SignInActivity.class));
            }
        });

        backdrop.animate().translationY(-height).setDuration(800).setStartDelay(800);
        splash.animate().translationY(-height).alpha(0).setDuration(800).setStartDelay(800);
        home.startAnimation(frombottom);

    }
}
