package com.cwenhui.mark.view;

import com.cwenhui.mark.bean.CourseVideo;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface ICourseView {
    /**
     * 初始化课程列表
     * @param courses
     */
    public void initCoursesList(List<CourseVideo> courses);

    /**
     * 隐藏加载进度条
     */
    public void hideLoading();
}
