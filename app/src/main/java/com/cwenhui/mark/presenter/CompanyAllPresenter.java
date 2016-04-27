package com.cwenhui.mark.presenter;

import android.os.Handler;

import com.cwenhui.mark.bean.CompanyAll;
import com.cwenhui.mark.model.ICompanyAllModel;
import com.cwenhui.mark.model.impl.CompanyAllModel;
import com.cwenhui.mark.utils.OnGetListener;
import com.cwenhui.mark.view.ICompanyAllView;

import java.util.Collection;
import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class CompanyAllPresenter {
    private static final String TAG = "CompanyAllPresenter";
    private ICompanyAllView companyAllView;
    private ICompanyAllModel companyAllModel;
    private Handler mHandler = new Handler();

    public CompanyAllPresenter(ICompanyAllView companyAllView) {
        this.companyAllView = companyAllView;
        this.companyAllModel = new CompanyAllModel();
    }

    /**
     * 初始化全部公司套题列表
     */
    public void initCompanyAllsList() {
//        companyAllView.showLoading();
        companyAllModel.getAllCompanySubjects(null, new OnGetListener<CompanyAll>() {
            @Override
            public void onSuccess(final Collection<CompanyAll> companyAlls) {
                //此时处于子线程,需要在UI线程执行,这点很重要
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        companyAllView.initCompanyAllsList((List<CompanyAll>) companyAlls);
                        companyAllView.hideLoading();
                    }
                });
            }
        });
    }

    /**
     * 下拉刷新、上拉加载
     * @param direction 方向（ICompanyAllModel.PULL_DOWN、ICompanyAllModel.PULL_UP）
     */
    public void reflesh(final int direction) {
        companyAllModel.refleshAllCompanySubjects(null, direction, new OnGetListener<CompanyAll>() {
            @Override
            public void onSuccess(final Collection<CompanyAll> companyAlls) {
                //此时处于子线程,需要在UI线程执行,这点很重要
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (direction == ICompanyAllModel.PULL_DOWN){
                            companyAllView.initCompanyAllsList((List<CompanyAll>) companyAlls);
                        }
                        else{
                            companyAllView.refleshCompanyAllsList((List<CompanyAll>) companyAlls);
                        }
                        companyAllView.hideLoading();
                    }
                });
            }
        });

    }
}
