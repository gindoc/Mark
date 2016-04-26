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
import com.cwenhui.mark.adapter.CommonRecyclerViewAdapter;
import com.cwenhui.mark.bean.CompanyRecommend;
import com.cwenhui.mark.configs.Constant;
import com.cwenhui.mark.presenter.RecommendPresenter;
import com.cwenhui.mark.utils.CommondRecyclerViewHolder;
import com.cwenhui.mark.utils.ImageFirstDisplayListener;
import com.cwenhui.mark.utils.ImageLoaderHelper;
import com.cwenhui.mark.view.ICompanyRecommendView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class CompanyRecommendFragment extends Fragment implements ICompanyRecommendView {
    private View view;
    private RecommendPresenter presenter;
    private RecyclerView recyclerView;
    private CommonRecyclerViewAdapter<CompanyRecommend> adapter;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;								//显示图片的各种设置
    private ImageFirstDisplayListener displayListener = new ImageFirstDisplayListener();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_company_recommend, container, false);
        initData();
        initView();
        return view;
    }

    private void initData() {
        presenter = new RecommendPresenter(this);
        options = ImageLoaderHelper.getInstance().initImageLoader(getActivity(),
                imageLoader, Constant.IMAGE_PATH, R.drawable.nowcoder_ic_launcher);
    }

    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_fragment_company_recommend);
        presenter.initRecommendsList();
    }

    @Override
    public void initRecommendsList(List<CompanyRecommend> recommends) {
        adapter = new CommonRecyclerViewAdapter<CompanyRecommend>(getActivity(), R.layout.item_fragment_company_recommend,
                recommends) {

            @Override
            public void convert(CommondRecyclerViewHolder holder, CompanyRecommend recommend) {
                holder.setText(R.id.tv_item_fragment_recommend_name, " " + recommend.getCompanyName())
                        .setText(R.id.tv_item_fragment_recommend_people_cnt, " 已有" + recommend.getPeopleCnt() + "人参加")
                        .setText(R.id.tv_item_fragment_recommend_question_cnt, " 题目数量:" + recommend.getQuestionCnt())
                        .setRatingBar(R.id.ratingBar_item_fragment_recommend, recommend.getDifficult());
                imageLoader.displayImage(recommend.getCompanyLogo(),
                        (ImageView)holder.getView(R.id.iv_item_fragment_recommend_logo), options, displayListener);
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
}
