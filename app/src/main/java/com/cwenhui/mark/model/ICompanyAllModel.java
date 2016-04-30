package com.cwenhui.mark.model;

import com.cwenhui.mark.bean.CompanyAll;
import com.cwenhui.mark.common.OnGetListener;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface ICompanyAllModel {
    public static final int PULL_DOWN = 1;
    public static final int PULL_UP = 2;

//    interface OnGetListener{
//        void onSuccess(List<CompanyAll> companyAlls);
//        void onFailure();
//    }

    /**
     * 调用api查询所有公司套题
     * @param api
     * @return
     */
//    public void getAllCompanySubjects(String api, OnGetListener getListener);
    public void getAllCompanySubjects(String api, OnGetListener<CompanyAll> getListener);

    /**
     * 根据是上拉或下拉刷新数据
     * @param api
     * @param direction
     * @return
     */
//    public List<CompanyAll> refleshAllSpecialSubjects(String api, int direction, OnGetListener getListener);
    public void refleshAllCompanySubjects(String api, int direction,
                                                      OnGetListener<CompanyAll> getListener);
}
