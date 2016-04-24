package com.cwenhui.mark.view;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface IProfileView {
    /**
     * 初始化用户基本信息栏
     * @param image 用户头像url
     * @param name 用户名
     * @param eduInfor 用户教育背景
     */
    public void setSelfMessage(String image, String name, String eduInfor);
}
