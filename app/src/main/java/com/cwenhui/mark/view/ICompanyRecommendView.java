package com.cwenhui.mark.view;

import com.cwenhui.mark.bean.CompanyRecommend;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface ICompanyRecommendView {
    /**
     * 初始化公司套题推荐列表
     * @param recommends
     */
    public void initRecommendsList(List<CompanyRecommend> recommends);
}
