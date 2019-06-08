package com.example.ieee_sb.MainFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ieee_sb.EWFragments.EventFragment;
import com.example.ieee_sb.EWFragments.WorkshopFragment;
import com.example.ieee_sb.R;

public class EWFragment extends Fragment {

    private View view;
    private ViewPager viewPager;
    private RootPagerAdapter adapter;
    private TabLayout tabs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.activity_ew, container, false);
        viewPager = view.findViewById(R.id.ew_viewPager);
        adapter = new RootPagerAdapter(getChildFragmentManager());
        adapter.add(new EventFragment(),"Events");
        adapter.add(new WorkshopFragment(),"Workshops");
        tabs = view.findViewById(R.id.ew_tabs);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        tabs.setupWithViewPager(viewPager);

        return view;
    }
}

