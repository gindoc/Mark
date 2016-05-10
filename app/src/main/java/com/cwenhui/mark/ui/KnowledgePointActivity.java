package com.cwenhui.mark.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Chronometer;

import com.cwenhui.mark.R;
import com.cwenhui.mark.common.CommonPagerAdapter;
import com.cwenhui.mark.entity.PaperDetail;
import com.cwenhui.mark.fragment.KnowledgePointFragment;
import com.cwenhui.mark.presenter.KnowledgePointPresenter;
import com.cwenhui.mark.view.IKnowledgePointView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class KnowledgePointActivity extends AppCompatActivity implements IKnowledgePointView{
    private static final String TAG = "KnowledgePointActivity";
    private KnowledgePointPresenter presenter;
    private Chronometer timer;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge_point);
        initView();
    }

    private void initView() {
        presenter = new KnowledgePointPresenter(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_activity_knowledge_ponit);
        toolbar.setTitle("知识点专项练习");
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        setSupportActionBar(toolbar);                       //不加这一句无法成功加载菜单
        toolbar.setNavigationIcon(R.drawable.back);

        timer = (Chronometer) findViewById(R.id.chronometer_activity_knowledge_ponit);
        timer.start();

        mViewPager = (ViewPager) findViewById(R.id.vp_activity_knowledge_point);
        presenter.dispatchPapersToFragment();
//        setupViewPager();
    }

//    private void setupViewPager() {
//        List<Fragment> fragments = new ArrayList<>();
//        fragments.add(new KnowledgePointFragment());
//        CommonPagerAdapter adapter = new CommonPagerAdapter(getSupportFragmentManager(), null, fragments);
//        mViewPager.setAdapter(adapter);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setupViewPager(List<PaperDetail> paperDetails) {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < paperDetails.size(); i++) {
            fragments.add(KnowledgePointFragment.newInstance(paperDetails.get(i)));
        }
        CommonPagerAdapter adapter = new CommonPagerAdapter(getSupportFragmentManager(), null, fragments);
        mViewPager.setAdapter(adapter);
    }
}
