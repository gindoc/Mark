package com.cwenhui.mark.model;

import com.cwenhui.mark.common.OnGetListener;
import com.cwenhui.mark.entity.Practice;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface IExaminationModel {
    /**
     * 调用api获得试卷
     * @param api               getPractices(分页)
     * @param getListener       callBack
     */
    void getPaperDetail(String api, OnGetListener<Practice> getListener);
}
