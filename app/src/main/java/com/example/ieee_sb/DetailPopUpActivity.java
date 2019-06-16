package com.example.ieee_sb;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

public class DetailPopUpActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_detail);

        toolbar = findViewById(R.id.my_toolbar);
        viewPager = findViewById(R.id.detail_contact_list);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        OrganizerAdapter adapter = new OrganizerAdapter(getSupportFragmentManager());
        adapter.add(new OrganizerFragment());
        adapter.add(new OrganizerFragment());
        adapter.add(new OrganizerFragment());
        adapter.add(new OrganizerFragment());
        adapter.add(new OrganizerFragment());

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
