package com.cwenhui.mark.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.cwenhui.mark.R;

import java.util.List;

public class MessagePagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "MessagePagerAdapter";
    private List<Fragment> mFragments;
    private Context context;
    private String titles[] = new String[]{"朋友私信", "系统通知"};

    public MessagePagerAdapter(Context context, FragmentManager fm, List<Fragment> fragments ) {
        super(fm);
        this.context = context;
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

    /**
     * 给tab添加自定义View
     */
    public View addCustomViewToTab(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_fragment_message_tab_title_, null);
        TextView tv = (TextView) view.findViewById(R.id.tv_fragment_message_tab_title);
        tv.setTextColor(Color.parseColor("#000000"));
        tv.setText(titles[position]);
        return view;
    }

    /**
     * 根据viewpager当前的位置设置tab的状态
     * @param tabs
     * @param pos
     */
    public void setTabState(TabLayout tabs, int pos) {
        for (int i = 0; i < titles.length; i++) {
            View view = tabs.getTabAt(i).getCustomView();
            TextView tv = (TextView) view.findViewById(R.id.tv_fragment_message_tab_title);
            tv.setTextColor(Color.parseColor("#000000"));
            if (i == pos) {
                tv.setTextColor(Color.parseColor("#00B090"));
            }
        }
    }
}
