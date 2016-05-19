package com.cwenhui.mark.entity;

import java.io.Serializable;

/**
 * Created by cwenhui on 2016.02.23
 */
public class Practice implements Serializable{
    private String praticeId;
    private String praticeTitle;
    private String praticeOptions;
    private String praticeAnswer;
    private int praticeType;
    private PracticeCategory category;
    private User user;

    public String getPraticeId() {
        return praticeId;
    }

    public void setPraticeId(String praticeId) {
        this.praticeId = praticeId;
    }

    public String getPraticeTitle() {
        return praticeTitle;
    }

    public void setPraticeTitle(String praticeTitle) {
        this.praticeTitle = praticeTitle;
    }

    public String getPraticeOptions() {
        return praticeOptions;
    }

    public void setPraticeOptions(String praticeOptions) {
        this.praticeOptions = praticeOptions;
    }

    public String getPraticeAnswer() {
        return praticeAnswer;
    }

    public void setPraticeAnswer(String praticeAnswer) {
        this.praticeAnswer = praticeAnswer;
    }

    public int getPraticeType() {
        return praticeType;
    }

    public void setPraticeType(int praticeType) {
        this.praticeType = praticeType;
    }

    public PracticeCategory getCategory() {
        return category;
    }

    public void setCategory(PracticeCategory category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
