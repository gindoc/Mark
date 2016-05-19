package com.cwenhui.mark.view;

import com.cwenhui.mark.entity.Comment;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface ICommentView {

    /**
     * 初始化解析列表
     * @param comments   解析
     */
    void initCommentList(List<Comment> comments);

    /**
     * 加载更多解析
     * @param comments  解析
     */
    void loadMoreComments(List<Comment> comments);
}
