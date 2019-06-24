package com.example.ieee_sb;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {

    ArrayList<CalendarItem> events;
    private Context context;

    public CalendarAdapter(ArrayList<CalendarItem> events,Context context){
        this.events = events;
        this.context = context;
    }

    @NonNull
    @Override
    public CalendarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.calender_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CalendarAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.title.setText(events.get(i).getTitle());
        viewHolder.time.setText(events.get(i).getTime());
        viewHolder.venue.setText(events.get(i).getVenue());
        viewHolder.index = events.get(i).getIndex();

        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailPopUpActivity.class);
                intent.putExtra("item",viewHolder.index);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title,time,venue;
        public ConstraintLayout layout;
        public int index;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.calendar_item_title);
            time = itemView.findViewById(R.id.calendar_item_time);
            venue = itemView.findViewById(R.id.calendar_item_venue);
            layout = itemView.findViewById(R.id.calendar_item_layout);
        }
    }
}
