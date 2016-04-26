package com.cwenhui.mark.utils;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by cwenhui on 2016.02.23
 */
public class CommondRecyclerViewHolder extends RecyclerView.ViewHolder {
    private View mConvertView;
    private SparseArray<View> mViews;

    public CommondRecyclerViewHolder(View viewGroup) {
        super(viewGroup);
        mConvertView = viewGroup;
        mViews = new SparseArray<View>();
    }

    public CommondRecyclerViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public CommondRecyclerViewHolder setRatingBar(int viewId, float rating) {
        RatingBar ratingBar = getView(viewId);
        ratingBar.setRating(rating);
        return this;
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;

    }
}
