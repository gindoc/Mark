package com.cwenhui.mark.view;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface IPublishTopicView {

    void toPlatesActivity();

    /**
     * 显示（Toast）提示信息
     * @param tips  提示信息
     */
    void ShowTips(String tips);

    /**
     * 获得板块信息
     * @return
     */
    String getPlate();

    /**
     * 获得话题标题
     * @return
     */
    CharSequence getTopicTitle();

    /**
     * 获得话题内容
     * @return
     */
    CharSequence getContent();

    /**
     * 添加板块
     * @param plate
     */
    void addPlate(String plate);
}
