package com.cwenhui.mark.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.cwenhui.mark.MainActivity;
import com.cwenhui.mark.R;
import com.cwenhui.mark.adapter.CommonAdapter;
import com.cwenhui.mark.bean.Category;
import com.cwenhui.mark.presenter.IndexPresenter;
import com.cwenhui.mark.utils.ViewHolder;
import com.cwenhui.mark.view.IIndexView;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class IndexFragment extends Fragment implements IIndexView{
    final String TAG = "IndexFragment";
    private View view;
    private GridView category;
    private IndexPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_index, container, false);

        initView();
        return view;
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
}
