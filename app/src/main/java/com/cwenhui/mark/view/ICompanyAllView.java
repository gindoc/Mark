package com.cwenhui.mark.view;

import com.cwenhui.mark.bean.CompanyAll;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface ICompanyAllView {
    /**
     * 初始化全部公司套题列表
     * @param companyAll
     */
    public void initCompanyAllsList(List<CompanyAll> companyAll);
}
