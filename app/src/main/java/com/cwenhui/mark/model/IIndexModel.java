package com.cwenhui.mark.model;

import com.cwenhui.mark.bean.Category;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface IIndexModel {
    /**
     * 从服务器获得分类列表
     * @return
     */
    public List<Category> getCategories();

}
