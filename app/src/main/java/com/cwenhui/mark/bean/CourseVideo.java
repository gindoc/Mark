package com.cwenhui.mark.bean;

/**
 * Created by cwenhui on 2016.02.23
 */
public class CourseVideo {
    private String courseImg;
    private String courseName;
    private int courseNum;
    private int participants;

    public CourseVideo(String courseImg, String courseName, int courseNum, int participants) {
        this.courseImg = courseImg;
        this.courseName = courseName;
        this.courseNum = courseNum;
        this.participants = participants;
    }

    public String getCourseImg() {
        return courseImg;
    }

    public void setCourseImg(String courseImg) {
        this.courseImg = courseImg;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(int courseNum) {
        this.courseNum = courseNum;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }
}
