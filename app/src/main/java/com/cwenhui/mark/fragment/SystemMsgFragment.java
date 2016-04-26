package com.cwenhui.mark.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cwenhui.mark.R;
import com.cwenhui.mark.adapter.MessageRecyclerViewAdapter;
import com.cwenhui.mark.bean.MessageType;
import com.cwenhui.mark.presenter.SystemMsgPresenter;
import com.cwenhui.mark.view.ISystemMsgView;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class SystemMsgFragment extends Fragment implements ISystemMsgView{
    private static final String TAG = "SystemMsgFragment";
    private View view;
    private SystemMsgPresenter presenter;
    private RecyclerView recyclerView;
    private MessageRecyclerViewAdapter adapter;
    private ImageView emptyView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_system_msg, container, false);
        initView();
        return view;
    }

    private void initView() {
        presenter = new SystemMsgPresenter(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_fragment_system_msg_empty);
        emptyView = (ImageView) view.findViewById(R.id.iv_fragment_system_msg_empty);
        presenter.initMessageList();
    }

    @Override
    public void initMessageList(List<MessageType> messageTypes) {
        adapter = new MessageRecyclerViewAdapter(getActivity(), messageTypes);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                adapter.checkAdapterIsEmpty(emptyView);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        adapter.checkAdapterIsEmpty(emptyView);
    }
}
