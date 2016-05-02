package com.cwenhui.mark.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.cwenhui.mark.R;
import com.cwenhui.mark.bean.Course;
import com.cwenhui.mark.common.CommonRecyclerViewAdapter;
import com.cwenhui.mark.common.CommondRecyclerViewHolder;
import com.cwenhui.mark.common.ImageFirstDisplayListener;
import com.cwenhui.mark.common.ImageLoaderHelper;
import com.cwenhui.mark.configs.Constant;
import com.cwenhui.mark.presenter.CoursePresenter;
import com.cwenhui.mark.view.ICourseView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class CourseActivity extends AppCompatActivity implements ICourseView,
        SwipeRefreshLayout.OnRefreshListener{
    private CoursePresenter presenter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipe;
    private CommonRecyclerViewAdapter<Course> adapter;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;								//显示图片的各种设置
    private ImageFirstDisplayListener displayListener = new ImageFirstDisplayListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        initData();
        initView();
    }

    private void initData() {
        presenter = new CoursePresenter(this);
        options = ImageLoaderHelper.getInstance().initImageLoader(this,
                imageLoader, Constant.IMAGE_PATH, R.drawable.nowcoder_ic_launcher);
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_activity_course);
        toolbar.setTitle("课程");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);

        recyclerView = (RecyclerView) findViewById(R.id.rv_activity_course);

        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe_activity_course);
        swipe.setColorSchemeResources(R.color.swipeColor1, R.color.swipeColor2,
                R.color.swipeColor3, R.color.swipeColor4);
        swipe.setOnRefreshListener(this);
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        swipe.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                24, getResources().getDisplayMetrics()));
        swipe.setRefreshing(true);

        presenter.initCoursesList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_course, menu);
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
    public void initCoursesList(List<Course> courses) {
        adapter = new CommonRecyclerViewAdapter<Course>(this, R.layout.item_activity_course, courses) {
            @Override
            public void convert(CommondRecyclerViewHolder holder, Course course) {
                holder.setText(R.id.tv_activity_course_name, course.getCourseName())
                .setText(R.id.tv_activity_course_num, "共"+course.getCourseNum()+"章")
                .setText(R.id.tv_activity_course_ppts, course.getParticipants() + "人练习");
                imageLoader.displayImage(course.getCourseImg(),
                        (ImageView) holder.getView(R.id.iv_activity_course_logo), options, displayListener);
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void hideLoading() {
        swipe.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        presenter.reflesh();
    }
}
