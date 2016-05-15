package com.cwenhui.mark.model.impl;

import com.cwenhui.mark.bean.Discuss;
import com.cwenhui.mark.common.OnResponseListener;
import com.cwenhui.mark.model.IPublishTopicModel;

/**
 * Created by cwenhui on 2016.02.23
 */
public class PublishTopicModel implements IPublishTopicModel {

    @Override
    public void publishTopic(Discuss discuss, final OnResponseListener<String> getListener) {
        //进行网络请求...
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getListener.onSuccess("success");
            }
        }).start();
    }
}
