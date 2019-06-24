package com.example.ieee_sb;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailPopUpActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private ImageButton left,right;

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

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final OrganizerAdapter adapter = new OrganizerAdapter(getSupportFragmentManager());
        ArrayList<Organizer> organizers = Data.events.get(item).getOrganizers();
        for(int i=0;i<organizers.size();i++){
            adapter.add(new OrganizerFragment(organizers.get(i)));
        }

        Event e = Data.events.get(item);
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

}
