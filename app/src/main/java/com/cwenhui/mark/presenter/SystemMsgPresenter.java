package com.cwenhui.mark.presenter;

import com.cwenhui.mark.model.ISystemMsgModel;
import com.cwenhui.mark.model.impl.SystemMsgModel;
import com.cwenhui.mark.view.ISystemMsgView;

/**
 * Created by cwenhui on 2016.02.23
 */
public class SystemMsgPresenter {
    private static final String TAG = "SystemMsgPresenter";
    private ISystemMsgView systemMsgView;
    private ISystemMsgModel systemMsgModel;

    public SystemMsgPresenter(ISystemMsgView systemMsgView) {
        this.systemMsgView = systemMsgView;
        this.systemMsgModel = new SystemMsgModel();
    }

    /**
     * 初始化系统通知消息列表
     */
    public void initMessageList(){
        systemMsgView.initMessageList(systemMsgModel.getSystemMsgs(null));
    }
}
