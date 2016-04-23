package com.cwenhui.mark.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cwenhui.mark.MainActivity;
import com.cwenhui.mark.R;
import com.cwenhui.mark.configs.Constant;
import com.cwenhui.mark.presenter.CommunityPresenter;
import com.cwenhui.mark.utils.ImageFirstDisplayListener;
import com.cwenhui.mark.utils.ImageLoaderHelper;
import com.cwenhui.mark.utils.RoundImageView;
import com.cwenhui.mark.view.ICommunityView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by cwenhui on 2016.02.23
 */
public class CommunityFragment extends Fragment implements ICommunityView{
    private View view;
    private CommunityPresenter presenter;
    private RoundImageView disguss, clockNews;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;								//显示图片的各种设置
    private ImageFirstDisplayListener displayListener = new ImageFirstDisplayListener();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_community, container, false);
        initData();
        initView();
        return view;
    }

    private void initData() {
        presenter = new CommunityPresenter(this);
        options = ImageLoaderHelper.getInstance().initImageLoader(getActivity(),
                imageLoader, Constant.IMAGE_PATH, R.drawable.nowcoder_ic_launcher);
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_fragment_community);
        toolbar.setTitle("社区");
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        disguss = (RoundImageView) view.findViewById(R.id.iv_fragment_community_disguss_member);
        clockNews = (RoundImageView) view.findViewById(R.id.iv_fragment_community_clock_news_member);
        presenter.init();
    }

    @Override
    public void setDisgussMemberImage(String imgUrl) {
        imageLoader.displayImage(imgUrl, disguss, options, displayListener);
    }

    @Override
    public void setClockNewsMemberImage(String imgUrl) {
        imageLoader.displayImage(imgUrl, clockNews, options, displayListener);
    }
}
