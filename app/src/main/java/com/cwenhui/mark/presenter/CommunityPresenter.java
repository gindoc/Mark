package com.cwenhui.mark.presenter;

import com.cwenhui.mark.model.ICommunityModel;
import com.cwenhui.mark.model.impl.CommunityModel;
import com.cwenhui.mark.view.ICommunityView;

/**
 * Created by cwenhui on 2016.02.23
 */
public class CommunityPresenter {
    private ICommunityView communityView;
    private ICommunityModel communityModel;

    public CommunityPresenter(ICommunityView communityView) {
        this.communityView = communityView;
        this.communityModel = new CommunityModel();
    }

    /**
     * 初始化界面操作（需要用到网络资源的部分）
     */
    public void init(){
        communityView.setDisgussMemberImage(communityModel.getDisgussImageLatest(null));
        communityView.setClockNewsMemberImage(communityModel.getClockNewsImageLates(null));
    }
}
