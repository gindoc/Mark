package com.cwenhui.mark.presenter;

import com.cwenhui.mark.model.IFriendMsgModel;
import com.cwenhui.mark.model.impl.FriendMsgModel;
import com.cwenhui.mark.view.IFriendMsgView;

/**
 * Created by cwenhui on 2016.02.23
 */
public class FriendMsgPresenter {
    private IFriendMsgView friendMsgView;
    private IFriendMsgModel friendMsgModel;

    public FriendMsgPresenter(IFriendMsgView friendMsgView) {
        this.friendMsgView = friendMsgView;
        this.friendMsgModel = new FriendMsgModel();
    }

    /**
     * 初始化好友私信消息列表
     */
    public void initMessageList() {
        friendMsgView.initMessageList(friendMsgModel.getFriendMsgs(null));
    }
}
