package com.cwenhui.mark.model;

import com.cwenhui.mark.bean.CompanyAll;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface ICompanyAllModel {
    /**
     * 调用api查询所有公司套题
     * @param api
     * @return
     */
    public List<CompanyAll> getAllCompanySubjects(String api);
}
