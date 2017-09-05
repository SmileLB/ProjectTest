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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginlibrary.R;
import com.example.loginlibrary.impl.SuperInterface;
import com.example.loginlibrary.preserter.RegisterPresenter;

import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnRegist;
    private Button mBtnCode;
    private RelativeLayout mImageView;

    private EditText mUsername;
    private EditText mPassword;
    private TextView login;

    private RegisterPresenter mPresenter;

    private static SuperInterface mView;

    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initEvent();
    }

    private void initView() {
        mPresenter = new RegisterPresenter(mView, this);
        mBtnRegist = (Button) findViewById(R.id.btn_regist);
        mBtnCode = (Button) findViewById(R.id.right);
        mImageView = (RelativeLayout) findViewById(R.id.iv_back);

        mUsername = (EditText) findViewById(R.id.id_et);
        mPassword = (EditText) findViewById(R.id.id_password);
        login = (TextView) findViewById(R.id.login);

        mBtnRegist.setOnClickListener(this);
        mImageView.setOnClickListener(this);
        login.setOnClickListener(this);
        mBtnCode.setOnClickListener(this);

        mBtnRegist.setEnabled(false);
        mBtnRegist.setTextColor(Color.parseColor("#55ffffff"));
        mBtnRegist.setBackgroundColor(Color.parseColor("#550076FF"));
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
                username = mUsername.getText().toString().trim();
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
                password = mPassword.getText().toString().trim();
                watch();
            }
        });
    }

    public void watch() {
        if ((!TextUtils.isEmpty(username) && username.length() > 0) && (!TextUtils.isEmpty(password) && password.length() > 0)) {
            mBtnRegist.setEnabled(true);
            mBtnRegist.setTextColor(Color.parseColor("#ffffff"));
            mBtnRegist.setBackgroundColor(Color.parseColor("#0076FF"));
        }else{
            mBtnRegist.setEnabled(false);
            mBtnRegist.setTextColor(Color.parseColor("#55ffffff"));
            mBtnRegist.setBackgroundColor(Color.parseColor("#550076FF"));
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_back) {
            finish();
        } else if (i == R.id.btn_regist) {
            if (check()) {
                mPresenter.normalRegister(username, password);
            }
        } else if (i == R.id.login) {

            startActivity(new Intent(this, LoginActivity.class));
            finish();

        } else if (i == R.id.right) {

            if ((!TextUtils.isEmpty(username) && username.length() == 11)) {

                Toast.makeText(this, "发送验证码", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "手机号必须是11位", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private boolean check() {
        if (!TextUtils.isEmpty(username) && username.length() != 11) {
            Toast.makeText(this, "手机号必须11位", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!TextUtils.isEmpty(password) && password.length() != 4) {
            Toast.makeText(this, "验证码必须是4位", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static void invoke(Context context, SuperInterface view) {
        mView = view;
        Intent intent = new Intent(context, RegisterActivity.class);
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
