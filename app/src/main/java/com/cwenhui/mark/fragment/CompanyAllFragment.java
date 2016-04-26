package com.cwenhui.mark.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cwenhui.mark.R;
import com.cwenhui.mark.adapter.CommonRecyclerViewAdapter;
import com.cwenhui.mark.bean.CompanyAll;
import com.cwenhui.mark.presenter.CompanyAllPresenter;
import com.cwenhui.mark.utils.CommondRecyclerViewHolder;
import com.cwenhui.mark.view.ICompanyAllView;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class CompanyAllFragment extends Fragment implements ICompanyAllView{
    private View view;
    private CompanyAllPresenter presenter;
    private RecyclerView recyclerView;
    private CommonRecyclerViewAdapter<CompanyAll> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_company_all, container, false);
        initView();
        return view;
    }

    private void initView() {
        presenter = new CompanyAllPresenter(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_fragment_company_all);
        presenter.initCompanyAllsList();
    }

    @Override
    public void initCompanyAllsList(final List<CompanyAll> companyAll) {
        adapter = new CommonRecyclerViewAdapter<CompanyAll>(getActivity(), R.layout.item_fragment_company_all, companyAll) {
            @Override
            public void convert(CommondRecyclerViewHolder holder, CompanyAll companyAll) {
                holder.setText(R.id.tv_item_fragment_all_name, companyAll.getCompanyName())
                        .setText(R.id.tv_item_fragment_all_question_cnt, companyAll.getQuestionAnt() + "套");
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
}
