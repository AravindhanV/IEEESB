package com.example.ieee_sb;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailPopUpActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private ImageButton left,right;
    private ImageView poster;
    private Dialog dialog;
    private Event e;
    private TextView title,date,time,description,fee;

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
        date.setText(""+e.getDate()+" "+e.getMonth()+", "+e.getYear());
        description.setText(e.getDescription());
        time.setText(e.getTime());
        if(e.getFee()==0){
            fee.setText("FREE!");
        }
        else {
            fee.setText("" + e.getFee() + "/-");
        }
        Picasso.get().setIndicatorsEnabled(true);
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
