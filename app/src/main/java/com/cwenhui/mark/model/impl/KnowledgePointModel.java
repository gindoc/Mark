package com.cwenhui.mark.model.impl;

import com.cwenhui.mark.common.OnGetListener;
import com.cwenhui.mark.entity.Course;
import com.cwenhui.mark.entity.FillBlankProblem;
import com.cwenhui.mark.entity.MulProblem;
import com.cwenhui.mark.entity.Paper;
import com.cwenhui.mark.entity.PaperDetail;
import com.cwenhui.mark.entity.SingleProblem;
import com.cwenhui.mark.model.IKnowledgePointModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class KnowledgePointModel implements IKnowledgePointModel {
    @Override
    public void getPaperDetail(String api, final OnGetListener<PaperDetail> getListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<PaperDetail> paperDetails = new ArrayList<PaperDetail>();
                Course course = new Course();
                course.setCourseId("123456");
                course.setCourseName("数组");

                Paper paper = new Paper();
                paper.setCourse(course);
                paper.setPaperId("123");
                paper.setPaperName("数组专项练习");
                paper.setPaperState(false);

                for (int i = 0; i < 7; i++) {
                    PaperDetail paperDetail = new PaperDetail();
                    paperDetail.setPaper(paper);
                    SingleProblem singleProblem = new SingleProblem();
                    singleProblem.setCourse(course);
                    singleProblem.setSpTitle("下列哪项不是链表优于数组的特点？" + i);
                    singleProblem.setSpAnswerA("方便删除");
                    singleProblem.setSpAnswerB("方便插入");
                    singleProblem.setSpAnswerC("长度可变");
                    singleProblem.setSpAnswerD("存储空间小");
                    singleProblem.setSpAnswer("D");
                    paperDetail.setSingleProblem(singleProblem);
                    paperDetails.add(paperDetail);
                }
                for (int i = 0; i < 2; i++) {
                    PaperDetail paperDetail = new PaperDetail();
                    paperDetail.setPaper(paper);
                    MulProblem mulProblem = new MulProblem();
                    mulProblem.setCourse(course);
                    mulProblem.setMpTitle("下列哪项不是链表优于数组的特点？" + i);
                    mulProblem.setMpAnswerA("方便删除");
                    mulProblem.setMpAnswerB("方便插入");
                    mulProblem.setMpAnswerC("长度可变");
                    mulProblem.setMpAnswerD("存储空间小");
                    mulProblem.setMpAnswer("A C D");
                    paperDetail.setMulProblem(mulProblem);
                    paperDetails.add(paperDetail);
                }
                FillBlankProblem fillBlankProblem = new FillBlankProblem();
                fillBlankProblem.setCourse(course);
                fillBlankProblem.setFbFrontTitle("测试填空题的前半部分");
                fillBlankProblem.setFbBackTitle("测试填空题的后半部分");
                fillBlankProblem.setFbAnswer("这是答案");
                PaperDetail paperDetail = new PaperDetail();
                paperDetail.setPaper(paper);
                paperDetail.setFillBlankProblem(fillBlankProblem);
                paperDetails.add(paperDetail);
                getListener.onSuccess(paperDetails);
            }
        }).start();
    }
}
