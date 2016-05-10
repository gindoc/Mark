package com.cwenhui.mark.entity;

import java.io.Serializable;

/**
 * Created by cwenhui on 2016.02.23
 */
public class FillBlankProblem implements Serializable {
    private String fbId;
    private String fbFrontTitle;
    private String fbBackTitle;
    private String fbAnswer;
    private Course course;

    public FillBlankProblem() {
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getFbFrontTitle() {
        return fbFrontTitle;
    }

    public void setFbFrontTitle(String fbFrontTitle) {
        this.fbFrontTitle = fbFrontTitle;
    }

    public String getFbBackTitle() {
        return fbBackTitle;
    }

    public void setFbBackTitle(String fbBackTitle) {
        this.fbBackTitle = fbBackTitle;
    }

    public String getFbAnswer() {
        return fbAnswer;
    }

    public void setFbAnswer(String fbAnswer) {
        this.fbAnswer = fbAnswer;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
