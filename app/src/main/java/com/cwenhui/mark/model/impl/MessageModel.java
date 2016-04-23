package com.cwenhui.mark.model.impl;

import com.cwenhui.mark.model.IMessageModel;

/**
 * Created by cwenhui on 2016.02.23
 */
public class MessageModel implements IMessageModel {
    @Override
    public boolean haveNewFriendMsg(String api) {
        return false;
    }

    @Override
    public boolean haveNewSystemMsg(String api) {
        return true;
    }
}
