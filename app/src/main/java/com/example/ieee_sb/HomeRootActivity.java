package com.example.ieee_sb;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.ieee_sb.Fragments.TeamFragment;
import com.example.ieee_sb.Fragments.ProfileFragment;
import com.example.ieee_sb.Fragments.HomeFragment;
import com.example.ieee_sb.Fragments.TabPagerAdapter;

public class HomeRootActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private BottomNavigationView navigation;
    private BottomNavigationMenuView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_root);

        viewPager = findViewById(R.id.root_viewpager);
        navigation = findViewById(R.id.root_navigation);
        menu = (BottomNavigationMenuView)navigation.getChildAt(0);
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager());
        adapter.add(new TeamFragment(),"Reset");
        adapter.add(new HomeFragment(),"Home");
        adapter.add(new ProfileFragment(),"Register");

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        navigation.setSelectedItemId(R.id.navigation_team);
                        break;
                    case 1:
                        navigation.setSelectedItemId(R.id.navigation_home);
                        break;
                    case 2:
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
