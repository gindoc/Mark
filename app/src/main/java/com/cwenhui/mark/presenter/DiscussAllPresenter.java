package com.cwenhui.mark.presenter;

import android.os.Handler;

import com.cwenhui.mark.bean.Discuss;
import com.cwenhui.mark.common.OnGetListener;
import com.cwenhui.mark.common.RVScrollListener;
import com.cwenhui.mark.model.IDiscussModel;
import com.cwenhui.mark.model.impl.DiscussModel;
import com.cwenhui.mark.view.IDiscussView;

import java.util.Collection;
import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class DiscussAllPresenter implements IPresenter {
    private IDiscussView discussView;
    private IDiscussModel discussModel;
    private Handler mHandler = new Handler();

    public DiscussAllPresenter(IDiscussView discussView) {
        this.discussView = discussView;
        this.discussModel = new DiscussModel();
    }

    /**
     * 初始化讨论区列表
     */
    public void initDiscussList() {
        discussModel.initDisgussList(null, new OnGetListener<Discuss>(){
            @Override
            public void onSuccess(final Collection<Discuss> discusses) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        discussView.initDiscussList((List<Discuss>) discusses);
                        discussView.hideLoading();
                        synchronized ((Object) RVScrollListener.isLoading) {
                            RVScrollListener.isLoading = false;
                        }
                    }
                });
            }
        });

    }

    /**
     * 测试，使用EventBus的方式获得数据来初始化讨论区列表
     */
    public void initDiscussListForEventBus() {
        discussModel.initDisgussList(null);
    }

    /**
     * 刷新讨论区
     * @param direction
     */
    @Override
    public void reflesh(int direction) {
        initDiscussList();                  //暂不做刷新处理
    }

    /**
     * 测试，使用EventBus的方式刷新讨论区
     * @param direction
     */
    public void refleshForEventBus(int direction) {
        initDiscussListForEventBus();      //暂不做刷新处理
    }
}
