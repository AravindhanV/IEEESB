package com.example.ieee_sb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {

    private CalendarView calender;
    private RecyclerView list;
    private TextView title,empty;
    private ArrayList<CalendarItem> items;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        calender = findViewById(R.id.calendar);
        list = findViewById(R.id.calendar_list_recycler);
        empty = findViewById(R.id.calendar_empty);
        title = findViewById(R.id.calendar_title);

        Calendar c = Calendar.getInstance();
        title.setText(""+c.get(Calendar.DAY_OF_MONTH)+" "+Data.months[c.get(Calendar.MONTH)]+", "+c.get(Calendar.YEAR));

        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
//                list.removeAllViews();
                empty.setVisibility(View.VISIBLE);
                items = new ArrayList<>();
                title.setText(""+dayOfMonth+" "+Data.months[month]+", "+year);
                for(int i=0;i<Data.events.size();i++){
                    Event e = Data.events.get(i);
                    if(e.getYear()==year && e.getDate() == dayOfMonth && e.getMonth()==(month+1)){
                        empty.setVisibility(View.GONE);
                        items.add(new CalendarItem(e.getTitle(),e.getTime(),e.getVenue(),i));
                    }
                }
                adapter = new CalendarAdapter(items,CalendarActivity.this);
                list.setAdapter(adapter);
            }
        });
        layoutManager = new LinearLayoutManager(CalendarActivity.this);
        list.setLayoutManager(layoutManager);
        list.setAdapter(adapter);
    }
}
