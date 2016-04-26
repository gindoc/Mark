package com.cwenhui.mark.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

//import android.support.v4.app.FragmentManager;

/**
 * Created by cwenhui on 2016.02.23
 */
public class CompanySubjectPagerAdapter extends FragmentPagerAdapter {
    private String titles[] = new String[]{"推荐", "全部"};
    private List<Fragment> mFragments;

    public CompanySubjectPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
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
