package com.cwenhui.mark.presenter;

import android.os.Handler;
import android.util.Log;

import com.cwenhui.mark.common.OnResponseListener;
import com.cwenhui.mark.common.ScrollViewTouchListener;
import com.cwenhui.mark.configs.Constant;
import com.cwenhui.mark.entity.Comment;
import com.cwenhui.mark.model.ICommentModel;
import com.cwenhui.mark.model.impl.CommentModel;
import com.cwenhui.mark.view.ICommentView;

import java.util.Collection;
import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class CommentPresenter implements IPresenter{
    private Handler mHandler = new Handler();
    private ICommentModel model = new CommentModel();
    private ICommentView view;

    public CommentPresenter(ICommentView view) {
        this.view = view;
    }

    /**
     * 初始化评论列表
     */
    public void initCommentList() {
        model.getComments(null, new OnResponseListener<Comment>() {
            @Override
            public void onSuccess(final Collection<Comment> comments) {
                Log.e("123", "123312");
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.initCommentList((List<Comment>) comments);
                    }
                });
            }
        });
    }

    /**
     * 上拉加载、下拉刷新评论列表
     * @param direction 方向
     */
    @Override
    public void reflesh(final int direction) {
        model.loadMoreComments(null, new OnResponseListener<Comment>() {
            @Override
            public void onSuccess(final Collection<Comment> comments) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (direction == Constant.PULL_DOWN) {
                            view.initCommentList((List<Comment>) comments);
                        } else {
                            view.loadMoreComments((List<Comment>) comments);
                        }
                        synchronized ((Object) ScrollViewTouchListener.isLoading) {
                            ScrollViewTouchListener.isLoading = false;
                        }
                    }
                });
            }
        });

    }
}
