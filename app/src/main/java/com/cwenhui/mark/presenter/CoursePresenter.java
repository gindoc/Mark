package com.cwenhui.mark.presenter;

import android.os.Handler;

import com.cwenhui.mark.bean.Course;
import com.cwenhui.mark.common.OnGetListener;
import com.cwenhui.mark.model.ICourseModel;
import com.cwenhui.mark.model.impl.CourseModel;
import com.cwenhui.mark.view.ICourseView;

import java.util.Collection;
import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class CoursePresenter {
    private ICourseView courseView;
    private ICourseModel courseModel;
    private Handler mHandler = new Handler();

    public CoursePresenter(ICourseView courseView) {
        this.courseView = courseView;
        this.courseModel = new CourseModel();
    }

    /**
     * 初始化课程列表
     */
    public void initCoursesList() {
        courseModel.getCourses(null, new OnGetListener<Course>(){
            @Override
            public void onSuccess(final Collection<Course> courses) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        courseView.initCoursesList((List<Course>) courses);
                        courseView.hideLoading();
                    }
                });
            }
        });
    }

    /**
     * 刷新课程列表
     */
    public void reflesh() {
        initCoursesList();      //此处不做刷新处理了，和CompanyAllFragment的差不多，所以用initCoursesList代替模拟刷新
    }
}
