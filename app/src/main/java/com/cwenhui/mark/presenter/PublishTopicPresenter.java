package com.cwenhui.mark.presenter;

import com.cwenhui.mark.common.OnGetListener;
import com.cwenhui.mark.model.IPublishTopicModel;
import com.cwenhui.mark.model.impl.PublishTopicModel;
import com.cwenhui.mark.view.IPublishTopicView;

/**
 * Created by cwenhui on 2016.02.23
 */
public class PublishTopicPresenter {
    IPublishTopicView view;
    IPublishTopicModel model;

    public PublishTopicPresenter(IPublishTopicView view) {
        this.view = view;
        this.model = new PublishTopicModel();
    }

    public void checkMessage() {
        if(view.getPlate() == null){
            view.ShowTips("设置板块、标签");
            view.toPlatesActivity();
            return;
        }
        if (view.getTopicTitle() == null) {
            view.ShowTips("标题不能为空");
            return;
        }
        if (view.getContent() == null) {
            view.ShowTips("内容不能为空");
            return;
        }
        model.publishTopic(null, new OnGetListener<String>(){
            @Override
            public void onSuccess(String s) {
                view.ShowTips("发表成功");
            }
        });
    }
}
