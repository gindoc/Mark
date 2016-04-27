package com.cwenhui.mark.utils;

import java.util.Collection;

/**
 * 网络请求监听
 * 用于回调处理UI
 * Created by cwenhui on 2016.02.23
 */
public interface IOnGetListener<T> {
    /**
     * 请求成功时返回一个指定类型的集合
     * @param T
     */
    void onSuccess(Collection<T> T);

    /**
     * 请求成功时返回一个指定类型的对象
     * @param t
     */
    void onSuccess(T t);

    /**
     * 请求失败时的回调
     */
    void onFailure();
}
