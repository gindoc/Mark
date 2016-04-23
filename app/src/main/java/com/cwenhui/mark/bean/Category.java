package com.cwenhui.mark.bean;

/**
 * IndexFragment中的分类板块的每个item对应的bean类
 * Created by cwenhui on 2016.02.23
 */
public class Category {
    private String catName;
    private int catImg;

    public Category(String catName, int catImg) {
        this.catName = catName;
        this.catImg = catImg;
    }

    public int getCatImg() {
        return catImg;
    }

    public void setCatImg(int catImg) {
        this.catImg = catImg;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
