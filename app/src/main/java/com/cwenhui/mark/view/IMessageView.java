package com.cwenhui.mark.view;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface IMessageView {
    int FRIEND_NEW_MSG = 0;
    int SYSTEM_NEW_MSG = 1;

    /**
     * 显示tab中提示新消息的红点
     * @param position
     */
    public void promptNewMsg(int position);

    /**
     * 隐藏tab中提示新消息的红点
     * @param position
     */
    public void hideNewMsg(int position);
}
