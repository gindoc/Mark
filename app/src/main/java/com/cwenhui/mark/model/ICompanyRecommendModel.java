package com.cwenhui.mark.model;

import com.cwenhui.mark.bean.CompanyRecommend;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface ICompanyRecommendModel {
    /**
     * 调用api查询公司推荐套题
     * @param api
     * @return
     */
    public List<CompanyRecommend> getRecommends(String api);

}
