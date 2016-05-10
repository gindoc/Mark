package com.cwenhui.mark.model.impl;

import com.cwenhui.mark.bean.CourseVideo;
import com.cwenhui.mark.common.OnGetListener;
import com.cwenhui.mark.model.ICourseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class CourseModel implements ICourseModel {
    @Override
    public void getCourses(String api, final OnGetListener<CourseVideo> getListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<CourseVideo> courses = new ArrayList<CourseVideo>();
                CourseVideo course = new CourseVideo("http://web.img.chuanke.com/course/2013-12/16/" +
                        "04b4ace29701365cb04ed4a352c4df0b.thumb.jpg","0基础学HTML语言",50,320);
                courses.add(course);
                course = new CourseVideo("http://web.img.chuanke.com/course/2016-03/21/" +
                        "c26a768e31007982ba9ec7f10c020ae1.thumb.jpg","Python语言入门课程", 12, 150);
                courses.add(course);
                course = new CourseVideo("http://web.img.chuanke.com/course/2015-12/26/" +
                        "0123128879c4529c3a7560609af01d86.thumb.jpg", "手把手教你WAMP实战", 15, 1456);
                courses.add(course);
                course = new CourseVideo("http://web.img.chuanke.com/course/2015-11/10/" +
                        "9cae000bdd48bdda61d47abe4aa3476d.thumb.jpg" , "Matlab入门与程序基础", 15, 1456);
                courses.add(course);
                course = new CourseVideo("http://web.img.chuanke.com/course/2015-11/03/" +
                        "613809b7b2b40c6a8cc1c228affab89e.thumb.jpg" ,"JSP快速入门", 50, 1320);
                courses.add(course);
                course = new CourseVideo("http://web.img.chuanke.com/course/2016-03/21/" +
                        "c26a768e31007982ba9ec7f10c020ae1.thumb.jpg","Python语言入门课程", 12, 150);
                courses.add(course);
                course = new CourseVideo("http://web.img.chuanke.com/course/2015-12/26/" +
                        "0123128879c4529c3a7560609af01d86.thumb.jpg", "手把手教你WAMP实战", 15, 1456);
                courses.add(course);
                course = new CourseVideo("http://web.img.chuanke.com/course/2015-11/10/" +
                        "9cae000bdd48bdda61d47abe4aa3476d.thumb.jpg" , "Matlab入门与程序基础", 15, 1456);
                courses.add(course);

                getListener.onSuccess(courses);
            }
        }).start();
    }
}
