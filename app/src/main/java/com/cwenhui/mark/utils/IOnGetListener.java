package com.cwenhui.mark.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 网络请求监听
 * 用于回调处理UI
 * Created by cwenhui on 2016.02.23
 */
public interface IOnGetListener<T> {
    /**
     * 请求成功时返回一个指定类型的集合
     * @param t
     */
    void onSuccess(Collection<T> t);

    /**
     * 请求成功时返回一个指定类型的对象
     * @param t
     */
    void onSuccess(T t);

    /**
     * 请求成功时返回一个指定类型的Map集合
     * @param ResultSet
     */
    void onSuccess(Map<String, List<T>> ResultSet);
    /**
     * 请求失败时的回调
     */
    void onFailure();
}
