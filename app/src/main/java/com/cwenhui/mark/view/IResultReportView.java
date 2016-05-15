package com.cwenhui.mark.view;

import com.cwenhui.mark.entity.CompletedPractice;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface IResultReportView {
    /**
     * 显示加载对话框
     */
    void showLoading();

    /**
     * 隐藏加载对话框
     */
    void hidenLoading();

    /**
     * 初始化答题卡
     * @param completedPractices    用户已完成的试题（包含用户答题情况）
     */
    void initAnswerSheet(List<CompletedPractice> completedPractices);
}
