package com.cwenhui.mark.view;

import com.cwenhui.mark.bean.Discuss;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface IDiscussView {
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
     * 隐藏加载进度条
     */
    void hideLoading();

}
