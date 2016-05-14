package com.cwenhui.mark.entity;

import java.io.Serializable;

/**
 * Created by cwenhui on 2016.02.23
 */
public class PracticeCategory implements Serializable{
    private String categoryId;
    private String categoryName;
    private PracticeCategory parent;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public PracticeCategory getParent() {
        return parent;
    }

    public void setParent(PracticeCategory parent) {
        this.parent = parent;
    }
}
