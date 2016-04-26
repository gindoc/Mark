package com.cwenhui.mark.model.impl;

import com.cwenhui.mark.bean.CompanyRecommend;
import com.cwenhui.mark.model.ICompanyRecommendModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class RecommendModel implements ICompanyRecommendModel {
    @Override
    public List<CompanyRecommend> getRecommends(String api) {
        List<CompanyRecommend> recommends = new ArrayList<CompanyRecommend>();
        CompanyRecommend recommend;
        for (int i = 0; i < 15; i++) {
            recommend = new CompanyRecommend("http://www.cwenhui.com/images/default_member.jpg", "京东测试" + i,
                    10 * i, 15 * i, i % 5);
            recommends.add(recommend);
        }
        return recommends;
    }
}
