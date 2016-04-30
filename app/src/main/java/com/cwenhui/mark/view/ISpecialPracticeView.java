package com.cwenhui.mark.view;

import com.cwenhui.mark.bean.Practice;

import java.util.HashMap;
import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface ISpecialPracticeView {
    /**
     * 初始化试题列表
     */
    public void initQuestionList(HashMap<String, List<Practice>> pratices);

    /**
     * 刷新试题列表
     * @param pratices
     */
    public void refleshQuestionList(HashMap<String, List<Practice>> pratices);

    /**
     * 隐藏swipeLayout加载框
     */
    public void hideLoading();

}
