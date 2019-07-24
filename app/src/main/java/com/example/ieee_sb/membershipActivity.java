package com.example.ieee_sb;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class membershipActivity extends AppCompatActivity {

    Context context;
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recylerViewLayoutManager;
    String[] steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership);

        steps = new String[]{"1. Go the the website www.ieee.org",
                             "2. Click on Join IEEE.",
                             "3. Then go to “Join as a student”.",
                             "4. Once the create account window pops up,go to Create account.",
                             "5. Fill all your details and click on “Continue Joining”.",
                             "6. Go to check out and check if IEEE membership is there in your bag.",
                             "7. Pay using Credit Card/Paypal.",
                             "8. You will receive an email with all the details and your member Id with validity."};

        context = getApplicationContext();

        recyclerView = findViewById(R.id.membershipRecyclerView);

        recylerViewLayoutManager = new LinearLayoutManager(context);

        recyclerView.setLayoutManager(recylerViewLayoutManager);

        recyclerViewAdapter = new membershipAdapter(context, steps);

        recyclerView.setAdapter(recyclerViewAdapter);

    }
}
