package com.cwenhui.mark.presenter;

import android.os.Handler;

import com.cwenhui.mark.bean.Practice;
import com.cwenhui.mark.common.OnResponseListener;
import com.cwenhui.mark.model.ICompanyAllModel;
import com.cwenhui.mark.model.ISpecialPracticeModel;
import com.cwenhui.mark.model.impl.SpecialPracticeModel;
import com.cwenhui.mark.view.ISpecialPracticeView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cwenhui on 2016.02.23
 */
public class SpecialPraticePresenter {
    private ISpecialPracticeView practiceView;
    private ISpecialPracticeModel practiceModel;
    private Handler mHandler = new Handler();

    public SpecialPraticePresenter(ISpecialPracticeView practiceView) {
        this.practiceView = practiceView;
        this.practiceModel = new SpecialPracticeModel();
    }

    /**
     * 初始化专项练习列表
     */
    public void initSpecialPracticeList() {
        practiceModel.getPratices(null, new OnResponseListener<Practice>() {
            @Override
            public void onSuccess(final Map<String, List<Practice>> ResultSet) {
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        practiceView.initQuestionList((HashMap<String, List<Practice>>) ResultSet);
                        practiceView.hideLoading();
                    }
                });
            }
        });
    }

    /**
     * 刷新专项练习列表
     * @param direction
     */
    public void reflesh(final int direction) {
        practiceModel.refleshAllSpecialSubjects(null, direction, new OnResponseListener<Practice>() {
            @Override
            public void onSuccess(final Map<String, List<Practice>> ResultSet) {
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        if (direction == ICompanyAllModel.PULL_DOWN){
                            practiceView.initQuestionList((HashMap<String, List<Practice>>) ResultSet);
                        }
                        practiceView.hideLoading();
                    }
                });
            }
        });
    }

}
