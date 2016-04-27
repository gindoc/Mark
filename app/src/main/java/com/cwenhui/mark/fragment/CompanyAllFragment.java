package com.cwenhui.mark.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cwenhui.mark.R;
import com.cwenhui.mark.adapter.CommonRefreshRecyclerViewAdapter;
import com.cwenhui.mark.bean.CompanyAll;
import com.cwenhui.mark.model.ICompanyAllModel;
import com.cwenhui.mark.presenter.CompanyAllPresenter;
import com.cwenhui.mark.utils.CommondRecyclerViewHolder;
import com.cwenhui.mark.view.ICompanyAllView;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class CompanyAllFragment extends Fragment implements ICompanyAllView,
        SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "CompanyAllFragment";
    private View view;
    private CompanyAllPresenter presenter;
    private SwipeRefreshLayout swipe;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private CommonRefreshRecyclerViewAdapter<CompanyAll> adapter;
    private int lastVisibleItemPosition;
    private boolean isLoading = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_company_all, container, false);
        initView();
        return view;
    }

    private void initView() {
        presenter = new CompanyAllPresenter(this);

        swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe_fragment_company_all);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_fragment_company_all);
        setRecyclerViewListener();

        swipe.setColorSchemeResources(R.color.swipeColor1, R.color.swipeColor2,
                R.color.swipeColor3, R.color.swipeColor4);
        swipe.setOnRefreshListener(this);
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        swipe.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                24, getResources().getDisplayMetrics()));
        swipe.setRefreshing(true);

        presenter.initCompanyAllsList();
    }

    /**
     * 为recylerView设置监听，实现上拉加载
     */
    private void setRecyclerViewListener() {
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (lastVisibleItemPosition + 1 == adapter.getItemCount()) {

//                    swipe.setRefreshing(true);
//                    boolean isRefreshing = swipe.isRefreshing();
//                    if (isRefreshing) {
//                        adapter.notifyItemRemoved(adapter.getItemCount());
//                        return;
//                    }
                    if (!isLoading) {
                        isLoading = true;
                        presenter.reflesh(ICompanyAllModel.PULL_UP);
                        Log.e(TAG, "loading executed");
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();
                Log.e(TAG, lastVisibleItemPosition + "");
            }
        });
    }

    @Override
    public void initCompanyAllsList(List<CompanyAll> companyAll) {
        adapter = new CommonRefreshRecyclerViewAdapter<CompanyAll>(getActivity(),
                R.layout.item_fragment_company_all, companyAll) {
            @Override
            protected void convert(RecyclerView.ViewHolder holder, CompanyAll companyAll) {
                ((CommondRecyclerViewHolder) holder).setText(R.id.tv_item_fragment_all_name, companyAll.getCompanyName())
                        .setText(R.id.tv_item_fragment_all_question_cnt, companyAll.getQuestionAnt() + "套");
            }
        };
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void refleshCompanyAllsList(List<CompanyAll> companyAll) {
        adapter.getmDatas().addAll(companyAll);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void hideLoading() {
        swipe.setRefreshing(false);
        isLoading = false;
    }

    /**
     * 为swipe设置监听，实现下拉刷新
     */
    @Override
    public void onRefresh() {
        presenter.reflesh(ICompanyAllModel.PULL_DOWN);
    }
}
