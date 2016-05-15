package com.cwenhui.mark.model;

import com.cwenhui.mark.common.OnResponseListener;
import com.cwenhui.mark.entity.CompletedPractice;
import com.loopj.android.http.RequestParams;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface IProblemModel {
    /**
     * 调用api，向服务器提交知识点专项练习答案
     * @param api          接口
     * @param postListener 回调
     */
    void submitAnswerForExamination(String api, RequestParams params, OnResponseListener<String> postListener);

    /**
     * 调用api，获得已完成的试题（具有相同试卷标识符）
     * @param api           getCompletedPraticesByUserIdAndPaperIdentifier
     * @param getListener
     */
    void getCompletedPratices(String api, OnResponseListener<CompletedPractice> getListener);
}
