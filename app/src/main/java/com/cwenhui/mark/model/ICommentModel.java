package com.cwenhui.mark.model;

import com.cwenhui.mark.common.OnResponseListener;
import com.cwenhui.mark.entity.Comment;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface ICommentModel {

    /**
     * 调用api获取评论
     * @param api           接口
     * @param getListener   回调
     */
    void getComments(String api, OnResponseListener<Comment> getListener);

    /**
     * 调用api获取更多评论
     * @param api           接口
     * @param getListener   回调
     */
    void loadMoreComments(String api, OnResponseListener<Comment> getListener);
}
