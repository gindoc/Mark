package com.cwenhui.mark.view;

import com.cwenhui.mark.entity.PaperDetail;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface IKnowledgePointView {

    /**
     * 为ViewPager设置适配器
     * @param paperDetails  传递给KnowledgePointFragment的试题（PaperDetail）
     */
    void setupViewPager(List<PaperDetail> paperDetails);
}
