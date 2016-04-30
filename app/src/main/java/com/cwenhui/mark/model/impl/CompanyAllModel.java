package com.cwenhui.mark.model.impl;

import android.util.Log;

import com.cwenhui.mark.bean.CompanyAll;
import com.cwenhui.mark.model.ICompanyAllModel;
import com.cwenhui.mark.utils.OnGetListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class CompanyAllModel implements ICompanyAllModel {

    private static final String TAG = "CompanyAllModel";

    @Override
    public void getAllCompanySubjects(String api, final OnGetListener<CompanyAll> getListener) {
        new Thread(new Runnable() {     //模拟网络耗时请求
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    Log.e(TAG, "SLEEP");
                    List<CompanyAll> companyAlls = new ArrayList<CompanyAll>();
                    CompanyAll companyAll;
                    for (int i = 0; i < 15; i++) {
                        companyAll = new CompanyAll("百度" + i, i * 13);
                        companyAlls.add(companyAll);
                    }
                    getListener.onSuccess(companyAlls);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void refleshAllCompanySubjects(String api, final int direction,
                                                            final OnGetListener getListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<CompanyAll> companyAlls = new ArrayList<CompanyAll>();
                CompanyAll companyAll;
                if (direction == PULL_DOWN) {
                    for (int i = 0; i < 20; i++) {
                        companyAll = new CompanyAll("百度" + i, i * 13);
                        companyAlls.add(companyAll);
                    }
                }else if (direction == PULL_UP) {
                    Log.e(TAG, "PULL_UP");
                    for (int i = 0; i < 20; i++) {
                        companyAll = new CompanyAll("百度" + i * 5, i * 5 + 6);
                        companyAlls.add(companyAll);
                    }
                }
                getListener.onSuccess(companyAlls);
            }
        }).start();

    }
}
