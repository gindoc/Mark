package com.cwenhui.mark.presenter;

import com.cwenhui.mark.bean.Practice;
import com.cwenhui.mark.model.ISpecialPracticeModel;
import com.cwenhui.mark.model.impl.SpecialPracticeModel;
import com.cwenhui.mark.utils.OnGetListener;
import com.cwenhui.mark.view.ISpecialPracticeView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cwenhui on 2016.02.23
 */
public class SpecialPraticePresenter {
    private ISpecialPracticeView practiceView;
    private ISpecialPracticeModel practiceModel;

    public SpecialPraticePresenter(ISpecialPracticeView practiceView) {
        this.practiceView = practiceView;
        this.practiceModel = new SpecialPracticeModel();
    }

    public void initSpecialPracticeList() {
        practiceModel.getPratices(null, new OnGetListener<Practice>() {
            @Override
            public void onSuccess(Map<String, List<Practice>> ResultSet) {
                practiceView.initQuestionList((HashMap<String, List<Practice>>) ResultSet);
            }
        });
    }

}
