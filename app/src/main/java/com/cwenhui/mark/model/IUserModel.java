package com.cwenhui.mark.model;

public interface IUserModel
{
    public void login(String username, String password, OnLoginListener loginListener);
}