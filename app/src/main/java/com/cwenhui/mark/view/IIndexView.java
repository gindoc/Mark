package com.cwenhui.mark.view;

import com.cwenhui.mark.bean.Category;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface IIndexView {
    /**
     * 初始化分类板块
     * @param categories
     */
    public void setCategoriesView(List<Category> categories);
}
