package com.cwenhui.mark.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.cwenhui.mark.R;
import com.cwenhui.mark.adapter.CommonExpandableListAdapter;
import com.cwenhui.mark.bean.Practice;
import com.cwenhui.mark.presenter.SpecialPraticePresenter;
import com.cwenhui.mark.utils.ViewHolder;
import com.cwenhui.mark.view.ISpecialPracticeView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class SpecialPracticeActivity extends AppCompatActivity implements ISpecialPracticeView {
    private static final String TAG = "SpecialPracticeActivity";
    private Toolbar toolbar;
    private ExpandableListView listView;
    private CommonExpandableListAdapter adapter;
    private SpecialPraticePresenter presenter;
    private SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_practice);
        initView();
    }

    private void initView() {
        presenter = new SpecialPraticePresenter(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar_activity_special_practice);
        toolbar.setTitle("知识点专题练习");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);

        swipe = (SwipeRefreshLayout) findViewById(R.id.swip_activity_special_practice);
        listView = (ExpandableListView) findViewById(R.id.expandablelv_activity_special_practice);
        listView.setGroupIndicator(null);
        listView.setChildIndicator(null);
        presenter.initSpecialPracticeList();
    }

    @Override
    public void initQuestionList(HashMap<String, List<Practice>> pratices) {
        adapter = new CommonExpandableListAdapter(this, R.layout.item_parent_activity_special_practice,
                R.layout.item_child_activity_special_practice, pratices) {

            @Override
            protected void convertToParent(ViewHolder holder, String s, boolean isExpanded) {
                TextView parentType = holder.getView(R.id.tv_item_parent_type);
                View linkLine = holder.getView(R.id.view_item_parent_link_line);
                parentType.setText("    "+s);
                if (!isExpanded) {
                    Drawable drawable = getResources().getDrawable(R.drawable.arror_down);
                    //必须设置图片大小，否则不显示
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    parentType.setCompoundDrawables(drawable,null,null, null);
                    linkLine.setVisibility(View.GONE);
                } else {
                    Drawable drawable = getResources().getDrawable(R.drawable.arror_up);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    parentType.setCompoundDrawables(drawable,null,null, null);
                    linkLine.setVisibility(View.VISIBLE);
                }
            }

            @Override
            protected void convertToChild(ViewHolder holder, Practice child, boolean isLastChild) {
                holder.setText(R.id.tv_item_child_question_name, "     " + child.getPracticeName())
                    .setText(R.id.tv_item_child_question_cnt, "共" + child.getQuesCnt() + "题")
                    .setText(R.id.tv_item_child_doned_cnt, "已练习" + child.getDoneCnt() + "题");
                if (child.getAccuracy() != 0) {
                    holder.setText(R.id.tv_item_child_accuracy, "正确率" + child.getAccuracy() + "%")
                            .setVisibility(R.id.tv_item_child_accuracy, View.VISIBLE)
                            .setVisibility(R.id.view_item_child_should_hide, View.VISIBLE);
                }else{
                    holder.setVisibility(R.id.tv_item_child_accuracy, View.GONE)
                            .setVisibility(R.id.view_item_child_should_hide, View.GONE);
                }
                if (isLastChild) {
                    holder.setVisibility(R.id.view_item_child_link_line, View.GONE);
                }else{
                    holder.setVisibility(R.id.view_item_child_link_line, View.VISIBLE);
                }
            }
        };
        listView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_special_practice, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.special_practice_notice:
                Snackbar.make(toolbar, "no news", Snackbar.LENGTH_SHORT).show();
        }
        return true;

    }
}
