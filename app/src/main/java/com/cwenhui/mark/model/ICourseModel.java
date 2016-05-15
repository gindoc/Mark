package com.cwenhui.mark.model;

import com.cwenhui.mark.bean.CourseVideo;
import com.cwenhui.mark.common.OnResponseListener;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface ICourseModel {
    /**
     * 调用api查询所有课程
     * @param api
     * @return
     */
    public void getCourses(String api, OnResponseListener<CourseVideo> getListener);

}
