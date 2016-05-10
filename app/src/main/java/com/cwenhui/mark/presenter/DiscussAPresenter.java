package com.cwenhui.mark.presenter;

import android.support.v4.app.Fragment;

import com.cwenhui.mark.fragment.DiscussFragment;
import com.cwenhui.mark.view.IDiscussAView;
import com.cwenhui.mark.view.IDiscussView;

import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class DiscussAPresenter {
    private IDiscussAView view;

    public DiscussAPresenter(IDiscussAView view) {
        this.view = view;
    }

    /**
     * 给讨论区的所有板块fragment发送类型，以更新数据，同时设置讨论区类型和隐藏对话框
     * @param fragments
     * @param type
     */
    public void dispatchType2Fragment(List<Fragment> fragments, String type) {
        for (Fragment fragment : fragments) {
            ((DiscussFragment) fragment).switchType(IDiscussView.NEWEST);
        }
        view.setType(type);
        view.dismissDialog();
    }


}
