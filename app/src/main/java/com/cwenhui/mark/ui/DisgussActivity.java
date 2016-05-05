package com.cwenhui.mark.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.cwenhui.mark.R;
import com.cwenhui.mark.common.CommonPagerAdapter;
import com.cwenhui.mark.fragment.DiscussFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class DisgussActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private CommonPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discuss);
        initView();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_activity_discuss);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);

        mViewPager = (ViewPager) findViewById(R.id.viewpager_activity_discuss);
        mTabLayout = (TabLayout) findViewById(R.id.tablayout_activity_discuss);
        setupViewPager();
    }

    private void setupViewPager() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(DiscussFragment.newInstance(DiscussFragment.ALL));
        fragments.add(DiscussFragment.newInstance(DiscussFragment.TECH));
        fragments.add(DiscussFragment.newInstance(DiscussFragment.INTERVIEW_EXCE));
        fragments.add(DiscussFragment.newInstance(DiscussFragment.CHAT));
        fragments.add(DiscussFragment.newInstance(DiscussFragment.NOTICE));
        fragments.add(DiscussFragment.newInstance(DiscussFragment.REC_SHARE));
        fragments.add(DiscussFragment.newInstance(DiscussFragment.QUES));
        fragments.add(DiscussFragment.newInstance(DiscussFragment.RECRUIT));
        fragments.add(DiscussFragment.newInstance(DiscussFragment.WORK_FEELING));
        String[] titles = new String[]{"全部", "技术交流", "笔试面经", "随便聊聊", "站内公告",
                "资源分享", "我要提问", "招聘信息", "工作感受"};
        adapter = new CommonPagerAdapter(getSupportFragmentManager(), titles, fragments);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(9);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_disguss, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
                break;
        }
        return true;
    }
}
