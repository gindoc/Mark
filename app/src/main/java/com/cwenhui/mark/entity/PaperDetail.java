package com.cwenhui.mark.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cwenhui on 2016.02.23
 */
public class PaperDetail implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pdId);
        dest.writeSerializable(paper);
        dest.writeSerializable(singleProblem);
        dest.writeSerializable(mulProblem);
        dest.writeSerializable(fillBlankProblem);
        dest.writeSerializable(shortAnswerProblem);
        dest.writeInt(pdMark);
    }

    public static final Parcelable.Creator<PaperDetail> CREATOR = new Parcelable.Creator<PaperDetail>(){

        @Override
        public PaperDetail createFromParcel(Parcel in) {
            return new PaperDetail(in);				//从Parcel中读取数据的顺序要和写入Parcel中的顺序一样(即和writeToParcel()方法中的写入保持一样的顺序),否则会报错
        }

        @Override
        public PaperDetail[] newArray(int size) {
            return new PaperDetail[size];
        }

    };

    private PaperDetail(Parcel in) {
        pdId = in.readString();
        paper = (Paper) in.readSerializable();
        singleProblem = (SingleProblem) in.readSerializable();
        mulProblem = (MulProblem) in.readSerializable();
        fillBlankProblem = (FillBlankProblem) in.readSerializable();
        shortAnswerProblem = (ShortAnswerProblem) in.readSerializable();
        pdMark = in.readInt();
    }
}
