package com.cwenhui.mark.model;

import com.cwenhui.mark.bean.CompanyAll;
import com.cwenhui.mark.common.OnResponseListener;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface ICompanyAllModel {
    public static final int PULL_DOWN = 1;
    public static final int PULL_UP = 2;

//    interface OnResponseListener{
//        void onSuccess(List<CompanyAll> companyAlls);
//        void onFailure();
//    }

    /**
     * 调用api查询所有公司套题
     * @param api
     * @return
     */
//    public void getAllCompanySubjects(String api, OnResponseListener getListener);
    public void getAllCompanySubjects(String api, OnResponseListener<CompanyAll> getListener);

    /**
     * 根据是上拉或下拉刷新数据
     * @param api
     * @param direction
     * @return
     */
//    public List<CompanyAll> refleshAllSpecialSubjects(String api, int direction, OnResponseListener getListener);
    public void refleshAllCompanySubjects(String api, int direction,
                                                      OnResponseListener<CompanyAll> getListener);
}
