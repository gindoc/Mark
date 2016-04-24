package com.cwenhui.mark.model.impl;

import com.cwenhui.mark.model.IProfileModel;

/**
 * Created by cwenhui on 2016.02.23
 */
public class ProfileModel implements IProfileModel {
    @Override
    public String getSelfName(String api) {
        return "马课198237";
    }

    @Override
    public String getSelfImageUrl(String api) {
        return "http://www.cwenhui.com/images/default_member.jpg";
    }

    @Override
    public String getSlefEduInfo(String api) {
        return "未填写教育信息";
    }
}
