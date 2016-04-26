package com.cwenhui.mark.bean;

/**
 * Created by cwenhui on 2016.02.23
 */
public class CompanyAll {
    private String companyName; //公司名
    private int questionAnt;    //套题数量

    public CompanyAll(String companyName, int questionAnt) {
        this.companyName = companyName;
        this.questionAnt = questionAnt;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getQuestionAnt() {
        return questionAnt;
    }

    public void setQuestionAnt(int questionAnt) {
        this.questionAnt = questionAnt;
    }
}
