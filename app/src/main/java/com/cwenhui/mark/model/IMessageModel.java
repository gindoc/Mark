package com.cwenhui.mark.model;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface IMessageModel {
    /**
     * 调用api查询是否有新的朋友私信
     * @param api
     * @return
     */
    public boolean haveNewFriendMsg(String api);

    /**
     * 调用api查询是否有新的系统通知
     * @param api
     * @return
     */
    public boolean haveNewSystemMsg(String api);
}
