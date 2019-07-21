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
    private RecyclerView list,list1;
    private TextView title,empty;
    private ArrayList<CalendarItem> items,items1;
    private RecyclerView.Adapter adapter,adapter1;
    private RecyclerView.LayoutManager layoutManager,layoutManager1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        calender = findViewById(R.id.calendar);
        list = findViewById(R.id.calendar_list_recycler_events);
        empty = findViewById(R.id.calendar_empty);
        title = findViewById(R.id.calendar_title);
        list1 = findViewById(R.id.calendar_list_recycler_workshops);

        Calendar c = Calendar.getInstance();
        title.setText(""+c.get(Calendar.DAY_OF_MONTH)+" "+Data.months[c.get(Calendar.MONTH)]+", "+c.get(Calendar.YEAR));

        calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
//                list.removeAllViews();
                empty.setVisibility(View.VISIBLE);
                items = new ArrayList<>();
                items1 = new ArrayList<>();
                title.setText(""+dayOfMonth+" "+Data.months[month]+", "+year);
                for(int i=0;i<Data.events.size();i++){
                    Event e = Data.events.get(i);
                    if(e.getYear()==year && e.getDate() == dayOfMonth && e.getMonth()==(month+1)){
                        empty.setVisibility(View.GONE);
                        items.add(new CalendarItem(e.getTitle(),e.getTime(),e.getVenue(),i));
                    }
                }
                for(int i=0;i<Data.workshops.size();i++){
                    Event e = Data.workshops.get(i);
                    if(e.getYear()==year && e.getDate() == dayOfMonth && e.getMonth()==(month+1)){
                        empty.setVisibility(View.GONE);
                        items1.add(new CalendarItem(e.getTitle(),e.getTime(),e.getVenue(),i));
                    }
                }
                adapter = new CalendarAdapterEvents(items,CalendarActivity.this);
                list.setAdapter(adapter);

                adapter1 = new CalendarAdapterWorkshops(items1,CalendarActivity.this);
                list1.setAdapter(adapter1);
            }
        });
        layoutManager = new LinearLayoutManager(CalendarActivity.this);
        list.setLayoutManager(layoutManager);
        list.setAdapter(adapter);

        layoutManager1 = new LinearLayoutManager(CalendarActivity.this);
        list1.setLayoutManager(layoutManager1);
        list1.setAdapter(adapter1);
    }
}
