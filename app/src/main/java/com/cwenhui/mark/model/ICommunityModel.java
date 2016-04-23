package com.cwenhui.mark.model;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface ICommunityModel {
    /**
     * 返回讨论区最新信息的图片的url
     * @param api 接口
     * @return
     */
    public String getDisgussImageLatest(String api);

    /**
     * 返回打卡动态最新信息的图片的url
     * @param api
     * @return
     */
    public String getClockNewsImageLates(String api);
}
