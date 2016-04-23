package com.cwenhui.mark.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cwenhui.mark.MainActivity;
import com.cwenhui.mark.R;
import com.cwenhui.mark.adapter.MessagePagerAdapter;
import com.cwenhui.mark.presenter.MessagePresenter;
import com.cwenhui.mark.view.IMessageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class MessageFragment extends Fragment implements IMessageView, ViewPager.OnPageChangeListener{
    private View view;
    private MessagePresenter presenter;
    private Toolbar toolbar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Context context;
    private MessagePagerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_message, container, false);
        context = inflater.getContext();
        initView();
        return view;
    }

    private void initView() {
        presenter = new MessagePresenter(this);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar_fragment_message);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager_fragment_message);
        mTabLayout = (TabLayout) view.findViewById(R.id.tablayout_fragment_message);

        toolbar.setTitle("消息");
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        setupViewPager();
        presenter.showNewMsg();
    }

    private void setupViewPager() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FriendMsgFragment());
        fragments.add(new FriendMsgFragment());
        adapter = new MessagePagerAdapter(context, getChildFragmentManager(), fragments/*, titles*/);
        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(this);
        mTabLayout.setupWithViewPager(mViewPager);

        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setCustomView(adapter.addCustomViewToTab(i));
        }
        adapter.setTabState(mTabLayout, 0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        adapter.setTabState(mTabLayout, position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void promptNewMsg(int position) {
        ImageView newMsg = getNewMsgView(position);
        newMsg.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNewMsg(int position) {
        ImageView newMsg = getNewMsgView(position);
        newMsg.setVisibility(View.GONE);
    }

    /**
     * 从tab中获得提示新消息的红点
     * @param position
     * @return
     */
    private ImageView getNewMsgView(int position) {
        View view = mTabLayout.getTabAt(position).getCustomView();
        return (ImageView) view.findViewById(R.id.iv_fragment_message_tab_new_msg);
    }
}
