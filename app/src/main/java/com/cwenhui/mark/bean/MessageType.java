package com.cwenhui.mark.bean;

/**
 * Created by cwenhui on 2016.02.23
 */
public class MessageType {
    /**
     * 消息类型的名字
     */
    private String msgTypeName;
    /**
     * 消息类型的图像
     */
    private int resId;
    /**
     * 是否显示红色的点提示有新消息
     */
    private boolean isPromptNewMsg;

    public MessageType(String msgTypeName, int resId, boolean isPromptNewMsg) {
        this.msgTypeName = msgTypeName;
        this.resId = resId;
        this.isPromptNewMsg = isPromptNewMsg;
    }

    public String getMsgTypeName() {
        return msgTypeName;
    }

    public void setMsgTypeName(String msgTypeName) {
        this.msgTypeName = msgTypeName;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public boolean isPromptNewMsg() {
        return isPromptNewMsg;
    }

    public void setIsPromptNewMsg(boolean isPromptNewMsg) {
        this.isPromptNewMsg = isPromptNewMsg;
    }
}
