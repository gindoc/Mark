package com.cwenhui.mark.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.cwenhui.mark.MainActivity;
import com.cwenhui.mark.R;
import com.cwenhui.mark.fragment.CommunityFragment;
import com.cwenhui.mark.fragment.IndexFragment;
import com.cwenhui.mark.fragment.MessageFragment;
import com.cwenhui.mark.fragment.ProfileFragment;
import com.cwenhui.mark.view.IMainView;

/**
 * Created by cwenhui on 2016.02.23
 */
public class MainPresenter {
    private IMainView mainView;
    private Fragment indexFragment, communityFragment, messageFragment, profileFragment;

    public MainPresenter(IMainView mainView) {
        this.mainView = mainView;
    }

    /**
     * 切换fragment
     * @param activity
     * @param index 被点击的下标
     */
    public void setSelect(MainActivity activity, int index) {
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        // 把图片设置为亮的
        // 设置内容区域
        switch (index)
        {
            case 0:
                if (indexFragment == null)
                {
                    indexFragment = new IndexFragment();
                    transaction.add(R.id.fl_activity_main_container, indexFragment);
                } else
                {
                    transaction.show(indexFragment);
                }
                mainView.setStudyImage(R.drawable.icon_main_study_selected);
                break;
            case 1:
                if (communityFragment == null)
                {
                    communityFragment = new CommunityFragment();
                    transaction.add(R.id.fl_activity_main_container, communityFragment);
                } else
                {
                    transaction.show(communityFragment);

                }
                mainView.setCommunityImage(R.drawable.icon_main_community_selected);
                break;
            case 2:
                if (messageFragment == null)
                {
                    messageFragment = new MessageFragment();
                    transaction.add(R.id.fl_activity_main_container, messageFragment);
                } else
                {
                    transaction.show(messageFragment);
                }
                mainView.setMessageImage(R.drawable.icon_main_message_selected);
                break;
            case 3:
                if (profileFragment == null)
                {
                    profileFragment = new ProfileFragment();
                    transaction.add(R.id.fl_activity_main_container, profileFragment);
                } else
                {
                    transaction.show(profileFragment);
                }
                mainView.setProfileImage(R.drawable.icon_main_profile_selected);
                break;

            default:
                break;
        }

        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction)
    {
        if (indexFragment != null)
        {
            transaction.hide(indexFragment);
        }
        if (communityFragment != null) {
            transaction.hide(communityFragment);
        }
        if (messageFragment != null) {
            transaction.hide(messageFragment);
        }
        if (profileFragment != null) {
            transaction.hide(profileFragment);
        }
    }

    /**
     * 切换图片至暗色
     */
    public void resetImgs()
    {
        mainView.setStudyImage(R.drawable.icon_main_study);
        mainView.setCommunityImage(R.drawable.icon_main_community);
        mainView.setMessageImage(R.drawable.icon_main_message);
        mainView.setProfileImage(R.drawable.icon_main_profile);
    }
}
