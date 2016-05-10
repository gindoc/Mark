package com.cwenhui.mark.entity;

import java.io.Serializable;

/**
 * Created by cwenhui on 2016.02.23
 */
public class SingleProblem implements Serializable {
    private String spId;
    private String spTitle;
    private String spAnswerA;
    private String spAnswerB;
    private String spAnswerC;
    private String spAnswerD;
    private String spAnswer;
    private Course course;

    public SingleProblem() {
    }

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId;
    }

    public String getSpTitle() {
        return spTitle;
    }

    public void setSpTitle(String spTitle) {
        this.spTitle = spTitle;
    }

    public String getSpAnswerA() {
        return spAnswerA;
    }

    public void setSpAnswerA(String spAnswerA) {
        this.spAnswerA = spAnswerA;
    }

    public String getSpAnswerB() {
        return spAnswerB;
    }

    public void setSpAnswerB(String spAnswerB) {
        this.spAnswerB = spAnswerB;
    }

    public String getSpAnswerC() {
        return spAnswerC;
    }

    public void setSpAnswerC(String spAnswerC) {
        this.spAnswerC = spAnswerC;
    }

    public String getSpAnswerD() {
        return spAnswerD;
    }

    public void setSpAnswerD(String spAnswerD) {
        this.spAnswerD = spAnswerD;
    }

    public String getSpAnswer() {
        return spAnswer;
    }

    public void setSpAnswer(String spAnswer) {
        this.spAnswer = spAnswer;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
