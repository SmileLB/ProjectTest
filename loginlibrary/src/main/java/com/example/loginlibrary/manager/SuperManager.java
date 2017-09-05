package com.example.loginlibrary.manager;

import android.content.Context;
import android.content.Intent;

import com.example.loginlibrary.activitys.LoginResgistActivity;
import com.example.loginlibrary.impl.SuperInterface;

/**
 * Created by LiBing
 * on 2017/8/12 0012
 * describe:
 */

public class SuperManager {

    private static SuperInterface mSuperInterface;

    public static void startLoginActivity(Context context, SuperInterface superInterface) {
        if (mSuperInterface == null) {
            mSuperInterface = superInterface;
        }
        context.startActivity(new Intent(context, LoginResgistActivity.class));
    }

    public static SuperInterface getSuperInterface() {
        return mSuperInterface;
    }
}
