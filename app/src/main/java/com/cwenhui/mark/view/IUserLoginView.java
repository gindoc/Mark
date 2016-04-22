package com.cwenhui.mark.view;

import com.cwenhui.mark.bean.User;

public interface IUserLoginView
{
    String getUserName();

    String getPassword();

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(User user);

    void showFailedError();

}