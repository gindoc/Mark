package com.cwenhui.mark.presenter;

import android.os.Handler;

import com.cwenhui.mark.bean.Discuss;
import com.cwenhui.mark.common.OnResponseListener;
import com.cwenhui.mark.common.TextUtils;
import com.cwenhui.mark.model.IPublishTopicModel;
import com.cwenhui.mark.model.impl.PublishTopicModel;
import com.cwenhui.mark.view.IPublishTopicView;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class PublishTopicPresenter {
    private IPublishTopicView view;
    private IPublishTopicModel model;
    private Handler mHandler = new Handler();

    public PublishTopicPresenter(IPublishTopicView view) {
        this.view = view;
        this.model = new PublishTopicModel();
    }

    /**
     * 检测信息填写是否完整
     */
    public void checkMessage(final List<String> tags) {
        if (view.getPlate() == null) {
            view.ShowTips("设置板块、标签");
            view.toPlatesActivity();
            return;
        }
        if (TextUtils.isEmpty(view.getTopicTitle())) {
            view.ShowTips("标题不能为空");
            return;
        }
        if (TextUtils.isEmpty(view.getContent())) {
            view.ShowTips("内容不能为空");
            return;
        }
        Discuss discuss = new Discuss();
        discuss.setTags(tags);
        model.publishTopic(discuss, new OnResponseListener<String>() {
            @Override
            public void onSuccess(String s) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.ShowTips("发表成功");
                    }
                });
            }
        });
    }

}
