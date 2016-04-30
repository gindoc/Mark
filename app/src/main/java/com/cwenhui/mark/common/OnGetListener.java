package com.cwenhui.mark.common;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 利用钩子方法，可以有选择性的重写方法
 * Created by cwenhui on 2016.02.23
 */
public class OnGetListener<T> implements IOnGetListener<T> {
    @Override
    public void onSuccess(Collection<T> t) {

    }

    @Override
    public void onSuccess(T t) {

    }

    @Override
    public void onSuccess(Map<String, List<T>> ResultSet) {

    }

    @Override
    public void onFailure() {

    }
}
