package com.example.administrator.projecttest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.projecttest.MyApplication;
import com.example.administrator.projecttest.util.AddressProvider;

import butterknife.ButterKnife;
import butterknife.Unbinder;



public abstract class BaseActivity extends AppCompatActivity{

    private Unbinder mBind;
    public AddressProvider mAddressProvider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        mBind = ButterKnife.bind(this);
        mAddressProvider =((MyApplication)this.getApplicationContext()).getAddressProvider();
        init();
    }

    public abstract int setLayout();

    public void startActivity(Class c) {
        startActivity(new Intent(this, c));
    }

    public abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != mBind.EMPTY) {
            mBind.unbind();
        }

    }
}
