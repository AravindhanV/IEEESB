package com.example.ieee_sb.EWFragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ieee_sb.Data;
import com.example.ieee_sb.DetailPopUpActivityWorkshops;
import com.example.ieee_sb.Event;
import com.example.ieee_sb.R;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WorkshopAdapter extends RecyclerView.Adapter<WorkshopAdapter.ViewHolder> {

    ArrayList<Event> events;
    private Context context;

    public WorkshopAdapter(ArrayList<Event> events, Context context){
        this.events = events;
        this.context = context;
    }

    @NonNull
    @Override
    public WorkshopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final WorkshopAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.title.setText(events.get(i).getTitle());
        viewHolder.date.setText(""+events.get(i).getDate());
        viewHolder.month.setText(Data.months[events.get(i).getMonth()-1]);
        viewHolder.year.setText(""+events.get(i).getYear());
        viewHolder.time.setText("Time: "+events.get(i).getTime());

        int fee = 0;
        if( Data.isMember){
            fee = events.get(i).getMemberFee();
        }
        else{
            fee = events.get(i).getNonMemberFee();
        }
        if(fee==0){
            viewHolder.fee.setText("FREE");
        }
        else {
            viewHolder.fee.setText("₹ " + fee);
        }
//        viewHolder.poster.setPadding(0,0,0,0);
        String url = events.get(i).getURL();

        if(!url.isEmpty()){
            viewHolder.poster.setPadding(0,0,0,0);
//            Picasso.get().setIndicatorsEnabled(true);
            Picasso.get().load(url).networkPolicy(NetworkPolicy.OFFLINE).fit().centerCrop().into(viewHolder.poster);
            viewHolder.setupDialog(url);
        }

        final String url1 = url;
        viewHolder.poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.showPopUp();
            }
        });

        viewHolder.itemcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idx = searchEvent(events.get(i).getID());
                Intent intent = new Intent(context, DetailPopUpActivityWorkshops.class);
                intent.putExtra("item",idx);
                context.startActivity(intent);
            }
        });

        viewHolder.topcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    public int searchEvent(String s){
        int i=0;
        for (Event e : Data.workshops) {
            if (e.getID().equals(s)) {
                break;
            }
            i++;
        }
        return i;
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title,date,month,year,time,fee;
        public ImageView poster;
        public CardView itemcard;
        public View topcard;
        public Dialog dialog;

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
            fee = itemView.findViewById(R.id.event_card_bottom_blue);
        }

        public void setupDialog(String url){
            dialog = new Dialog(context);
            dialog.setContentView(R.layout.activity_pop_up_poster);
            Window window = dialog.getWindow();
            ImageView poster = dialog.findViewById(R.id.dialog_poster);
            Picasso.get().load(url).into(poster);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            window.setGravity(Gravity.CENTER);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        public void showPopUp(){
            dialog.setCancelable(true);
            dialog.show();
        }
    }
}
