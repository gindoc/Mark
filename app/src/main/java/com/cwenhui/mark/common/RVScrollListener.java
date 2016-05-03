package com.cwenhui.mark.common;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cwenhui.mark.configs.Constant;
import com.cwenhui.mark.presenter.IPresenter;

/**
 * RecyclerView滚动监听
 * Created by cwenhui on 2016.02.23
 */
public class RVScrollListener extends RecyclerView.OnScrollListener {
    private static final String TAG = "RVScrollListener";
    private RecyclerView recyclerView;
    private CommonRefreshRecyclerViewAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private SwipeRefreshLayout swipe;
    public static boolean isLoading = false;
    private IPresenter presenter;
    private int lastVisibleItemPosition;

    public RVScrollListener(RecyclerView recyclerView, SwipeRefreshLayout swipe, IPresenter presenter) {
        this.recyclerView = recyclerView;
        this.adapter = (CommonRefreshRecyclerViewAdapter) recyclerView.getAdapter();
        this.mLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        this.swipe = swipe;
        this.presenter = presenter;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (adapter == null)  return;   //防止第一次加载时就滑动屏幕造成报空指针异常
        if (lastVisibleItemPosition + 1 == adapter.getItemCount()) {

            boolean isRefreshing = swipe.isRefreshing();
            if (isRefreshing) {         //防止在下拉刷新时又上拉加载
                adapter.notifyItemRemoved(adapter.getItemCount());
                return;
            }
            if (!adapter.isFullScreen()) {      //如果加载的item小于屏幕能装载的个数，则隐藏footerView
                return;
            }
            if (!isLoading) {
                isLoading = true;
                presenter.reflesh(Constant.PULL_UP);
            }
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();
    }
}
