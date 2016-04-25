package com.cwenhui.mark.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cwenhui.mark.R;
import com.cwenhui.mark.configs.Constant;
import com.cwenhui.mark.presenter.ProfilePresenter;
import com.cwenhui.mark.utils.ImageFirstDisplayListener;
import com.cwenhui.mark.utils.ImageLoaderHelper;
import com.cwenhui.mark.utils.RoundImageView;
import com.cwenhui.mark.view.IProfileView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by cwenhui on 2016.02.23
 */
public class ProfileFragment extends Fragment implements IProfileView{
    private static final String TAG = "ProfileFragment";
    private View view;
    private ProfilePresenter presenter;
    private RoundImageView selfImage;
    private TextView selfName, selfEduInfo;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;								//显示图片的各种设置
    private ImageFirstDisplayListener displayListener = new ImageFirstDisplayListener();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        initData();
        initView();
        return view;
    }

    private void initData() {
        options = ImageLoaderHelper.getInstance().initImageLoader(getActivity(),
                imageLoader, Constant.IMAGE_PATH, R.drawable.nowcoder_ic_launcher);
        presenter = new ProfilePresenter(this);
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_fragment_profile);
        toolbar.setTitle("马课");
        toolbar.inflateMenu(R.menu.menu_profile);

        selfImage = (RoundImageView) view.findViewById(R.id.iv_fragment_profile_self_img);
        selfName = (TextView) view.findViewById(R.id.tv_fragment_profile_self_name);
        selfEduInfo = (TextView) view.findViewById(R.id.tv_fragment_profile_edu_info);

        presenter.initSelfMessage();
    }

    @Override
    public void setSelfMessage(String image, String name, String eduInfor) {
        selfName.setText(name);
        selfEduInfo.setText(eduInfor);
        imageLoader.displayImage(image, selfImage, options, displayListener);
    }

}
