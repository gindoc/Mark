package com.cwenhui.mark.model;

import com.cwenhui.mark.common.OnGetListener;
import com.cwenhui.mark.entity.PaperDetail;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface IKnowledgePointModel {

    /**
     * 调用api获得试卷
     * @param api               getPaperDetailByPaperId
     * @param getListener       callBack
     */
    void getPaperDetail(String api, OnGetListener<PaperDetail> getListener);
}
