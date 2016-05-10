package com.cwenhui.mark.presenter;

import android.os.Handler;

import com.cwenhui.mark.common.OnGetListener;
import com.cwenhui.mark.entity.PaperDetail;
import com.cwenhui.mark.model.IKnowledgePointModel;
import com.cwenhui.mark.model.impl.KnowledgePointModel;
import com.cwenhui.mark.view.IKnowledgePointView;

import java.util.Collection;
import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class KnowledgePointPresenter {
    private IKnowledgePointView view;
    private IKnowledgePointModel model;
    private Handler mHandler = new Handler();

    public KnowledgePointPresenter(IKnowledgePointView view) {
        this.view = view;
        this.model = new KnowledgePointModel();
    }

    /**
     * 初始化KnowledgePointFragment，同时分发试题（PaperDetail）给每一个KnowledgePointFragment
     * 同时为ViewPager设置适配器
     */
    public void dispatchPapersToFragment() {
        //显示提示框（组卷中）。。。
        model.getPaperDetail(null, new OnGetListener<PaperDetail>() {
            @Override
            public void onSuccess(final Collection<PaperDetail> paperDetails) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.setupViewPager((List<PaperDetail>) paperDetails);
                    }
                });
            }
        });
    }
}
