package com.cwenhui.mark.model;

import com.cwenhui.mark.bean.Practice;
import com.cwenhui.mark.common.OnGetListener;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface ISpecialPracticeModel {
    public static final int PULL_DOWN = 1;
    public static final int PULL_UP = 2;

    /**
     * 调用api查询专题
     * @param api
     * @param getListener
     */
    public void getPratices(String api, OnGetListener<Practice> getListener);

    public void refleshAllSpecialSubjects(String api, int direction,
                                          OnGetListener<Practice> getListener);
}