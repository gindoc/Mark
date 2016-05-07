package com.cwenhui.mark.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.cwenhui.mark.R;
import com.cwenhui.mark.common.CommonPagerAdapter;
import com.cwenhui.mark.common.DialogViewHolder;
import com.cwenhui.mark.fragment.DiscussFragment;
import com.cwenhui.mark.presenter.DiscussActivityPresenter;
import com.cwenhui.mark.view.IDiscussActivityView;
import com.cwenhui.mark.view.IDiscussView;
import com.cwenhui.mark.widget.CustomDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by cwenhui on 2016.02.23
 */
public class DisgussActivity extends AppCompatActivity implements IDiscussActivityView,View.OnClickListener {
    private DiscussActivityPresenter presenter;
    private FloatingActionButton mFAB;
    private TextView mType;
    private CustomDialog mDialog;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private CommonPagerAdapter adapter;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discuss);
        initView();
    }

    private void initView() {
        presenter = new DiscussActivityPresenter(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_activity_discuss);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);

        mFAB = (FloatingActionButton) findViewById(R.id.fab_activity_discuss);
        mFAB.setOnClickListener(this);
        mType = (TextView) findViewById(R.id.tv_type_activity_discuss);
        mType.setOnClickListener(this);

        mDialog = new CustomDialog(this, null, R.layout.layout_dialog_activity_discuss) {
            @Override
            public void convert(DialogViewHolder holder, Map<String, String> data) {
                holder.setClickListener(R.id.tv_dialog_discuss_newest, DisgussActivity.this)
                        .setClickListener(R.id.tv_dialog_discuss_hotest, DisgussActivity.this)
                        .setClickListener(R.id.tv_dialog_discuss_cream, DisgussActivity.this);
            }
        };

        mViewPager = (ViewPager) findViewById(R.id.viewpager_activity_discuss);
        mTabLayout = (TabLayout) findViewById(R.id.tablayout_activity_discuss);
        setupViewPager();
    }

    private void setupViewPager() {
        fragments = new ArrayList<>();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_activity_discuss:
                Intent intent = new Intent(DisgussActivity.this, PublishTopicActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
                break;
            case R.id.tv_type_activity_discuss:
                mDialog.show();
                break;
            case R.id.tv_dialog_discuss_newest:
                presenter.dispatchType2Fragment(fragments, IDiscussView.NEWEST);
                break;
            case R.id.tv_dialog_discuss_hotest:
                presenter.dispatchType2Fragment(fragments, IDiscussView.HOTEST);
                break;
            case R.id.tv_dialog_discuss_cream:
                presenter.dispatchType2Fragment(fragments, IDiscussView.CREAM);
                break;
        }
    }

    @Override
    public void setType(String type) {
        mType.setText(type);
    }

    @Override
    public void dismissDialog() {
        mDialog.dismiss();
    }
}
