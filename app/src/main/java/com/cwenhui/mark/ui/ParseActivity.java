package com.cwenhui.mark.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.cwenhui.mark.R;
import com.cwenhui.mark.common.CommonPagerAdapter;
import com.cwenhui.mark.configs.Constant;
import com.cwenhui.mark.entity.CompletedPractice;
import com.cwenhui.mark.entity.Practice;
import com.cwenhui.mark.fragment.ParseChoiceFragment;
import com.cwenhui.mark.fragment.ParseFillBlankFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class ParseActivity extends AppCompatActivity {
    public static final String TAG = "ParseActivity";
    private ViewPager mViewPager;
    private CommonPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse);
        initData();
        initView();
    }

    private void initData() {

    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_activity_parse);
        toolbar.setTitle("试题解析");
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.back));

        mViewPager = (ViewPager) findViewById(R.id.vp_activity_parse);
        setupViewPager();
    }

    private void setupViewPager() {
        List<CompletedPractice> completedPractices = getIntent().getParcelableArrayListExtra(TAG);
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < completedPractices.size(); i++) {
            Practice practice = completedPractices.get(i).getPractice();
            switch (practice.getPraticeType()) {
                case Constant.SINGLE_PROBLEM:
                case Constant.MULTIPUL_PROBLEM:
                    fragments.add(ParseChoiceFragment.newInstance(i + 1, completedPractices.size(),
                            completedPractices.get(i)));
                    break;

                case Constant.FILL_BLANK_PROBLEM:
                    fragments.add(ParseFillBlankFragment.newInstance(i + 1, completedPractices.size(),
                            completedPractices.get(i)));
                    break;
            }

        }
        adapter = new CommonPagerAdapter(getSupportFragmentManager(), null, fragments);
        mViewPager.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_parse, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
                break;
            case R.id.parse_collect:
                item.setIcon(ContextCompat.getDrawable(this, R.drawable.icon_h_collected));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
