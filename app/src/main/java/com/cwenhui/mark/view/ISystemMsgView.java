package com.cwenhui.mark.view;

import com.cwenhui.mark.bean.MessageType;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface ISystemMsgView {
    /**
     * 初始化消息列表（即RecyclerView）
     */
    public void initMessageList(List<MessageType> messageTypes);
}
