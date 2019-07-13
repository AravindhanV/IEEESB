package com.example.ieee_sb.MainFragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.ieee_sb.CalendarActivity;
import com.example.ieee_sb.Data;
import com.example.ieee_sb.GalleryActivity;
import com.example.ieee_sb.R;
import com.example.ieee_sb.TeamActivity;

public class HomeFragment extends Fragment {

    private View view;
    private CardView calender,gallery,team,membership,whatsapp;
    private ImageView calimg,galimg,teamimg,whatsappimg,memberimg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_home_page, container, false);

        calender = view.findViewById(R.id.card_calender);
        gallery = view.findViewById(R.id.card_gallery);
        team = view.findViewById(R.id.card_team);
        membership = view.findViewById(R.id.card_memberships);
        whatsapp = view.findViewById(R.id.card_whatsapp);
        calimg =view.findViewById(R.id.home_calendar_icon);
        galimg = view.findViewById(R.id.home_gallery_icon);
        teamimg = view.findViewById(R.id.home_team_icon);
        whatsappimg = view.findViewById(R.id.home_whatsapp_icon);
        memberimg = view.findViewById(R.id.home_member_icon);

        Animation pulse = AnimationUtils.loadAnimation(getActivity(), R.anim.pulse);

        if (Data.isMember){
            whatsapp.setVisibility(View.VISIBLE);
            whatsappimg.startAnimation(pulse);
        }
        else{
            membership.setVisibility(View.VISIBLE);
            memberimg.startAnimation(pulse);
        }

        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), CalendarActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(getActivity(), calimg, "calendar");
                startActivity(i,options.toBundle());
            }
        });

        team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), TeamActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(getActivity(), teamimg, "team");
                startActivity(i,options.toBundle());
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), GalleryActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(getActivity(), galimg, "gallery");
                startActivity(i,options.toBundle());
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

