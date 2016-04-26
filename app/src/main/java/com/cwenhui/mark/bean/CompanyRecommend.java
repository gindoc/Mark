package com.cwenhui.mark.bean;

/**
 * Created by cwenhui on 2016.02.23
 */
public class CompanyRecommend {
    private String companyLogo;
    private String companyName;
    private int peopleCnt;
    private int questionCnt;
    private float difficult;

    public CompanyRecommend(String companyLogo, String companyName, int peopleCnt, int questionCnt, float difficult) {
        this.companyLogo = companyLogo;
        this.companyName = companyName;
        this.peopleCnt = peopleCnt;
        this.questionCnt = questionCnt;
        this.difficult = difficult;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getPeopleCnt() {
        return peopleCnt;
    }

    public void setPeopleCnt(int peopleCnt) {
        this.peopleCnt = peopleCnt;
    }

    public int getQuestionCnt() {
        return questionCnt;
    }

    public void setQuestionCnt(int questionCnt) {
        this.questionCnt = questionCnt;
    }

    public float getDifficult() {
        return difficult;
    }

    public void setDifficult(float difficult) {
        this.difficult = difficult;
    }
}
