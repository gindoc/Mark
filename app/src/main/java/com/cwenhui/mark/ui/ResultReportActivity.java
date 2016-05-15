package com.cwenhui.mark.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cwenhui.mark.R;
import com.cwenhui.mark.common.CommonRecyclerViewAdapter;
import com.cwenhui.mark.common.CommondRecyclerViewHolder;
import com.cwenhui.mark.entity.CompletedPractice;
import com.cwenhui.mark.presenter.ProblemPresenter;
import com.cwenhui.mark.view.IResultReportView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class ResultReportActivity extends AppCompatActivity implements IResultReportView, View.OnClickListener {
    private TextView tvCategory;
    private TextView tvElapsed;
    private TextView tvAccuracy;
    private RecyclerView rvAnswer;
    private CommonRecyclerViewAdapter<CompletedPractice> adapter;
    private List<CompletedPractice> completedPractices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_report);
        initData();
        initView();
    }

    private void initData() {
        ProblemPresenter presenter = new ProblemPresenter();        //借用ProblemPresenter,不另写一个presenter了
        presenter.initAnswerSheet(this);
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_activity_result_report);
        toolbar.setTitle("结果报告");
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.back));

        tvCategory = (TextView) findViewById(R.id.tv_activity_result_report_cat);
        tvAccuracy = (TextView) findViewById(R.id.tv_activity_result_report_accuracy);
        tvElapsed = (TextView) findViewById(R.id.tv_activity_result_report_elapsed);
        rvAnswer = (RecyclerView) findViewById(R.id.rv_activity_result_report);

        LinearLayout parseError = (LinearLayout) findViewById(R.id.ll_activity_result_report_error);
        LinearLayout parseAll = (LinearLayout) findViewById(R.id.ll_activity_result_report_all);
        parseError.setOnClickListener(this);
        parseAll.setOnClickListener(this);
    }

    @Override
    public void showLoading() {
        //显示一个提示框
        Toast.makeText(this, "数据加载中...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hidenLoading() {
        Toast.makeText(this, "数据加载完成", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initAnswerSheet(List<CompletedPractice> completedPractices) {
        this.completedPractices = completedPractices;
        adapter = new CommonRecyclerViewAdapter<CompletedPractice>(this, R.layout.item_fragment_answer_sheet, completedPractices) {
            @Override
            public void convert(CommondRecyclerViewHolder holder, CompletedPractice cp) {
                holder.setText(R.id.tv_item_fragment_answer_sheet, holder.getLayoutPosition() + 1 + "");
                Drawable drawable;
                if (cp.isRight()) {
                    drawable = ContextCompat.getDrawable(ResultReportActivity.this,
                            R.drawable.choice_question_option_selected);
                } else {
                    drawable = ContextCompat.getDrawable(ResultReportActivity.this,
                            R.drawable.choice_question_option_wrong);
                }
                holder.getView(R.id.tv_item_fragment_answer_sheet).setBackground(drawable);
            }
        };
        rvAnswer.setAdapter(adapter);
        rvAnswer.setLayoutManager(new GridLayoutManager(this, 5));
        rvAnswer.setHasFixedSize(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(ResultReportActivity.this, ParseActivity.class);
        switch (v.getId()) {
            case R.id.ll_activity_result_report_error:
                Toast.makeText(ResultReportActivity.this, "click", Toast.LENGTH_SHORT).show();
                List<CompletedPractice> errors = new ArrayList<>();
                for (int i = 0; i < completedPractices.size(); i++) {
                    if (!completedPractices.get(i).isRight()) {
                        errors.add(completedPractices.get(i));
                    }
                }
                intent.putParcelableArrayListExtra(ParseActivity.TAG,
                        (ArrayList<? extends Parcelable>) errors);
                break;
            case R.id.ll_activity_result_report_all:
                intent.putParcelableArrayListExtra(ParseActivity.TAG,
                        (ArrayList<? extends Parcelable>) completedPractices);
                break;
        }
        startActivity(intent);
        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
    }
}
