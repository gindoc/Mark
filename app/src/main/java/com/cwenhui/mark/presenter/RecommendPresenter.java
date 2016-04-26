package com.cwenhui.mark.presenter;

import com.cwenhui.mark.model.ICompanyRecommendModel;
import com.cwenhui.mark.model.impl.RecommendModel;
import com.cwenhui.mark.view.ICompanyRecommendView;

/**
 * Created by cwenhui on 2016.02.23
 */
public class RecommendPresenter {
    private ICompanyRecommendModel recommendModel;
    private ICompanyRecommendView recommendView;

    public RecommendPresenter(ICompanyRecommendView recommendView) {
        this.recommendView = recommendView;
        this.recommendModel = new RecommendModel();
    }

    /**
     * 初始化公司推荐套题列表
     */
    public void initRecommendsList() {
        recommendView.initRecommendsList(recommendModel.getRecommends(null));
    }
}
