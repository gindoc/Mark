package com.cwenhui.mark.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.cwenhui.mark.MainActivity;
import com.cwenhui.mark.R;
import com.cwenhui.mark.configs.Constant;
import com.cwenhui.mark.presenter.CommunityPresenter;
import com.cwenhui.mark.common.ImageFirstDisplayListener;
import com.cwenhui.mark.common.ImageLoaderHelper;
import com.cwenhui.mark.ui.DisgussActivity;
import com.cwenhui.mark.widget.RoundImageView;
import com.cwenhui.mark.view.ICommunityView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by cwenhui on 2016.02.23
 */
public class CommunityFragment extends Fragment implements ICommunityView, View.OnClickListener {
    private static final String TAG = "CommunityFragment";
    private View view;
    private CommunityPresenter presenter;
    private RoundImageView disgussImg, clockNewsImg;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;                                //显示图片的各种设置
    private ImageFirstDisplayListener displayListener = new ImageFirstDisplayListener();
    private RelativeLayout disgussLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_community, container, false);
        initData();
        initView();
        initEvent();
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
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

        disgussImg = (RoundImageView) view.findViewById(R.id.iv_fragment_community_disguss_member);
        clockNewsImg = (RoundImageView) view.findViewById(R.id.iv_fragment_community_clock_news_member);
        disgussLayout = (RelativeLayout) view.findViewById(R.id.rl_fragment_community_disguss);

        presenter.init();
    }

    private void initEvent() {
        disgussLayout.setOnClickListener(this);
    }

    @Override
    public void setDisgussMemberImage(String imgUrl) {
        imageLoader.displayImage(imgUrl, disgussImg, options, displayListener);
    }

    @Override
    public void setClockNewsMemberImage(String imgUrl) {
        imageLoader.displayImage(imgUrl, clockNewsImg, options, displayListener);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.rl_fragment_community_disguss:
                intent = new Intent(getActivity(), DisgussActivity.class);
                startActivity(intent);
                break;
        }
        getActivity().overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
    }
}
