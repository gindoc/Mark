package com.cwenhui.mark.view;


import com.cwenhui.mark.entity.Practice;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public interface IExaminationView {
    /**
     * 为ViewPager设置适配器
     * @param practices  传递给Fragment的试题（Practice）
     */
    void setupViewPager(List<Practice> practices);
}
