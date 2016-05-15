package com.cwenhui.mark.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by cwenhui on 2016.02.23
 */
public class CompletedPractice implements Parcelable{
    private String completedId;
    private Practice practice;
    private boolean isRight;
    private String userAnswer;
    private Date createTime;
    private String paperIdentifier;           //试卷标识
    private String elapsed;         //耗时

    public CompletedPractice() {
    }

    public String getCompletedId() {
        return completedId;
    }

    public void setCompletedId(String completedId) {
        this.completedId = completedId;
    }

    public Practice getPractice() {
        return practice;
    }

    public void setPractice(Practice practice) {
        this.practice = practice;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setIsRight(boolean isRight) {
        this.isRight = isRight;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPaperIdentifier() {
        return paperIdentifier;
    }

    public void setPaperIdentifier(String paperIdentifier) {
        this.paperIdentifier = paperIdentifier;
    }

    public String getElapsed() {
        return elapsed;
    }

    public void setElapsed(String elapsed) {
        this.elapsed = elapsed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(completedId);
        dest.writeSerializable(practice);
        dest.writeByte((byte) (isRight ? 1 : 0));
        dest.writeString(userAnswer);
        dest.writeLong(createTime.getTime());
        dest.writeString(paperIdentifier);
        dest.writeString(elapsed);
    }

    public static final Parcelable.Creator<CompletedPractice> CREATOR = new Parcelable.Creator<CompletedPractice>(){

        @Override
        public CompletedPractice createFromParcel(Parcel source) {
            return new CompletedPractice(source);
        }

        @Override
        public CompletedPractice[] newArray(int size) {
            return new CompletedPractice[size];
        }
    };

    private CompletedPractice(Parcel in) {
        completedId = in.readString();
        practice = (Practice) in.readSerializable();
        isRight = in.readByte()==1;
        userAnswer = in.readString();
        createTime = new Date(in.readLong());
        paperIdentifier = in.readString();
        elapsed = in.readString();
    }
}
