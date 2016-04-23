package com.cwenhui.mark;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.cwenhui.mark.presenter.MainPresenter;
import com.cwenhui.mark.view.IMainView;

public class MainActivity extends AppCompatActivity implements IMainView, View.OnClickListener{
    private static final String TAG = "MainActivity";
    private LinearLayout tabStudy, tabCommunity, tabMessage, tabProfile;
    private ImageButton btnStudy, btnCommunity, btnMessage, btnProfile;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
    }

    private void initEvent() {
        tabStudy.setOnClickListener(this);
        tabCommunity.setOnClickListener(this);
        tabMessage.setOnClickListener(this);
        tabProfile.setOnClickListener(this);
    }

    private void initView() {
        presenter = new MainPresenter(this);
        tabStudy = (LinearLayout) findViewById(R.id.ll_activity_main_bottom_study);
        tabCommunity = (LinearLayout) findViewById(R.id.ll_activity_main_bottom_community);
        tabMessage = (LinearLayout) findViewById(R.id.ll_activity_main_bottom_message);
        tabProfile = (LinearLayout) findViewById(R.id.ll_activity_main_bottom_profile);
        btnStudy = (ImageButton) findViewById(R.id.ib_activity_main_bottom_study);
        btnMessage = (ImageButton) findViewById(R.id.ib_activity_main_bottom_message);
        btnCommunity = (ImageButton) findViewById(R.id.ib_activity_main_bottom_community);
        btnProfile = (ImageButton) findViewById(R.id.ib_activity_main_bottom_profile);
        presenter.setSelect(this,0);
    }

    @Override
    public void setStudyImage(int resId) {
        btnStudy.setImageResource(resId);
    }

    @Override
    public void setCommunityImage(int resId) {
        btnCommunity.setImageResource(resId);
    }

    @Override
    public void setMessageImage(int resId) {
        btnMessage.setImageResource(resId);
    }

    @Override
    public void setProfileImage(int resId) {
        btnProfile.setImageResource(resId);
    }


    @Override
    public void onClick(View v) {
        presenter.resetImgs();
        switch (v.getId()) {
            case R.id.ll_activity_main_bottom_study:
                presenter.setSelect(this,0);
                break;
            case R.id.ll_activity_main_bottom_community:
                presenter.setSelect(this, 1);
                break;
        }
    }
}
