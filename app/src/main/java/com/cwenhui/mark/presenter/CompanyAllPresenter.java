package com.cwenhui.mark.presenter;

import com.cwenhui.mark.model.ICompanyAllModel;
import com.cwenhui.mark.model.impl.CompanyAllModel;
import com.cwenhui.mark.view.ICompanyAllView;

/**
 * Created by cwenhui on 2016.02.23
 */
public class CompanyAllPresenter {
    private ICompanyAllView companyAllView;
    private ICompanyAllModel companyAllModel;

    public CompanyAllPresenter(ICompanyAllView companyAllView) {
        this.companyAllView = companyAllView;
        this.companyAllModel = new CompanyAllModel();
    }

    public void initCompanyAllsList() {
        companyAllView.initCompanyAllsList(companyAllModel.getAllCompanySubjects(null));
    }
}
