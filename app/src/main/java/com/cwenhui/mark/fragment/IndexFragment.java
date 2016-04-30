package com.cwenhui.mark.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.cwenhui.mark.MainActivity;
import com.cwenhui.mark.R;
import com.cwenhui.mark.common.CommonAdapter;
import com.cwenhui.mark.bean.Category;
import com.cwenhui.mark.presenter.IndexPresenter;
import com.cwenhui.mark.ui.CompanySubjectActivity;
import com.cwenhui.mark.ui.SpecialPracticeActivity;
import com.cwenhui.mark.common.ViewHolder;
import com.cwenhui.mark.view.IIndexView;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class IndexFragment extends Fragment implements IIndexView, AdapterView.OnItemClickListener{
    final String TAG = "IndexFragment";
    private View view;
    private GridView category;
    private IndexPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_index, container, false);

        Log.e(TAG, "onCreateView");

        initView();
        initEvent();
        return view;
    }

    private void initEvent() {
        category.setOnItemClickListener(this);
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_fragment_index);
        toolbar.setTitle("马课");
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);

        presenter = new IndexPresenter(this);
        category = (GridView) view.findViewById(R.id.gv_activity_main_category);

        presenter.setCategoryView();
        presenter.setGridViewHeightBasedOnChildren(category, 3);
    }

    @Override
    public void setCategoriesView(List<Category> categories) {
        category.setAdapter(new CommonAdapter<Category>(getActivity(), categories, R.layout.item_activity_main_category) {
            @Override
            public void convert(ViewHolder holder, Category category) {
                holder.setText(R.id.tv_item_activity_main_category, category.getCatName())
                        .setImageResource(R.id.iv_item_activity_main_category, category.getCatImg());
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.e(TAG, "onCreateOptionsMenu");
        inflater.inflate(R.menu.menu_index, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e(TAG, position + "");
        Intent intent;
        switch (position) {
            case 0:
                intent = new Intent(getActivity(), SpecialPracticeActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(getActivity(), CompanySubjectActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
