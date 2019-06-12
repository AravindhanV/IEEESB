package com.example.ieee_sb.EWFragments;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ieee_sb.Event;
import com.example.ieee_sb.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EWAdapter extends RecyclerView.Adapter<EWAdapter.ViewHolder> {

    ArrayList<Event> events;
    private Dialog dialog;
    private Context context;

    public EWAdapter(ArrayList<Event> events,Context context){
        this.events = events;
        this.context = context;
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

        final int idx = i;

        viewHolder.poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp();
            }
        });

        viewHolder.itemcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Stuff","Card");
            }
        });

        viewHolder.topcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title,date,month,year,time;
        public ImageView poster;
        public CardView itemcard;
        public View topcard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.event_card_event_name);
            date = itemView.findViewById(R.id.event_card_date);
            month = itemView.findViewById(R.id.event_card_month);
            year = itemView.findViewById(R.id.event_card_year);
            time = itemView.findViewById(R.id.event_card_event_time);
            poster = itemView.findViewById(R.id.event_poster);
            itemcard = itemView.findViewById(R.id.item_card);
            topcard = itemView.findViewById(R.id.event_card_top_blue);
        }
    }

    public void showPopUp(){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.activity_pop_up);
        Window window = dialog.getWindow();
        Image
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.show();
    }
}
