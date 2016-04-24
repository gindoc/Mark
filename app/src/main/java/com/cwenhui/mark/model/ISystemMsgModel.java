package com.cwenhui.mark.model;

import com.cwenhui.mark.bean.MessageType;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface ISystemMsgModel {
    /**
     * 调用api查询系统通知
     * @param api
     * @return
     */
    public List<MessageType> getSystemMsgs(String api);

}
