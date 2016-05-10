package com.cwenhui.mark.entity;

import java.io.Serializable;

/**
 * Created by cwenhui on 2016.02.23
 */
public class PaperDetail implements Serializable {
    private String pdId;
    private Paper paper;
    private SingleProblem singleProblem;
    private MulProblem mulProblem;
    private FillBlankProblem fillBlankProblem;
    private ShortAnswerProblem shortAnswerProblem;
    private int pdMark;

    public PaperDetail() {
    }

    public String getPdId() {
        return pdId;
    }

    public void setPdId(String pdId) {
        this.pdId = pdId;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public SingleProblem getSingleProblem() {
        return singleProblem;
    }

    public void setSingleProblem(SingleProblem singleProblem) {
        this.singleProblem = singleProblem;
    }

    public MulProblem getMulProblem() {
        return mulProblem;
    }

    public void setMulProblem(MulProblem mulProblem) {
        this.mulProblem = mulProblem;
    }

    public FillBlankProblem getFillBlankProblem() {
        return fillBlankProblem;
    }

    public void setFillBlankProblem(FillBlankProblem fillBlankProblem) {
        this.fillBlankProblem = fillBlankProblem;
    }

    public ShortAnswerProblem getShortAnswerProblem() {
        return shortAnswerProblem;
    }

    public void setShortAnswerProblem(ShortAnswerProblem shortAnswerProblem) {
        this.shortAnswerProblem = shortAnswerProblem;
    }

    public int getPdMark() {
        return pdMark;
    }

    public void setPdMark(int pdMark) {
        this.pdMark = pdMark;
    }
}
