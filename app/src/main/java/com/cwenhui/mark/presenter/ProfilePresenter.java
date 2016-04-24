package com.cwenhui.mark.presenter;

import com.cwenhui.mark.model.IProfileModel;
import com.cwenhui.mark.model.impl.ProfileModel;
import com.cwenhui.mark.view.IProfileView;

/**
 * Created by cwenhui on 2016.02.23
 */
public class ProfilePresenter {
    private IProfileView profileView;
    private IProfileModel profileModel;

    public ProfilePresenter(IProfileView profileView) {
        this.profileView = profileView;
        this.profileModel = new ProfileModel();
    }

    /**
     * 初始化用户基本信息栏
     */
    public void initSelfMessage() {
        profileView.setSelfMessage(profileModel.getSelfImageUrl(null), profileModel.getSelfName(null),
                profileModel.getSlefEduInfo(null));
    }
}
