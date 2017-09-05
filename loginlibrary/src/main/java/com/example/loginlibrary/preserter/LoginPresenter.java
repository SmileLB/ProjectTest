package com.example.loginlibrary.preserter;

import android.support.v7.app.AppCompatActivity;

import com.example.loginlibrary.impl.SuperInterface;
import com.example.loginlibrary.preserter.ipreserter.ILoginPresenter;


public class LoginPresenter extends ILoginPresenter {

    private SuperInterface mLoginView;
    AppCompatActivity activity;


    public LoginPresenter(SuperInterface loginView, AppCompatActivity activity) {
        super(loginView);
        mLoginView = loginView;
        this.activity=activity;
    }

    @Override
    public void start() {

    }

    @Override
    public void finish() {

    }

    @Override
    public boolean checkUserNameLogin(String username,String password) {

        return false;
    }

    @Override
    public void usernameLogin(String username,String password) {
        mLoginView.onSuccess("登录成功",activity);
    }
}
