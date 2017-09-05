package com.example.loginlibrary.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginlibrary.R;
import com.example.loginlibrary.manager.SuperManager;

public class LoginResgistActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mLogin;
    private Button mRegist;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_resgist);
        mLogin = (Button) findViewById(R.id.btn_login);
        mRegist = (Button) findViewById(R.id.btn_regist);
        mTextView = (TextView) findViewById(R.id.line2);
        mLogin.setOnClickListener(this);
        mRegist.setOnClickListener(this);
        mTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_login) {

            LoginActivity.builder()
                        .setContext(this)
                        .setLister(SuperManager.getSuperInterface())
                        .start();
            finish();

        } else if (i == R.id.btn_regist) {

            RegisterActivity.builder()
                    .setContext(this)
                    .setLister(SuperManager.getSuperInterface())
                    .start();
            finish();

        }else{
            Toast.makeText(this,"随便看看",Toast.LENGTH_SHORT).show();
        }
    }
}
