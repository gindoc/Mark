package com.cwenhui.mark.presenter;

import android.os.Handler;

import com.cwenhui.mark.bean.Discuss;
import com.cwenhui.mark.common.OnGetListener;
import com.cwenhui.mark.common.RVScrollListener;
import com.cwenhui.mark.configs.Constant;
import com.cwenhui.mark.model.IDiscussModel;
import com.cwenhui.mark.model.impl.DiscussModel;
import com.cwenhui.mark.view.IDiscussView;

import java.util.Collection;
import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class DiscussPresenter implements IPresenter {
    private IDiscussView discussView;
    private IDiscussModel discussModel;
    private Handler mHandler = new Handler();

    public DiscussPresenter(IDiscussView discussView) {
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
     * 刷新讨论区
     * @param direction
     */
    @Override
    public void reflesh(final int direction) {
        discussModel.reflesh(null, direction, new OnGetListener<Discuss>(){
            @Override
            public void onSuccess(final Collection<Discuss> discusses) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (direction == Constant.PULL_DOWN) {
                            discussView.initDiscussList((List<Discuss>) discusses);
                        } else {
                            discussView.refleshDiscussList((List<Discuss>) discusses);
                        }
                        discussView.hideLoading();
                        synchronized ((Object) RVScrollListener.isLoading) {
                            RVScrollListener.isLoading = false;
                        }
                    }
                });
            }
        });
    }



//    /**
//     * 测试，使用EventBus的方式获得数据来初始化讨论区列表
//     */
//    public void initDiscussListForEventBus(int plate) {
//        discussModel.initDisgussList(null);
//    }
//
//
//    /**
//     * 测试，使用EventBus的方式刷新讨论区
//     * @param direction
//     */
//    public void refleshForEventBus(int plate, int direction) {
////        initDiscussListForEventBus(plate);      //暂不做刷新处理
//        discussModel.reflesh(null, direction);
//    }
}
