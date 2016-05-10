package com.cwenhui.mark.entity;

import java.io.Serializable;

/**
 * Created by cwenhui on 2016.02.23
 */
public class Paper implements Serializable{
    private String paperId;
    private String paperName;           //数组专项练习等
    private boolean paperState;
    private Course course;

    public Paper() {
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public boolean getPaperState() {
        return paperState;
    }

    public void setPaperState(boolean paperState) {
        this.paperState = paperState;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
