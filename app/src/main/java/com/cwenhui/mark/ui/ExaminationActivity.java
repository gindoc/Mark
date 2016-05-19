package com.cwenhui.mark.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Toast;

import com.cwenhui.mark.R;
import com.cwenhui.mark.common.CommonPagerAdapter;
import com.cwenhui.mark.common.MyEvent;
import com.cwenhui.mark.configs.Constant;
import com.cwenhui.mark.entity.Practice;
import com.cwenhui.mark.fragment.AnswerSheetFragment;
import com.cwenhui.mark.fragment.FillBlankProblemFragment;
import com.cwenhui.mark.fragment.MultipleProblemFragment;
import com.cwenhui.mark.fragment.SingleProblemFragment;
import com.cwenhui.mark.presenter.ExaminationPresenter;
import com.cwenhui.mark.view.IExaminationView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by cwenhui on 2016.02.23
 */
public class ExaminationActivity extends AppCompatActivity implements IExaminationView,
        ViewPager.OnPageChangeListener, View.OnClickListener {
    public static final String TAG = "ExaminationActivity";
    public static final String FROM_ANSWERSHEET = "fromAnswerSheet";
    public static final String SUBMIT_SUCCESS = "submitSuccess";
    private ExaminationPresenter presenter;
    private ViewPager mViewPager;
    private Button preQuestion, nextQuestion;
    private Chronometer timer;
    private Set<Integer> hasSendAnswerFragmt = new HashSet<>();
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examination);
        initView();
    }

    private void initView() {
        EventBus.getDefault().register(this);

        presenter = new ExaminationPresenter(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_activity_examination);
        toolbar.setTitle("知识点专项练习");
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        setSupportActionBar(toolbar);                       //不加这一句无法成功加载菜单
        toolbar.setNavigationIcon(R.drawable.back);

        ImageView completionState = (ImageView) findViewById(R.id.iv_activity_examination_completion_state);
        preQuestion = (Button) findViewById(R.id.btn_activity_examination_pre);
        nextQuestion = (Button) findViewById(R.id.btn_activity_examination_next);
        completionState.setOnClickListener(this);
        preQuestion.setOnClickListener(this);
        nextQuestion.setOnClickListener(this);

        timer = (Chronometer) findViewById(R.id.chronometer_activity_examination);
        timer.start();

        mViewPager = (ViewPager) findViewById(R.id.vp_activity_examination);
        mViewPager.addOnPageChangeListener(this);
        presenter.dispatchPapersToFragment();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int pos = mViewPager.getCurrentItem();
        switch (v.getId()) {
            case R.id.iv_activity_examination_completion_state:
                mViewPager.setCurrentItem(10);
                break;
            case R.id.btn_activity_examination_next:
                mViewPager.setCurrentItem(pos + 1);
                break;
            case R.id.btn_activity_examination_pre:
                if (pos == 10) {
                    v.setClickable(false);
                    timer.stop();
                    MyEvent myEvent = new MyEvent();
                    myEvent.eventType = ExaminationActivity.TAG;
                    EventBus.getDefault().post(myEvent);
                } else if (pos != 0) {
                    mViewPager.setCurrentItem(pos - 1);
                }
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            preQuestion.setText("无");
        } else if (position == 10) {
            preQuestion.setText("提交并查看结果");
            nextQuestion.setVisibility(View.GONE);
        } else {
            preQuestion.setText("上一题");
            nextQuestion.setVisibility(View.VISIBLE);
        }
        MyEvent myEvent = new MyEvent();
        myEvent.eventType = FillBlankProblemFragment.TAG;       //发送给FillBlankProblemFragment
        myEvent.eventData = position;                           //发送当前页面所处位置
        EventBus.getDefault().post(myEvent);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}

    @Override
    public void setupViewPager(List<Practice> practices) {
        //此处还应创建一个时间作为试卷的标识符，传给各个页面，用于提交答案给服务器时做标识，表明是同一套试卷
        //。。。。
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < practices.size(); i++) {
            switch (practices.get(i).getPraticeType()) {
                case Constant.SINGLE_PROBLEM:
                    fragments.add(SingleProblemFragment.newInstance(i, practices.get(i)));
                    break;
                case Constant.MULTIPUL_PROBLEM:
                    fragments.add(MultipleProblemFragment.newInstance(i, practices.get(i)));
                    break;
                case Constant.FILL_BLANK_PROBLEM:
                    fragments.add(FillBlankProblemFragment.newInstance(i, practices.get(i)));
                    break;

            }
        }
        fragments.add(new AnswerSheetFragment());
        CommonPagerAdapter adapter = new CommonPagerAdapter(getSupportFragmentManager(), null, fragments);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(11);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEventMainThread(final MyEvent myEvent) {
        if (myEvent.eventType == FROM_ANSWERSHEET) {
            mViewPager.setCurrentItem((Integer) myEvent.eventData);
        }
        if (myEvent.eventType == SUBMIT_SUCCESS) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    hasSendAnswerFragmt.add((Integer) myEvent.eventData);
                    if (hasSendAnswerFragmt.size() == 10) {
                        //跳转到结果报告...同时把试卷的标识也传过去
                        Toast.makeText(ExaminationActivity.this, "跳转到结果报告", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ExaminationActivity.this, ResultReportActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
                        finish();
                    }
                }
            });

        }
    }

}
