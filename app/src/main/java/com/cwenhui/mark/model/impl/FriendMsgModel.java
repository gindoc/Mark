package com.cwenhui.mark.model.impl;

import com.cwenhui.mark.bean.MessageType;
import com.cwenhui.mark.model.IFriendMsgModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class FriendMsgModel implements IFriendMsgModel {
    @Override
    public List<MessageType> getFriendMsgs(String api) {
        return new ArrayList<MessageType>();
    }
}
