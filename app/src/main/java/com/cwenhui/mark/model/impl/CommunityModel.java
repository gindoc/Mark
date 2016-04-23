package com.cwenhui.mark.model.impl;

import com.cwenhui.mark.model.ICommunityModel;

/**
 * Created by cwenhui on 2016.02.23
 */
public class CommunityModel implements ICommunityModel {
    @Override
    public String getDisgussImageLatest(String api) {
        return "http://www.cwenhui.com/images/default_member.jpg"; //此处模拟从接口得到的图片url
    }

    @Override
    public String getClockNewsImageLates(String api) {
        return "http://www.cwenhui.com/images/default_member.jpg"; //此处模拟从接口得到的图片url
    }
}
