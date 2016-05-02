package com.cwenhui.mark.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

//import android.support.v4.app.FragmentManager;

/**
 * Created by cwenhui on 2016.02.23
 */
public class CommonPagerAdapter extends FragmentPagerAdapter {
    private String titles[];
    private List<Fragment> mFragments;

    public CommonPagerAdapter(FragmentManager fm, String[] titles, List<Fragment> fragments) {
        super(fm);
        this.titles = titles;
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
