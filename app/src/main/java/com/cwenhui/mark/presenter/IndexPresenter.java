package com.cwenhui.mark.presenter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.cwenhui.mark.model.IIndexModel;
import com.cwenhui.mark.model.impl.IndexModel;
import com.cwenhui.mark.view.IIndexView;

/**
 * Created by cwenhui on 2016.02.23
 */
public class IndexPresenter {
    private IIndexModel indexModel;
    private IIndexView indexView;

    public IndexPresenter(IIndexView indexView) {
        this.indexModel = new IndexModel();
        this.indexView = indexView;
    }

    public void init(){
        indexView.setCategoriesView(indexModel.getCategories());
    }

    /**
     * 根据item的高度来决定listview的高度,解决scrollview内嵌listview的问题
     * @param gridView
     */
    public void setGridViewHeightBasedOnChildren(GridView gridView, int columns) {
        if (gridView == null) return;
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount() / columns; i++) {
            View listItem = listAdapter.getView(i + columns, null, gridView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight + (gridView.getVerticalSpacing() * (listAdapter.getCount() / columns - 1));
        gridView.setLayoutParams(params);
    }
}
