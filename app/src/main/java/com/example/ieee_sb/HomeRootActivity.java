package com.example.ieee_sb;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ieee_sb.MainFragments.EWFragment;
import com.example.ieee_sb.MainFragments.HomeFragment;
import com.example.ieee_sb.MainFragments.ProfileFragment;
import com.example.ieee_sb.MainFragments.RootPagerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeRootActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private BottomNavigationView navigation;
    private ImageView backdrop;
    private TextView welcome;
    private LinearLayout splash,home;
    private BottomNavigationMenuView menu;
    private FirebaseDatabase firebaseDatabase;

    private String iname,iusn,isem,iid;
    private boolean isMember;

    private Animation frombottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_root);

        viewPager = findViewById(R.id.root_viewpager);
        navigation = findViewById(R.id.root_navigation);
        menu = (BottomNavigationMenuView)navigation.getChildAt(0);
        backdrop = findViewById(R.id.backdrop);
        welcome = findViewById(R.id.welcome_text);
        splash = findViewById(R.id.home_textsplash);
        home = findViewById(R.id.home_texthome);

        iname = getIntent().getStringExtra("name");
        firebaseDatabase = FirebaseDatabase.getInstance();

        welcome.append(iname+"!");

        RootPagerAdapter adapter = new RootPagerAdapter(getSupportFragmentManager());
        adapter.add(new EWFragment(),"Reset");
        adapter.add(new HomeFragment(),"Home");
        adapter.add(new ProfileFragment(),"Register");

        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        backdrop.getLayoutParams().width = (int)(1.31*width);
        backdrop.getLayoutParams().height = (int)(1.27*height);

        backdrop.animate().translationY(-(float)(1.065*height)).setDuration(800).setStartDelay(800);
        splash.animate().translationY(-height).alpha(0).setDuration(800).setStartDelay(800);
        home.startAnimation(frombottom);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

//            0C3B51

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        ((TextView)(home.getChildAt(0))).setText("");
                        ((TextView)(home.getChildAt(1))).setText("");
                        backdrop.setVisibility(View.GONE);
                        navigation.setSelectedItemId(R.id.navigation_activities);
                        navigation.setBackgroundColor(0xFF156387);
                        break;
                    case 1:
                        ((TextView)(home.getChildAt(0))).setText("Explore");
                        ((TextView)(home.getChildAt(1))).setText("The journey starts here!");
                        backdrop.setVisibility(View.VISIBLE);
                        navigation.setSelectedItemId(R.id.navigation_home);
                        navigation.setBackgroundColor(0xFF239FD8);
                        break;
                    case 2:
                        ((TextView)(home.getChildAt(0))).setText("");
                        ((TextView)(home.getChildAt(1))).setText("");
                        navigation.setBackgroundColor(0xFF156387);
                        backdrop.setVisibility(View.GONE);
                        navigation.setSelectedItemId(R.id.navigation_profile);
                        break;
                }
//                for(int x=0;x<menu.getChildCount();x++) {
//                    final View iconView = menu.getChildAt(x).findViewById(android.support.design.R.id.icon);
//                    final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
//                    final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
//                    if (x == i) {
//                        layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, displayMetrics);
//                        layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, displayMetrics);
//                        continue;
//                    } else {
//                        layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, displayMetrics);
//                        layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, displayMetrics);
//                    }
//                    iconView.setLayoutParams(layoutParams);
//                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        DatabaseReference ref = firebaseDatabase.getReference("/gallery");
        Data.images = new ArrayList<>();
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Data.images.add(Picasso.get().load(dataSnapshot.getValue().toString()).networkPolicy(NetworkPolicy.OFFLINE));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_activities:
                    viewPager.setCurrentItem(0);
                    navigation.setBackgroundColor(0xFF156387);
                    getWindow().setStatusBarColor(0xFF10445C);
                    return true;
                case R.id.navigation_home:
                    viewPager.setCurrentItem(1);
                    navigation.setBackgroundColor(0xFF239FD8);
                    getWindow().setStatusBarColor(0xFF1D54A3);
                    return true;
                case R.id.navigation_profile:
                    viewPager.setCurrentItem(2);
                    navigation.setBackgroundColor(0xFF156387);
                    getWindow().setStatusBarColor(0xFF10445C);
                    return true;
            }
            return false;
        }
    };
}
