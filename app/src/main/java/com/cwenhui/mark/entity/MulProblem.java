package com.cwenhui.mark.entity;

import java.io.Serializable;

/**
 * Created by cwenhui on 2016.02.23
 */
public class MulProblem implements Serializable {
    private String mpId;
    private String mpTitle;
    private String mpAnswerA;
    private String mpAnswerB;
    private String mpAnswerC;
    private String mpAnswerD;
    private String mpAnswer;
    private Course course;

    public MulProblem() {
    }

    public String getMpId() {
        return mpId;
    }

    public void setMpId(String mpId) {
        this.mpId = mpId;
    }

    public String getMpTitle() {
        return mpTitle;
    }

    public void setMpTitle(String mpTitle) {
        this.mpTitle = mpTitle;
    }

    public String getMpAnswerA() {
        return mpAnswerA;
    }

    public void setMpAnswerA(String mpAnswerA) {
        this.mpAnswerA = mpAnswerA;
    }

    public String getMpAnswerB() {
        return mpAnswerB;
    }

    public void setMpAnswerB(String mpAnswerB) {
        this.mpAnswerB = mpAnswerB;
    }

    public String getMpAnswerC() {
        return mpAnswerC;
    }

    public void setMpAnswerC(String mpAnswerC) {
        this.mpAnswerC = mpAnswerC;
    }

    public String getMpAnswerD() {
        return mpAnswerD;
    }

    public void setMpAnswerD(String mpAnswerD) {
        this.mpAnswerD = mpAnswerD;
    }

    public String getMpAnswer() {
        return mpAnswer;
    }

    public void setMpAnswer(String mpAnswer) {
        this.mpAnswer = mpAnswer;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
