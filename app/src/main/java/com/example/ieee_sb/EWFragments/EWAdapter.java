package com.example.ieee_sb.EWFragments;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ieee_sb.Event;
import com.example.ieee_sb.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EWAdapter extends RecyclerView.Adapter<EWAdapter.ViewHolder> {

    ArrayList<Event> events;

    public EWAdapter(ArrayList<Event> events){
        this.events = events;
    }

    @NonNull
    @Override
    public EWAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EWAdapter.ViewHolder viewHolder, int i) {
        viewHolder.title.setText(events.get(i).getTitle());
        viewHolder.date.setText(""+events.get(i).getDate());
        viewHolder.month.setText(events.get(i).getMonth());
        viewHolder.year.setText(""+events.get(i).getYear());
        viewHolder.time.setText("Time: "+events.get(i).getTime());
//        viewHolder.poster.setPadding(0,0,0,0);
        String url = events.get(i).getURL();

        if(!url.isEmpty()){
            viewHolder.poster.setPadding(100,10,10,10);
            Picasso.get().load(url).fit().centerCrop().into(viewHolder.poster);
        }
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title,date,month,year,time;
        public ImageView poster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.event_card_event_name);
            date = itemView.findViewById(R.id.event_card_date);
            month = itemView.findViewById(R.id.event_card_month);
            year = itemView.findViewById(R.id.event_card_year);
            time = itemView.findViewById(R.id.event_card_event_time);
            poster = itemView.findViewById(R.id.event_poster);
        }
    }
}
