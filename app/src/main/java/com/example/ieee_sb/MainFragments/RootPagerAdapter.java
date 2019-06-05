package com.example.ieee_sb.MainFragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class RootPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> strings = new ArrayList<>();

    public RootPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    public void add(Fragment f, String s){
        fragments.add(f);
        strings.add(s);
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int i){
        return strings.get(i);
    }
}
