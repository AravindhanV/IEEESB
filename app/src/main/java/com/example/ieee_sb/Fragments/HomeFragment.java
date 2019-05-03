package com.example.ieee_sb.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.ieee_sb.R;
import com.example.ieee_sb.SignInActivity;
import com.google.firebase.auth.FirebaseAuth;

public class HomeFragment extends Fragment {

    private Button logout;
    private ImageView backdrop;
    private LinearLayout splash,home;
    private Animation frombottom;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_home_page, container, false);
        logout = view.findViewById(R.id.home_logout);
        backdrop = view.findViewById(R.id.backdrop);
        splash = view.findViewById(R.id.home_textsplash);
        home = view.findViewById(R.id.home_texthome);
//        home.setVisibility(View.GONE);
        frombottom = AnimationUtils.loadAnimation(getActivity(),R.anim.frombottom);


        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        backdrop.getLayoutParams().width = (int)(1.31*width);
        backdrop.getLayoutParams().height = (int)(1.27*height);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                getActivity().finish();
                startActivity(new Intent(getActivity(), SignInActivity.class));
            }
        });

        backdrop.animate().translationY(-height).setDuration(800).setStartDelay(800);
        splash.animate().translationY(-height).alpha(0).setDuration(800).setStartDelay(800);
        home.startAnimation(frombottom);

        return view;
    }
}

