package com.cwenhui.mark.presenter;

import com.cwenhui.mark.model.IMessageModel;
import com.cwenhui.mark.model.impl.MessageModel;
import com.cwenhui.mark.view.IMessageView;

/**
 * Created by cwenhui on 2016.02.23
 */
public class MessagePresenter {
    public IMessageModel messageModel;
    public IMessageView messageView;

    public MessagePresenter(IMessageView messageView) {
        this.messageView = messageView;
        this.messageModel = new MessageModel();
    }

    /**
     * 根据服务器回馈的信息，如果有新消息则显示对应的tab中提示新消息的红点
     */
    public void showNewMsg() {
        if (messageModel.haveNewFriendMsg(null)) {
            messageView.promptNewMsg(IMessageView.FRIEND_NEW_MSG);
        }
        if (messageModel.haveNewSystemMsg(null)) {
            messageView.promptNewMsg(IMessageView.SYSTEM_NEW_MSG);
        }

    }
}
