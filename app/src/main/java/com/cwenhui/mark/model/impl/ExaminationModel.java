package com.cwenhui.mark.model.impl;

import com.cwenhui.mark.common.OnResponseListener;
import com.cwenhui.mark.configs.Constant;
import com.cwenhui.mark.entity.Practice;
import com.cwenhui.mark.entity.PracticeCategory;
import com.cwenhui.mark.model.IExaminationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class ExaminationModel implements IExaminationModel {
    @Override
    public void getPaperDetail(String api, final OnResponseListener<Practice> getListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<Practice> practices = new ArrayList<Practice>();
                PracticeCategory category = new PracticeCategory();
                category.setCategoryName("数组");
                for (int i = 0; i < 7; i++) {
                    Practice practice = new Practice();
                    practice.setPraticeTitle("下列哪项不是链表优于数组的特点？" + i);
                    practice.setPraticeOptions("方便删除" + Constant.OPTIONS_SPECIAL_CHARS + "方便插入" +
                            Constant.OPTIONS_SPECIAL_CHARS + "长度可变" + Constant.OPTIONS_SPECIAL_CHARS + "存储空间小");
                    practice.setPraticeAnswer("A");
                    practice.setPraticeType(Constant.SINGLE_PROBLEM);
                    practice.setCategory(category);
                    practices.add(practice);
                }
                for (int i = 0; i < 2; i++) {
                    Practice practice = new Practice();
                    practice.setPraticeTitle("下列哪项不是链表优于数组的特点？" + i);
                    practice.setPraticeOptions("方便删除" + Constant.OPTIONS_SPECIAL_CHARS + "方便插入" +
                            Constant.OPTIONS_SPECIAL_CHARS + "长度可变" + Constant.OPTIONS_SPECIAL_CHARS + "存储空间小");
                    practice.setPraticeAnswer("A" + Constant.OPTIONS_SPECIAL_CHARS + "B");
                    practice.setCategory(category);
                    practice.setPraticeType(Constant.MULTIPUL_PROBLEM);
                    practices.add(practice);
                }
                Practice practice = new Practice();
                practice.setPraticeTitle("测试填空题1" + Constant.OPTIONS_SPECIAL_CHARS + "测试填空题2" +
                        Constant.OPTIONS_SPECIAL_CHARS + "测试填空题3");
                practice.setPraticeAnswer("A" + Constant.OPTIONS_SPECIAL_CHARS + "B");
                practice.setCategory(category);
                practice.setPraticeType(Constant.FILL_BLANK_PROBLEM);
                practices.add(practice);
                getListener.onSuccess(practices);
            }
        }).start();
    }
}
