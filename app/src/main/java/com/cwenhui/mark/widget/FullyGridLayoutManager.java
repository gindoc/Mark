package com.cwenhui.mark.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author:    ZhuWenWu
 * Version    V1.0
 * Date:      2015/2/26  14:14.
 * Description:
 * Modification  History:
 * Date             Author                Version            Description
 * -----------------------------------------------------------------------------------
 * 2015/2/26        ZhuWenWu            1.0                    1.0
 * Why & What is modified:
 */
public class FullyGridLayoutManager extends GridLayoutManager {
    public FullyGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public FullyGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    private int[] mMeasuredDimension = new int[2];      //View的尺寸

    /**
     * 设置View的宽和高
     * onMeasure传入的widthMeasureSpec和heightMeasureSpec不是一般的尺寸数值，而是将模式和尺寸组合在一起的数值
     * @param recycler      recyclerView
     * @param state         state
     * @param widthSpec     模式和尺寸组合在一起的数值
     * @param heightSpec    模式和尺寸组合在一起的数值
     */
    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        final int widthMode = View.MeasureSpec.getMode(widthSpec);      //得到模式
        final int heightMode = View.MeasureSpec.getMode(heightSpec);
        final int widthSize = View.MeasureSpec.getSize(widthSpec);      //得到尺寸
        final int heightSize = View.MeasureSpec.getSize(heightSpec);

        int width = 0;
        int height = 0;
        int count = getItemCount();                                     //item数量
        int span = getSpanCount();                                      //列数
        for (int i = 0; i < count; i++) {
            measureScrapChild(recycler, i,
                    View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
                    mMeasuredDimension);

            if (getOrientation() == HORIZONTAL) {
                if (i % span == 0) {
                    width = width + mMeasuredDimension[0];
                }
                if (i == 0) {
                    height = mMeasuredDimension[1];
                }
            } else {
                if (i % span == 0) {
                    height = height + mMeasuredDimension[1];
                }
                if (i == 0) {
                    width = mMeasuredDimension[0];
                }
            }
        }

        switch (widthMode) {
            case View.MeasureSpec.EXACTLY:                             //精确尺寸
                width = widthSize;
            case View.MeasureSpec.AT_MOST:                             //最大尺寸
            case View.MeasureSpec.UNSPECIFIED:                         //未指定尺寸
        }

        switch (heightMode) {
            case View.MeasureSpec.EXACTLY:
                height = heightSize;
            case View.MeasureSpec.AT_MOST:
            case View.MeasureSpec.UNSPECIFIED:
        }

        setMeasuredDimension(width, height);            //设置实际大小
    }

    /**
     * 测量每个item的尺寸
     * @param recycler              recyclerView
     * @param position              position
     * @param widthSpec             尺寸宽
     * @param heightSpec            尺寸高
     * @param measuredDimension     子view的尺寸
     */
    private void measureScrapChild(RecyclerView.Recycler recycler, int position, int widthSpec,
                                   int heightSpec, int[] measuredDimension) {
        if (position < getItemCount()) {
            try {
                View view = recycler.getViewForPosition(0);//fix 动态添加时报IndexOutOfBoundsException
                if (view != null) {
                    //测量每个子View高宽时，同时得到了该View本身的边距大小
                    RecyclerView.LayoutParams p = (RecyclerView.LayoutParams) view.getLayoutParams();
                    //获取子View的childWidthMeasureSpec属性，去除了该父View的边距值 mPaddingLeft + mPaddingRight
                    int childWidthSpec = ViewGroup.getChildMeasureSpec(widthSpec,
                            getPaddingLeft() + getPaddingRight(), p.width);
                    int childHeightSpec = ViewGroup.getChildMeasureSpec(heightSpec,
                            getPaddingTop() + getPaddingBottom(), p.height);
                    //调用measure()方法去做一些前期准备，真正的设置View的长宽值在onMeasure()
                    view.measure(childWidthSpec, childHeightSpec);
                    measuredDimension[0] = view.getMeasuredWidth() + p.leftMargin + p.rightMargin;
                    measuredDimension[1] = view.getMeasuredHeight() + p.bottomMargin + p.topMargin;
                    recycler.recycleView(view);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}