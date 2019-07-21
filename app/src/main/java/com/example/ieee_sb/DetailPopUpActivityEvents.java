package com.example.ieee_sb;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailPopUpActivityEvents extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private ImageButton left,right;
    private ImageView poster;
    private Dialog dialog;
    private Event e;
    private TextView title,venue,date,time,description,fee,register;
    private String datestr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_detail);

        int item = getIntent().getIntExtra("item",-1);

        toolbar = findViewById(R.id.my_toolbar);
        viewPager = findViewById(R.id.detail_contact_list);
        left = findViewById(R.id.detail_left_arrow);
        right = findViewById(R.id.detail_right_arrow);
        title = findViewById(R.id.detail_title);
        date = findViewById(R.id.detail_date);
        time = findViewById(R.id.detail_time);
        description = findViewById(R.id.detail_description);
        fee = findViewById(R.id.detail_fee);
        poster = findViewById(R.id.detail_poster);
        venue = findViewById(R.id.detail_venue);
        register = findViewById(R.id.detail_register_button);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final OrganizerAdapter adapter = new OrganizerAdapter(getSupportFragmentManager());
        ArrayList<Organizer> organizers = Data.events.get(item).getOrganizers();
        for(int i=0;i<organizers.size();i++){
            adapter.add(new OrganizerFragment(organizers.get(i)));
        }

        e = Data.events.get(item);
        title.setText(e.getTitle());
        datestr = e.getDate()+" "+Data.months[e.getMonth()-1]+", "+e.getYear();
        date.setText(datestr);
        description.setText(e.getDescription());
        time.setText(e.getTime());
        if(Data.isMember){
            if(e.getMemberFee()==0){
                fee.setText("FREE!");
            }
            else {
                fee.setText("" + e.getMemberFee() + "/-");
            }
        }
        else{
            if(e.getNonMemberFee()==0){
                fee.setText("FREE!");
            }
            else {
                fee.setText("" + e.getNonMemberFee() + "/-");
            }
        }

        venue.setText(e.getVenue());
//        Picasso.get().setIndicatorsEnabled(true);
        Picasso.get().load(e.getURL()).networkPolicy(NetworkPolicy.OFFLINE).fit().centerCrop().into(poster);

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }
        });

        poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUp(e.getURL());
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailPopUpActivityEvents.this,EventRegistrationActivity.class));
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if(i==0){
                    left.setVisibility(View.INVISIBLE);
                    right.setVisibility(View.VISIBLE);
                }
                else if(i==adapter.getCount()-1){
                    left.setVisibility(View.VISIBLE);
                    right.setVisibility(View.INVISIBLE);
                }
                else{
                    left.setVisibility(View.VISIBLE);
                    right.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        if(adapter.getCount()==1){
            left.setVisibility(View.INVISIBLE);
            right.setVisibility(View.INVISIBLE);
        }

        left.setVisibility(View.INVISIBLE);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_share:
                shareApp();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void shareApp(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Join me for an IEEE SB Event");
        String body = "Register through the IEEE SB app to join "+e.getTitle()+" on "+datestr+" at "+e.getTime()+"\n" +
                "Download the app from the Play Store\n\n";
        body = body + "https://play.google.com/store/apps/details?id=com.udemy.android";
        intent.putExtra(Intent.EXTRA_TEXT, body);
        startActivity(Intent.createChooser(intent, "Share Via"));
    }

    public void showPopUp(String url){
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_pop_up_poster);
        Window window = dialog.getWindow();
        ImageView poster = dialog.findViewById(R.id.dialog_poster);
        Picasso.get().load(url).into(poster);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.show();
    }
}
