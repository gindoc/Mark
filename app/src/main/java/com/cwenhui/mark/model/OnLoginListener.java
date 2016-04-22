package com.cwenhui.mark.model;

import com.cwenhui.mark.bean.User;

public interface OnLoginListener
{
    void loginSuccess(User user);

    void loginFailed();
}