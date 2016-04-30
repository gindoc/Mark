package com.cwenhui.mark.model;

import com.cwenhui.mark.bean.Practice;
import com.cwenhui.mark.utils.OnGetListener;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface ISpecialPracticeModel {
    /**
     * 调用api查询专题
     * @param api
     * @param getListener
     */
    public void getPratices(String api, OnGetListener<Practice> getListener);
}