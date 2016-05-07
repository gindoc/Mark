package com.cwenhui.mark.view;

import com.cwenhui.mark.bean.Discuss;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface IDiscussView {
    /**
     * 最新
     */
    public static final String NEWEST = "最新";

    /**
     * 最热
     */
    public static final String HOTEST = "最热";

    /**
     * 精华
     */
    public static final String CREAM = "精华";

    /**
     * 初始化讨论区列表
     */
    void initDiscussList(List<Discuss> discusses);

    /**
     * 刷新讨论区列表
     * @param discusses
     */
    void refleshDiscussList(List<Discuss> discusses);

    /**
     * 切换类型
     * @param type 类型
     */
    void switchType(String type);

    /**
     * 隐藏加载进度条
     */
    void hideLoading();

}
