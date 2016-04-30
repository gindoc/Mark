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

}
