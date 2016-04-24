package com.cwenhui.mark.model;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface IProfileModel {
    /**
     * 调用api获得用户名（这里只是模拟，实际情况可能返回一个实体）
     * @param api
     * @return
     */
    public String getSelfName(String api);

    /**
     * 调用api获得用户头像url（这里只是模拟，实际情况可能返回一个实体）
     * @param api
     * @return
     */
    public String getSelfImageUrl(String api);

    /**
     * 调用api获得用户教育背景信息（这里只是模拟，实际情况可能返回一个实体）
     * @param api
     * @return
     */
    public String getSlefEduInfo(String api);
}
