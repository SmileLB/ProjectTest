package com.example.loginlibrary.preserter;


import android.support.v7.app.AppCompatActivity;

import com.example.loginlibrary.impl.SuperInterface;
import com.example.loginlibrary.preserter.ipreserter.IRegisterPresenter;

/**
 * @description: 注册信息管理
 */
public class RegisterPresenter extends IRegisterPresenter {
    private SuperInterface mIRegisterView;
    AppCompatActivity activity;

    public RegisterPresenter(SuperInterface baseView,AppCompatActivity activity) {
        super(baseView);
        mIRegisterView = baseView;
        this.activity=activity;
    }

    @Override
    public void start() {

    }

    @Override
    public void finish() {

    }


    @Override
    protected boolean checkNormalRegister() {
        return false;
    }


    /**
     * tls用户名注册
     */
    @Override
    public void normalRegister(String username,String password) {
        mIRegisterView.onSuccess("注册成功",activity);
        activity.finish();
    }
}
