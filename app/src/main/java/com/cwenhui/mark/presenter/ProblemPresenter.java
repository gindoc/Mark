package com.cwenhui.mark.presenter;

import android.os.Handler;

import com.cwenhui.mark.common.MyEvent;
import com.cwenhui.mark.common.OnResponseListener;
import com.cwenhui.mark.entity.CompletedPractice;
import com.cwenhui.mark.model.IProblemModel;
import com.cwenhui.mark.model.impl.ProblemModel;
import com.cwenhui.mark.ui.ExaminationActivity;
import com.cwenhui.mark.view.IResultReportView;

import java.util.Collection;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by cwenhui on 2016.02.23
 */
public class ProblemPresenter {
    private Handler mHandler = new Handler();

    /**
     * 调用模型层向服务器发送用户答案
     */
    public void submitAnswerForExamination(final int index) {
        IProblemModel problemModel = new ProblemModel();
        problemModel.submitAnswerForExamination(null, null, new OnResponseListener<String>() {
            @Override
            public void onSuccess(String s) {
                if (s.equals("success")) {
                    MyEvent event = new MyEvent();
                    event.eventType = ExaminationActivity.SUBMIT_SUCCESS;
                    event.eventData = index;
                    EventBus.getDefault().post(event);
                }
            }
        });
    }

    public void initAnswerSheet(final IResultReportView view) {
        IProblemModel problemModel = new ProblemModel();
        view.showLoading();
        problemModel.getCompletedPratices(null, new OnResponseListener<CompletedPractice>() {
            @Override
            public void onSuccess(final Collection<CompletedPractice> cps) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.initAnswerSheet((List<CompletedPractice>) cps);
                    }
                });
            }
        });
        view.hidenLoading();
    }
}
