package com.cwenhui.mark.model.impl;

import com.cwenhui.mark.common.OnResponseListener;
import com.cwenhui.mark.configs.Constant;
import com.cwenhui.mark.entity.CompletedPractice;
import com.cwenhui.mark.entity.Practice;
import com.cwenhui.mark.entity.PracticeCategory;
import com.cwenhui.mark.model.IProblemModel;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class ProblemModel implements IProblemModel {
    @Override
    public void submitAnswerForExamination(String api, RequestParams params,
                                           final OnResponseListener<String> postListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //向服务器提交数据。。。
                postListener.onSuccess("success");
            }
        }).start();
    }

    @Override
    public void getCompletedPratices(String api, final OnResponseListener<CompletedPractice> getListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //此处模拟从服务器获得已做题目
                PracticeCategory category = new PracticeCategory();
                category.setCategoryName("数组");
                Practice practice = new Practice();     //单选题
                practice.setPraticeTitle("下列哪项不是链表优于数组的特点？");
                practice.setPraticeOptions("方便删除" + Constant.OPTIONS_SPECIAL_CHARS + "方便插入" +
                        Constant.OPTIONS_SPECIAL_CHARS + "长度可变" + Constant.OPTIONS_SPECIAL_CHARS + "存储空间小");
                practice.setPraticeAnswer("A");
                practice.setPraticeType(Constant.SINGLE_PROBLEM);
                practice.setCategory(category);
                List<CompletedPractice> cps = new ArrayList<CompletedPractice>();
                for (int i = 0; i < 6; i++) {
                    CompletedPractice cp = new CompletedPractice();     //对题
                    cp.setUserAnswer("A");
                    cp.setElapsed("425");
                    cp.setIsRight(true);
                    cp.setPaperIdentifier("1");
                    cp.setPractice(practice);
                    cp.setCreateTime(new Date());
                    cps.add(cp);
                }
                CompletedPractice cp = new CompletedPractice(); //错题
                cp.setUserAnswer("B");
                cp.setElapsed("425");
                cp.setIsRight(false);
                cp.setPaperIdentifier("1");
                cp.setPractice(practice);
                cp.setCreateTime(new Date());
                cps.add(cp);

                practice = new Practice();      //多选题
                practice.setPraticeTitle("下列哪项不是链表优于数组的特点？");
                practice.setPraticeOptions("方便删除" + Constant.OPTIONS_SPECIAL_CHARS + "方便插入" +
                        Constant.OPTIONS_SPECIAL_CHARS + "长度可变" + Constant.OPTIONS_SPECIAL_CHARS + "存储空间小");
                practice.setPraticeAnswer("A" + Constant.OPTIONS_SPECIAL_CHARS + "B");
                practice.setCategory(category);
                practice.setPraticeType(Constant.MULTIPUL_PROBLEM);

                cp = new CompletedPractice();     //对题
                cp.setUserAnswer("A" + Constant.OPTIONS_SPECIAL_CHARS + "B");
                cp.setElapsed("425");
                cp.setIsRight(true);
                cp.setPaperIdentifier("1");
                cp.setPractice(practice);
                cp.setCreateTime(new Date());
                cps.add(cp);

                cp = new CompletedPractice();     //错题
                cp.setUserAnswer("A");
                cp.setElapsed("425");
                cp.setIsRight(false);
                cp.setPaperIdentifier("1");
                cp.setPractice(practice);
                cp.setCreateTime(new Date());
                cps.add(cp);

                practice = new Practice();          //填空题
                practice.setPraticeTitle("测试填空题1" + Constant.OPTIONS_SPECIAL_CHARS + "测试填空题2" +
                        Constant.OPTIONS_SPECIAL_CHARS + "测试填空题3");
                practice.setPraticeAnswer("A" + Constant.OPTIONS_SPECIAL_CHARS + "B");
                practice.setCategory(category);
                practice.setPraticeType(Constant.FILL_BLANK_PROBLEM);

                cp = new CompletedPractice();       //对题
                cp.setUserAnswer("A" + Constant.OPTIONS_SPECIAL_CHARS + "B");
                cp.setElapsed("425");
                cp.setIsRight(true);
                cp.setPaperIdentifier("1");
                cp.setPractice(practice);
                cp.setCreateTime(new Date());
                cps.add(cp);

                getListener.onSuccess(cps);
            }
        }).start();
    }
}
