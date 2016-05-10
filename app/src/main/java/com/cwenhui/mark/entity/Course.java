package com.cwenhui.mark.entity;

import java.io.Serializable;

/**
 * 数组、链表等课程
 * Created by cwenhui on 2016.02.23
 */
public class Course implements Serializable{
    private String courseId;
    private String courseName;

    public Course() {
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
