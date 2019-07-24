package com.example.ieee_sb;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class membershipAdapter extends RecyclerView.Adapter<membershipAdapter.ViewHolder>{

    String[] steps;
    Context context;
    View view1;
    ViewHolder viewHolder1;
    TextView textView;

    public membershipAdapter(Context context,String[] steps){

        this.steps = steps;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textView;

        public ViewHolder(View v){
            super(v);
            textView = (TextView)v.findViewById(R.id.membershipSingleStep);
        }
    }

    @Override
    public membershipAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        view1 = LayoutInflater.from(context).inflate(R.layout.membershipstep_layout,parent,false);

        viewHolder1 = new ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

        holder.textView.setText(steps[position]);

    }

    @Override
    public int getItemCount(){

        return steps.length;
    }
}
