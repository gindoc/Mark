package com.cwenhui.mark.model.impl;

import com.cwenhui.mark.common.OnResponseListener;
import com.cwenhui.mark.entity.Comment;
import com.cwenhui.mark.entity.Practice;
import com.cwenhui.mark.entity.User;
import com.cwenhui.mark.model.ICommentModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class CommentModel implements ICommentModel {
    @Override
    public void getComments(String api, final OnResponseListener<Comment> getListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                User user = new User();
                user.setUsername("小海豹");
                user.setPassword("123");
                user.setUserImg("http://www.cwenhui.com/images/default_member.jpg");

                Practice practice = new Practice();

                /*List<Comment> comments = new ArrayList<Comment>();
                for (int i = 0; i < 5; i++) {
                    Comment comment = new Comment();
                    comments.add(comment);
                }*/

                Comment comment = new Comment();
                comment.setContent("我们都是好孩子");
                comment.setCreateTime(new Date());
                comment.setPractice(practice);
                comment.setUser(user);
//                comment.setCommented(comments);
                List<Comment> real = new ArrayList<Comment>();
                real.add(comment);
                getListener.onSuccess(real);
            }
        }).start();
    }

    @Override
    public void loadMoreComments(String api, final OnResponseListener<Comment> getListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                User user = new User();
                user.setUsername("小海豹");
                user.setPassword("123");
                user.setUserImg("http://www.cwenhui.com/images/default_member.jpg");

                Practice practice = new Practice();

                List<Comment> real = new ArrayList<Comment>();
                for (int i = 0; i < 5; i++) {
                    Comment comment = new Comment();
                    comment.setContent("我们都是好孩子");
                    comment.setCreateTime(new Date());
                    comment.setPractice(practice);
                    comment.setUser(user);
                    real.add(comment);
                }
                getListener.onSuccess(real);
            }
        }).start();
    }
}
