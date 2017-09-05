package com.example.loginlibrary.activitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginlibrary.R;
import com.example.loginlibrary.impl.SuperInterface;
import com.example.loginlibrary.preserter.LoginPresenter;

import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnLogin;
    private EditText mUsername;
    private EditText mPassword;
    private TextView tv1;
    private TextView tv2;
    private LoginPresenter mPresenter;
    private static SuperInterface mLoginView;

    private String username;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initEvent();
    }

    private void initEvent() {
        mUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                username = s.toString().trim();
                watch();
            }
        });

        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                password = s.toString().trim();
                watch();
            }
        });
    }

    public void watch() {
        if ((!TextUtils.isEmpty(username) && username.length() > 0) && (!TextUtils.isEmpty(password) && password.length() > 0)) {
            mBtnLogin.setEnabled(true);
            mBtnLogin.setTextColor(Color.parseColor("#ffffff"));
            mBtnLogin.setBackgroundColor(Color.parseColor("#0076FF"));
        }else{
            mBtnLogin.setEnabled(false);
            mBtnLogin.setTextColor(Color.parseColor("#55ffffff"));
            mBtnLogin.setBackgroundColor(Color.parseColor("#550076FF"));
        }
    }


    private void initView() {
        mPresenter = new LoginPresenter(mLoginView, this);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mUsername = (EditText) findViewById(R.id.id_et);
        mPassword = (EditText) findViewById(R.id.id_password);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);

        mBtnLogin.setOnClickListener(this);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);

        mBtnLogin.setEnabled(false);
        mBtnLogin.setTextColor(Color.parseColor("#55ffffff"));
        mBtnLogin.setBackgroundColor(Color.parseColor("#550076FF"));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_login) {
            if (check()) {
                mPresenter.usernameLogin(username, password);
            }

        } else if (id == R.id.tv1) {
            Toast.makeText(this, "验证码登录", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.tv2) {
            Toast.makeText(this, "忘记密码", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean check() {
        if (username.length() != 11) {
            Toast.makeText(this, "手机号必须11位", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.length() < 6) {
            Toast.makeText(this, "密码必须大于6位", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static void invoke(Context context, SuperInterface loginView) {
        mLoginView = loginView;
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Context context;
        private Map<String, String> mparams;
        private SuperInterface loginView;

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder setParams(Map<String, String> params) {
            this.mparams = params;
            return this;
        }

        public Builder setLister(SuperInterface loginView) {
            this.loginView = loginView;
            return this;
        }

        public void start() {
            invoke(context, loginView);
        }

    }
}
