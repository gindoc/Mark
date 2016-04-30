package com.cwenhui.mark.model.impl;

import com.cwenhui.mark.bean.Practice;
import com.cwenhui.mark.model.ISpecialPracticeModel;
import com.cwenhui.mark.utils.OnGetListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class SpecialPracticeModel implements ISpecialPracticeModel {
    private String[] groupName = new String[]{"数据结构", "软件开发", "计算机基础", "算法", "编程语言"};
    private String[] childName = new String[]{"数组","字符串","链表","栈","队列",
            "树","java","C","VB","PHP","软件工程","ACM","数字与逻辑","剑指Office","编程之美"};
    @Override
    public void getPratices(String api, final OnGetListener<Practice> getListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, List<Practice>> pratices = new HashMap<String, List<Practice>>();
                List<Practice> group;
                Practice child;
                for (int j = 0; j < 5; j++) {
                    group = new ArrayList<Practice>();
                    for (int i = 0; i < 15; i++) {
                        child = new Practice(childName[i], i * 52 + 5, i * 32, (float) Math.random());
                        group.add(child);
                    }
                    pratices.put(groupName[j], group);
                }
                getListener.onSuccess(pratices);
            }
        }).start();
    }
}
