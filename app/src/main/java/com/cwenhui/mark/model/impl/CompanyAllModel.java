package com.cwenhui.mark.model.impl;

import com.cwenhui.mark.bean.CompanyAll;
import com.cwenhui.mark.model.ICompanyAllModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class CompanyAllModel implements ICompanyAllModel {
    @Override
    public List<CompanyAll> getAllCompanySubjects(String api) {
        List<CompanyAll> companyAlls = new ArrayList<CompanyAll>();
        CompanyAll companyAll;
        for (int i = 0; i < 20; i++) {
            companyAll = new CompanyAll("百度" + i, i * 13);
            companyAlls.add(companyAll);
        }
        return companyAlls;
    }
}
