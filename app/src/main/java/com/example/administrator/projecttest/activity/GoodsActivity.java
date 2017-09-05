package com.example.administrator.projecttest.activity;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.administrator.projecttest.R;
import com.example.administrator.projecttest.widget.LoadingDialog;

import butterknife.BindView;
import butterknife.OnClick;

public class GoodsActivity extends BaseActivity {


    @BindView(R.id.id_back_iv)
    RelativeLayout mIdBackIv;
    @BindView(R.id.commit)
    Button mCommit;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    public int setLayout() {
        return R.layout.activity_goods;
    }

    @Override
    public void init() {

    }

    @OnClick({R.id.id_back_iv, R.id.commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_back_iv:
                finish();
                break;
            case R.id.commit:
                LoadingDialog.Builder builder1=new LoadingDialog.Builder(this)
                        .setMessage("正在提交")
                        .setCancelable(false);
                final LoadingDialog dialog1=builder1.create();
                dialog1.show();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        dialog1.dismiss();
                    }
                },1500);
                break;
        }
    }
}
