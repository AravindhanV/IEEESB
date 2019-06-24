package com.example.ieee_sb.MainFragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ieee_sb.CalendarActivity;
import com.example.ieee_sb.Data;
import com.example.ieee_sb.R;
import com.example.ieee_sb.TeamActivity;

public class HomeFragment extends Fragment {

    private View view;
    private CardView calender,gallery,team,membership,whatsapp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_home_page, container, false);

        calender = view.findViewById(R.id.card_calender);
        gallery = view.findViewById(R.id.card_gallery);
        team = view.findViewById(R.id.card_team);
        membership = view.findViewById(R.id.card_memberships);
        whatsapp = view.findViewById(R.id.card_whatsapp);

        if (Data.isMember){
            whatsapp.setVisibility(View.VISIBLE);
        }
        else{
            membership.setVisibility(View.VISIBLE);
        }

        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CalendarActivity.class));
            }
        });

        team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TeamActivity.class));
            }
        });

        membership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://chat.whatsapp.com/CAMw0Qkzp539A7tJd1GGZT");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        return view;
    }
}

