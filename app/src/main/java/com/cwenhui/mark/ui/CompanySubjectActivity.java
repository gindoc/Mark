package com.cwenhui.mark.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.cwenhui.mark.R;
import com.cwenhui.mark.adapter.CompanySubjectPagerAdapter;
import com.cwenhui.mark.fragment.CompanyAllFragment;
import com.cwenhui.mark.fragment.CompanyRecommendFragment;

import java.util.ArrayList;
import java.util.List;

import static com.cwenhui.mark.R.id.toolbar_activity_company_subject;

/**
 * Created by cwenhui on 2016.02.23
 */
public class CompanySubjectActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private CompanySubjectPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_subject);
        initView();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager_activity_company_subject);
        mTabLayout = (TabLayout) findViewById(R.id.tablayout_activity_company_subject);
        Toolbar toolbar = (Toolbar) findViewById(toolbar_activity_company_subject);
        toolbar.setTitle("公司真题套题");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        setupViewPager();
    }

    private void setupViewPager() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new CompanyRecommendFragment());
        fragments.add(new CompanyAllFragment());
        adapter = new CompanySubjectPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_company_subject, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.company_subject_search:
                Snackbar.make(mTabLayout, "search", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.company_subject_notice:
                break;
            case R.id.company_subject_filter:
                break;
            default:
                break;
        }
        return true;
    }
}
