package com.cwenhui.mark.common;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

import com.cwenhui.mark.configs.Constant;
import com.cwenhui.mark.presenter.IPresenter;

/**
 * Created by Administrator on 2016/5/18.
 */
public class ScrollViewTouchListener implements View.OnTouchListener {
    private ScrollView scrollView;
    private CommonRefreshRecyclerViewAdapter adapter;
    private SwipeRefreshLayout swipe;
    private IPresenter presenter;
    public static boolean isLoading = false;

    public ScrollViewTouchListener(ScrollView scrollView, CommonRefreshRecyclerViewAdapter adapter,
                                   SwipeRefreshLayout swipe, IPresenter presenter) {
        this.scrollView = scrollView;
        this.adapter = adapter;
        this.swipe = swipe;
        this.presenter = presenter;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        View childView = scrollView.getChildAt(0);
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:     //手指抬起
                if (childView != null && childView.getMeasuredHeight() <= scrollView.getScrollY() +
                        scrollView.getHeight()) {
                    boolean isRefreshing = swipe.isRefreshing();
                    if (isRefreshing) {         //防止在下拉刷新时又上拉加载
                        adapter.notifyItemRemoved(adapter.getItemCount());
                        break;
                    }
                    if (!adapter.isFullScreen()) {      //如果加载的item小于屏幕能装载的个数，则隐藏footerView
                        break;
                    }
                    if (!isLoading) {
                        isLoading = true;
                        presenter.reflesh(Constant.PULL_UP);
                    }
                    swipe.setEnabled(false);
                } else if (scrollView.getScrollY() == 0) {
                    swipe.setEnabled(true);
                } else {
                    swipe.setEnabled(false);
                }
                break;
        }
        return false;
    }
}
