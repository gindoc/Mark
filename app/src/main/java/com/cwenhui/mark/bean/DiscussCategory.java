package com.cwenhui.mark.bean;

/**
 * Created by cwenhui on 2016.02.23
 */
public class DiscussCategory {
    private int disCatId;
    private String disCatName;

    public DiscussCategory(int disCatId, String disCatName) {
        this.disCatId = disCatId;
        this.disCatName = disCatName;
    }

    public int getDisCatId() {
        return disCatId;
    }

    public void setDisCatId(int disCatId) {
        this.disCatId = disCatId;
    }

    public String getDisCatName() {
        return disCatName;
    }

    public void setDisCatName(String disCatName) {
        this.disCatName = disCatName;
    }
}
