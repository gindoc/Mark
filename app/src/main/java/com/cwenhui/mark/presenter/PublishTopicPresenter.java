package com.cwenhui.mark.presenter;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cwenhui.mark.MainActivity;
import com.cwenhui.mark.common.TextUtils;
import com.cwenhui.mark.view.IPublishTopicView;

/**
 * Created by cwenhui on 2016.02.23
 */
public class PublishTopicPresenter {
    IPublishTopicView view;

    public PublishTopicPresenter(IPublishTopicView view) {
        this.view = view;
    }

    public void checkPlatesOrTags(Activity activity, LinearLayout llPlates) {
        if(llPlates.getChildCount()<3) {
            // TODO: 2016/5/7 跳转到PlatesActivity添加板块信息
            Toast.makeText(activity, "请设置板块、标签", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(activity, MainActivity.class);
            activity.startActivityForResult(intent, activity.RESULT_FIRST_USER);
        }

    }

    public void isTopicEmpty(EditText topic) {
        if(TextUtils.isEmpty(topic.getText())) {
            view.ShowTips("标题不能为空");
            return;
        }
    }

    public void isContentEmpty(EditText content) {
        if (TextUtils.isEmpty(content.getText())) {
            view.ShowTips("内容不能为空");
            return;
        }
    }
}
