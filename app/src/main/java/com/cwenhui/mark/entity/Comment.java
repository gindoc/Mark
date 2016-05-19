package com.cwenhui.mark.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class Comment {
    private String commentId;
    private User user;
    private Practice practice;
    private String content;
    private Date createTime;
    private Comment parent;
    private List<Comment> commented;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Practice getPractice() {
        return practice;
    }

    public void setPractice(Practice practice) {
        this.practice = practice;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Comment getParent() {
        return parent;
    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public List<Comment> getCommented() {
        return commented;
    }

    public void setCommented(List<Comment> commented) {
        this.commented = commented;
    }
}
