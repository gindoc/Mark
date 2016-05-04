package com.cwenhui.mark.model;

import com.cwenhui.mark.bean.Discuss;
import com.cwenhui.mark.common.OnGetListener;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface IDiscussModel {
    /**
     * 调用api查询讨论区信息，并在监听回调中处理信息（例如显示到UI）
     * @param api
     * @param getListener
     */
    public void initDisgussList(String api, OnGetListener<Discuss> getListener);

    /**
     * 测试EventBus使用情况，不用回调处理信息
     * @param api
     */
    public void initDisgussList(String api);
}
