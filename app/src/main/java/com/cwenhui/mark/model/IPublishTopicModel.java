package com.cwenhui.mark.model;

import com.cwenhui.mark.bean.Discuss;
import com.cwenhui.mark.common.OnGetListener;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface IPublishTopicModel {
    /**
     * 发表话题
     * @param discuss 话题（其实Discuss应该改名为Topic会好点）
     */
    void publishTopic(Discuss discuss, OnGetListener<String> getListener);
}
