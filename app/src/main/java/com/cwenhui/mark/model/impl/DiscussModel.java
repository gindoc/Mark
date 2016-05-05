package com.cwenhui.mark.model.impl;

import com.cwenhui.mark.bean.Discuss;
import com.cwenhui.mark.bean.DiscussCategory;
import com.cwenhui.mark.common.OnGetListener;
import com.cwenhui.mark.model.IDiscussModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class DiscussModel implements IDiscussModel {
    @Override
    public void initDisgussList(String api, final OnGetListener<Discuss> getListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<Discuss> discusses = new ArrayList<>();
                Discuss discuss = new Discuss("http://www.cwenhui.com/images/default_member.jpg",
                        "雾里看花","最后回复7天前", 1, "念念不忘，必有回响", "带上自己的阳光，做真实的自己，" +
                        "不以物喜，不以己悲，真诚坦荡生活每一天。流水人生转瞬即逝，每一天我们都像蝼蚁一样在忙碌，" +
                        "被生活压顶。为名忙，为利忙，忙忙碌碌何时休，多少前缘成了过往，其实抓不住的是潺潺时光。");
                DiscussCategory category = new DiscussCategory(1, "随便聊聊");
                discuss.setCategory(category);
                discusses.add(discuss);

                discuss = new Discuss("http://www.cwenhui.com/images/default_member.jpg",
                        "雾里看花","最后回复7天前", 0, "念念不忘，必有回响", "带上自己的阳光，做真实的自己，" +
                        "不以物喜，不以己悲，真诚坦荡生活每一天。流水人生转瞬即逝，每一天我们都像蝼蚁一样在忙碌，" +
                        "被生活压顶。为名忙，为利忙，忙忙碌碌何时休，多少前缘成了过往，其实抓不住的是潺潺时光。");
                discuss.setCategory(category);
                for (int i = 0; i < 10; i++) {
                    discusses.add(discuss);
                }
                getListener.onSuccess(discusses);
            }
        }).start();
    }

    @Override
    public void reflesh(String api, int direction, final OnGetListener<Discuss> getListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<Discuss> discusses = new ArrayList<>();
                Discuss discuss = new Discuss("http://www.cwenhui.com/images/default_member.jpg",
                        "雾里看花","最后回复7天前", 1, "念念不忘，必有回响2", "带上自己的阳光，做真实的自己，" +
                        "不以物喜，不以己悲，真诚坦荡生活每一天。流水人生转瞬即逝，每一天我们都像蝼蚁一样在忙碌，" +
                        "被生活压顶。为名忙，为利忙，忙忙碌碌何时休，多少前缘成了过往，其实抓不住的是潺潺时光。");
                DiscussCategory category = new DiscussCategory(1, "随便聊聊");
                discuss.setCategory(category);
                discusses.add(discuss);

                for (int i = 0; i < 2; i++) {
                    discuss = new Discuss("http://www.cwenhui.com/images/default_member.jpg",
                            "雾里看花","最后回复"+i+"天前", 0, "念念不忘，必有回响"+i, "带上自己的阳光，做真实的自己，" +
                            "不以物喜，不以己悲，真诚坦荡生活每一天。流水人生转瞬即逝，每一天我们都像蝼蚁一样在忙碌，" +
                            "被生活压顶。为名忙，为利忙，忙忙碌碌何时休，多少前缘成了过往，其实抓不住的是潺潺时光。");
                    discuss.setCategory(category);
                    discusses.add(discuss);
                }
                getListener.onSuccess(discusses);
            }
        }).start();
    }

//    @Override
//    public void initDisgussList(String api) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                List<Discuss> discusses = new ArrayList<>();
//                Discuss discuss = new Discuss("http://www.cwenhui.com/images/default_member.jpg",
//                        "雾里看花","最后回复7天前", 1, "念念不忘，必有回响2", "带上自己的阳光，做真实的自己，" +
//                        "不以物喜，不以己悲，真诚坦荡生活每一天。流水人生转瞬即逝，每一天我们都像蝼蚁一样在忙碌，" +
//                        "被生活压顶。为名忙，为利忙，忙忙碌碌何时休，多少前缘成了过往，其实抓不住的是潺潺时光。");
//                DiscussCategory category = new DiscussCategory(1, "随便聊聊");
//                discuss.setCategory(category);
//                discusses.add(discuss);
//
//                discuss = new Discuss("http://www.cwenhui.com/images/default_member.jpg",
//                        "雾里看花","最后回复7天前", 0, "念念不忘，必有回响2", "带上自己的阳光，做真实的自己，" +
//                        "不以物喜，不以己悲，真诚坦荡生活每一天。流水人生转瞬即逝，每一天我们都像蝼蚁一样在忙碌，" +
//                        "被生活压顶。为名忙，为利忙，忙忙碌碌何时休，多少前缘成了过往，其实抓不住的是潺潺时光。");
//                discuss.setCategory(category);
//                for (int i = 0; i < 2; i++) {
//                    discusses.add(discuss);
//                }
//                EventBus.getDefault().post(discusses);
//            }
//        }).start();
//    }
//
//    @Override
//    public void reflesh(String api, int directon) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                List<Discuss> discusses = new ArrayList<>();
//                Discuss discuss = new Discuss("http://www.cwenhui.com/images/default_member.jpg",
//                        "雾里看花","最后回复7天前", 1, "念念不忘，必有回响2", "带上自己的阳光，做真实的自己，" +
//                        "不以物喜，不以己悲，真诚坦荡生活每一天。流水人生转瞬即逝，每一天我们都像蝼蚁一样在忙碌，" +
//                        "被生活压顶。为名忙，为利忙，忙忙碌碌何时休，多少前缘成了过往，其实抓不住的是潺潺时光。");
//                DiscussCategory category = new DiscussCategory(1, "随便聊聊");
//                discuss.setCategory(category);
//                discusses.add(discuss);
//
//                for (int i = 0; i < 2; i++) {
//                    discuss = new Discuss("http://www.cwenhui.com/images/default_member.jpg",
//                            "雾里看花","最后回复"+i+"天前", 0, "念念不忘，必有回响"+i, "带上自己的阳光，做真实的自己，" +
//                            "不以物喜，不以己悲，真诚坦荡生活每一天。流水人生转瞬即逝，每一天我们都像蝼蚁一样在忙碌，" +
//                            "被生活压顶。为名忙，为利忙，忙忙碌碌何时休，多少前缘成了过往，其实抓不住的是潺潺时光。");
//                    discuss.setCategory(category);
//                    discusses.add(discuss);
//                }
//                EventBus.getDefault().post(discusses);
//            }
//        }).start();
//    }
}
