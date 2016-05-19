package com.cwenhui.mark.entity;

import java.io.Serializable;

/**
 * Created by cwenhui on 2016.02.23
 */
public class User implements Serializable{
    private String userId;
    private String username;
    private String password;
    private String userImg;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }
}
