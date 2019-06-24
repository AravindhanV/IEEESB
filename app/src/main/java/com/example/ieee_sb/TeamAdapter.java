package com.example.ieee_sb;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {

    ArrayList<TeamMember> members;
    private Context context;

    public TeamAdapter(ArrayList<TeamMember> members,Context context){
        this.members = members;
        this.context = context;
    }

    @NonNull
    @Override
    public TeamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.team_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TeamAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.name.setText(members.get(i).name);
        viewHolder.position.setText(members.get(i).position);
        viewHolder.picture.setImageResource(members.get(i).image);

        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"+members.get(i).email)); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, "Hey there!");
//                intent.putExtra(Intent.EXTRA_SUBJECT, "");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name,position;
        public ImageView picture;
        public View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.team_item_name);
            position = itemView.findViewById(R.id.team_item_position);
            picture = itemView.findViewById(R.id.team_item_image);
            view = itemView;
        }
    }
}