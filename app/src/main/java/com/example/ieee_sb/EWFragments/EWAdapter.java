package com.example.ieee_sb.EWFragments;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ieee_sb.R;

import java.util.ArrayList;

public class EWAdapter extends RecyclerView.Adapter<EWAdapter.ViewHolder> {

    ArrayList<String> events;

    public EWAdapter(ArrayList<String> events){
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
        viewHolder.name.setText(events.get(i));
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.event_card_event_name);
        }
    }
}
