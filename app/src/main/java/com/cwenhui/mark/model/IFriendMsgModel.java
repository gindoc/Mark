package com.cwenhui.mark.model;

import com.cwenhui.mark.bean.MessageType;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface IFriendMsgModel {
    /**
     * 调用api查询朋友私信
     * @param api
     * @return
     */
    public List<MessageType> getFriendMsgs(String api);

}
