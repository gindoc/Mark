package com.cwenhui.mark.bean;

/**
 * Created by cwenhui on 2016.02.23
 */
public class Practice {
    private String practiceName;    //练习题类型
    private int quesCnt;            //总共题数
    private int doneCnt;            //解决的题数
    private float accuracy;         //正确率

    public Practice(String practiceName, int quesCnt, int doneCnt, float accuracy) {
        this.practiceName = practiceName;
        this.quesCnt = quesCnt;
        this.doneCnt = doneCnt;
        this.accuracy = accuracy;
    }

    public String getPracticeName() {
        return practiceName;
    }

    public void setPracticeName(String practiceName) {
        this.practiceName = practiceName;
    }

    public int getQuesCnt() {
        return quesCnt;
    }

    public void setQuesCnt(int quesCnt) {
        this.quesCnt = quesCnt;
    }

    public int getDoneCnt() {
        return doneCnt;
    }

    public void setDoneCnt(int doneCnt) {
        this.doneCnt = doneCnt;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }
}
