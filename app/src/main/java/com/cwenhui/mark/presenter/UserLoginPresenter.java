package com.cwenhui.mark.presenter;

import android.os.Handler;

import com.cwenhui.mark.bean.User;
import com.cwenhui.mark.model.IUserModel;
import com.cwenhui.mark.model.OnLoginListener;
import com.cwenhui.mark.model.impl.UserModel;
import com.cwenhui.mark.view.IUserLoginView;

public class UserLoginPresenter
{
    private IUserModel userModel;
    private IUserLoginView userLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView)
    {
        this.userLoginView = userLoginView;
        this.userModel = new UserModel();
    }

    public void login()
    {
        userLoginView.showLoading();
        userModel.login(userLoginView.getUserName(), userLoginView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(final User user) {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.toMainActivity(user);
                        userLoginView.hideLoading();
                    }
                });

            }

            @Override
            public void loginFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        userLoginView.showFailedError();
                        userLoginView.hideLoading();
                    }
                });

            }
        });
    }

    public void clear()
    {
        userLoginView.clearUserName();
        userLoginView.clearPassword();
    }



}