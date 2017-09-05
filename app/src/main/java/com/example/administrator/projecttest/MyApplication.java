package com.example.administrator.projecttest;

import android.app.Application;

import com.example.administrator.projecttest.util.AddressProvider;

/**
 * Created by LiBing
 * on 2017/8/11 0011
 * describe:
 */

public class MyApplication extends Application{

    public static AddressProvider mAddressProvider;

    @Override
    public void onCreate() {
        super.onCreate();
        mAddressProvider=new AddressProvider(this);
    }

    public AddressProvider getAddressProvider(){
        return mAddressProvider;
    }
}
