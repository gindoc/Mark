package com.cwenhui.mark.presenter;

import android.os.Handler;

import com.cwenhui.mark.common.OnResponseListener;
import com.cwenhui.mark.entity.Practice;
import com.cwenhui.mark.model.IExaminationModel;
import com.cwenhui.mark.model.impl.ExaminationModel;
import com.cwenhui.mark.view.IExaminationView;

import java.util.Collection;
import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class ExaminationPresenter {
    private IExaminationView view;
    private IExaminationModel model;
    private Handler handler = new Handler();

    public ExaminationPresenter(IExaminationView view) {
        this.view = view;
        model = new ExaminationModel();
    }

    /**
     * 初始化KnowledgePointFragment，同时分发试题（PaperDetail）给每一个KnowledgePointFragment
     * 同时为ViewPager设置适配器
     */
    public void dispatchPapersToFragment() {
        //显示提示框（组卷中）。。。
        model.getPaperDetail(null, new OnResponseListener<Practice>() {
            @Override
            public void onSuccess(final Collection<Practice> practices) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.setupViewPager((List<Practice>) practices);
                    }
                });
            }
        });
    }
}
