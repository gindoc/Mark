package com.cwenhui.mark.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.cwenhui.mark.MainActivity;
import com.cwenhui.mark.R;
import com.cwenhui.mark.fragment.IndexFragment;
import com.cwenhui.mark.view.IMainView;

/**
 * Created by cwenhui on 2016.02.23
 */
public class MainPresenter {
//    private IMainModel mainModel;
    private IMainView mainView;
    private Fragment IndexFragment;

    public MainPresenter(IMainView mainView) {
        this.mainView = mainView;
//        this.mainModel = new MainModel();
    }

    public void setSelect(MainActivity activity, int index) {
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        // 把图片设置为亮的
        // 设置内容区域
        switch (index)
        {
            case 0:
                if (IndexFragment == null)
                {
                    IndexFragment = new IndexFragment();
                    transaction.add(R.id.fl_activity_main_container, IndexFragment);
                } else
                {
                    transaction.show(IndexFragment);
                }
                mainView.setStudyImage(R.drawable.icon_main_study_selected);
                break;
            /*case 1:
                if (mTab02 == null)
                {
                    mTab02 = new FrdFragment();transaction.add(R.id.id_content, mTab02);
                } else
                {
                    transaction.show(mTab02);

                }
                mImgFrd.setImageResource(R.drawable.tab_find_frd_pressed);
                break;
            case 2:
                if (mTab03 == null)
                {
                    mTab03 = new AddressFragment();
                    transaction.add(R.id.id_content, mTab03);
                } else
                {
                    transaction.show(mTab03);
                }
                mImgAddress.setImageResource(R.drawable.tab_address_pressed);
                break;
            case 3:
                if (mTab04 == null)
                {
                    mTab04 = new SettingFragment();
                    transaction.add(R.id.id_content, mTab04);
                } else
                {
                    transaction.show(mTab04);
                }
                mImgSettings.setImageResource(R.drawable.tab_settings_pressed);
                break;*/

            default:
                break;
        }

        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction)
    {
        if (IndexFragment != null)
        {
            transaction.hide(IndexFragment);
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
