package com.cwenhui.mark.configs;

/**
 * Created by cwenhui on 2016.02.23
 */
public class Constant {
    //图片缓存地址<i>("/Android/data/[app_package_name]/cache/dirName")</i>
    public static final String IMAGE_PATH = "images";

    public static final int PULL_DOWN = 1;      //下拉
    public static final int PULL_UP = 2;        //shangla

    public static final String OPTIONS_SPECIAL_CHARS = "&*@$&";     //选择题每个选项的分隔符
    public static final String SPLIT_OPTIONS = "\\&\\*\\@\\$\\&";     //选择题每个选项的分隔符
    public static final int SINGLE_PROBLEM = 101;           //单选题的type
    public static final int MULTIPUL_PROBLEM = 102;         //多选题的type
    public static final int FILL_BLANK_PROBLEM = 103;
    public static final int SHORT_ANSWER_PROBLEM = 104;
}
