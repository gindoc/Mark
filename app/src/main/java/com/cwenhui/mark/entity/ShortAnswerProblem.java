package com.cwenhui.mark.entity;

import java.io.Serializable;

/**
 * Created by cwenhui on 2016.02.23
 */
public class ShortAnswerProblem implements Serializable {
    private String saId;
    private String saTitle;
    private String saAnswer;
    private Course course;

    public ShortAnswerProblem() {
    }

    public String getSaId() {
        return saId;
    }

    public void setSaId(String saId) {
        this.saId = saId;
    }

    public String getSaTitle() {
        return saTitle;
    }

    public void setSaTitle(String saTitle) {
        this.saTitle = saTitle;
    }

    public String getSaAnswer() {
        return saAnswer;
    }

    public void setSaAnswer(String saAnswer) {
        this.saAnswer = saAnswer;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
