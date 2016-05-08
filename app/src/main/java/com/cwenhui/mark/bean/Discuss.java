package com.cwenhui.mark.bean;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class Discuss {
    private String dcsImg;
    private String dcsName;
    private String publishTime;
    private DiscussCategory category;
    private int level;          //0 置顶  1 精华
    private String topic;       //
    private String content;
    private List<String> tags;

    public Discuss(String dcsImg, String dcsName, String publishTime, int level, String topic, String content) {
        this.dcsImg = dcsImg;
        this.dcsName = dcsName;
        this.publishTime = publishTime;
        this.level = level;
        this.topic = topic;
        this.content = content;
    }

    public String getDcsImg() {
        return dcsImg;
    }

    public void setDcsImg(String dcsImg) {
        this.dcsImg = dcsImg;
    }

    public String getDcsName() {
        return dcsName;
    }

    public void setDcsName(String dcsName) {
        this.dcsName = dcsName;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DiscussCategory getCategory() {
        return category;
    }

    public void setCategory(DiscussCategory category) {
        this.category = category;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
