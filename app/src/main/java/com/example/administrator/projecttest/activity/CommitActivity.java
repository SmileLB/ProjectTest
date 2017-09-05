package com.example.administrator.projecttest.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.projecttest.R;
import com.example.administrator.projecttest.bean.Address;
import com.example.administrator.projecttest.widget.LoadingDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CommitActivity extends BaseActivity {


    @BindView(R.id.rl_address)
    RelativeLayout mRlAddress;
    @BindView(R.id.commit)
    Button mCommit;
    @BindView(R.id.rl_add_address)
    RelativeLayout mRlAddAddress;
    @BindView(R.id.id_rl_send)
    RelativeLayout mIdRlSend;
    @BindView(R.id.id_back_iv)
    RelativeLayout mIdBackIv;
    @BindView(R.id.id_address)
    TextView mIdAddress;
    @BindView(R.id.phone)
    TextView mPhone;
    @BindView(R.id.id_detail_address)
    TextView mIdDetailAddress;
    @BindView(R.id.id_btn_minus)
    Button mIdBtnMinus;
    @BindView(R.id.id_btn_value)
    Button mBtnValue;
    @BindView(R.id.id_btn_add)
    Button mIdBtnAdd;
    @BindView(R.id.tv_total)
    TextView mTvTotal;
    private List<Address> mDataFromLocal;
    private int mCount = 1;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    public int setLayout() {
        return R.layout.activity_commit;
    }

    @Override
    public void init() {
        initData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCall(Address messageEvent) {
        Toast.makeText(this,messageEvent.getName(),Toast.LENGTH_SHORT).show();
    }

    private void initData() {
        mDataFromLocal = mAddressProvider.getDataFromLocal();
        if (mDataFromLocal != null && mDataFromLocal.size() != 0) {

            for (int i = 0; i < mDataFromLocal.size(); i++) {
                Address address = mDataFromLocal.get(i);
                if (address.isStarder()) {
                    mRlAddAddress.setVisibility(View.GONE);
                    mRlAddress.setVisibility(View.VISIBLE);
                    mIdAddress.setText(address.getName());
                    mPhone.setText(address.getPhone());
                    mIdDetailAddress.setText(address.getAddress());
                    return;
                } else {
                    mRlAddAddress.setVisibility(View.VISIBLE);
                    mRlAddress.setVisibility(View.GONE);
                }
            }

        } else {
            mRlAddAddress.setVisibility(View.VISIBLE);
            mRlAddress.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.id_btn_minus, R.id.id_btn_add, R.id.rl_address, R.id.commit, R.id.rl_add_address, R.id.id_back_iv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_address:
                startActivity(new Intent(this, AddressManagerActivity.class));
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
//                        startActivity(new Intent(CommitActivity.this, GoodsActivity.class));
                        dialog1.dismiss();
                    }
                },1500);
                break;
            case R.id.rl_add_address:
                startActivity(new Intent(this, AddressManagerActivity.class));
                break;
            case R.id.id_back_iv:
                finish();
                break;
            case R.id.id_btn_minus:
                changeCountValue(1);
                break;
            case R.id.id_btn_add:
                changeCountValue(2);
                break;
        }
    }

    private void changeCountValue(int value) {
        if (value == 1) {
            if (mCount > 0) {
                mCount = mCount - 1;
                mBtnValue.setText(mCount + "");
            }
        } else {
            mCount = mCount + 1;
            mBtnValue.setText(mCount + "");
        }
        mTvTotal.setText("¥"+(mCount*38+8)+".00");
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }
}
