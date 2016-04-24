package com.cwenhui.mark.model.impl;

import com.cwenhui.mark.R;
import com.cwenhui.mark.bean.MessageType;
import com.cwenhui.mark.model.ISystemMsgModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class SystemMsgModel implements ISystemMsgModel {
    @Override
    public List<MessageType> getSystemMsgs(String api) {
        List<MessageType> messageTypes = new ArrayList<>();
        MessageType type = new MessageType("通知", R.drawable.notify, true);
        messageTypes.add(type);
        type = new MessageType("赞", R.drawable.like, false);
        messageTypes.add(type);
        type = new MessageType("回复", R.drawable.reply, false);
        messageTypes.add(type);
        type = new MessageType("关注", R.drawable.follow, false);
        messageTypes.add(type);
        type = new MessageType("回答采纳", R.drawable.recommend, false);
        messageTypes.add(type);
        return messageTypes;
    }
}
