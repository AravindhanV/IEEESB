package com.example.ieee_sb;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ieee_sb.Fragments.HomeFragment;
import com.example.ieee_sb.Fragments.ProfileFragment;
import com.example.ieee_sb.Fragments.TabPagerAdapter;
import com.example.ieee_sb.Fragments.TeamFragment;

public class HomeRootActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private BottomNavigationView navigation;
    private ImageView backdrop;
    private TextView welcome;
    private LinearLayout splash,home;
    private BottomNavigationMenuView menu;

    private String iname,iusn,isem,iid;

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

        welcome.append(iname+"!");

        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager());
        adapter.add(new TeamFragment(),"Reset");
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

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        ((TextView)(home.getChildAt(0))).setText("IEEE Team");
                        navigation.setSelectedItemId(R.id.navigation_team);
                        break;
                    case 1:
                        ((TextView)(home.getChildAt(0))).setText("Explore");
                        navigation.setSelectedItemId(R.id.navigation_home);
                        break;
                    case 2:
                        ((TextView)(home.getChildAt(0))).setText("Profile");
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

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_team:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_home:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_profile:
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };
}
