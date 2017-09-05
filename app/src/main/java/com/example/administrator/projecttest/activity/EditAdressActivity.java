package com.example.administrator.projecttest.activity;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.projecttest.R;
import com.example.administrator.projecttest.bean.Address;
import com.example.administrator.projecttest.widget.LoadingDialog;
import com.zcw.togglebutton.ToggleButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class EditAdressActivity extends BaseActivity {


    @BindView(R.id.id_back_iv)
    RelativeLayout mIdBackIv;
    @BindView(R.id.id_tv_manager)
    TextView mIdTvManager;
    @BindView(R.id.id_toolbar)
    RelativeLayout mIdToolbar;
    @BindView(R.id.id_people)
    TextView mIdPeople;
    @BindView(R.id.et_people)
    EditText mEtPeople;
    @BindView(R.id.id_contract)
    TextView mIdContract;
    @BindView(R.id.tv_contract_phone)
    EditText mTvContractPhone;
    @BindView(R.id.id_detail_adress)
    TextView mIdDetailAdress;
    @BindView(R.id.detail_adress)
    EditText mDetailAdress;
    @BindView(R.id.id_stard_adress)
    TextView mIdStardAdress;
    @BindView(R.id.id_toggle)
    ToggleButton mIdToggle;
    @BindView(R.id.sava)
    Button mSava;
    private int mPosion;
    private ArrayList<Address> mDataFromLocal;
    private boolean mIsOn;
    private Address mAddress;
    private boolean onToggle;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    public int setLayout() {
        return R.layout.activity_edit_adress;
    }

    @Override
    public void init() {
        initView();
        initDate();
        initEvent();
    }

    private void initEvent() {

        mIdToggle.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                EditAdressActivity.this.onToggle = on;
            }
        });
    }

    private void initDate() {
        mAddress = mDataFromLocal.get(mPosion);
        mEtPeople.setText(mAddress.getName());
        mTvContractPhone.setText(mAddress.getPhone());
        mDetailAdress.setText(mAddress.getAddress());
    }

    private void initView() {
        mPosion = getIntent().getIntExtra("position", -1);
        mIsOn = getIntent().getBooleanExtra("isOn", false);
        onToggle = mIsOn;
        if (mIsOn) {
            mIdToggle.setToggleOn();
        } else {
            mIdToggle.setToggleOff();
        }
        if (mPosion != -1) {
            mDataFromLocal = mAddressProvider.getDataFromLocal();
        }
    }

    @OnClick({R.id.id_back_iv, R.id.id_tv_manager, R.id.sava})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_back_iv:
                finish();
                break;
            case R.id.id_tv_manager:
                mDataFromLocal.remove(mPosion);
                mAddressProvider.save(mDataFromLocal);
                finish();
                break;
            case R.id.sava:
                LoadingDialog.Builder builder1=new LoadingDialog.Builder(this)
                        .setMessage("正在提交")
                        .setCancelable(false);
                final LoadingDialog dialog1=builder1.create();
                dialog1.show();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        saveAddress();
                        dialog1.dismiss();
                    }
                },1500);

                break;
        }
    }

    private void saveAddress() {
        String people = mEtPeople.getText().toString().trim();
        String contractPhone = mTvContractPhone.getText().toString().trim();
        String detailAdress = mDetailAdress.getText().toString().trim();

        if (people.equals(mAddress.getName())
                && contractPhone.equals(mAddress.getPhone())
                && detailAdress.equals(mAddress.getAddress())
                && mIsOn == onToggle) {

            Toast.makeText(this, "没有修改地址和状态", Toast.LENGTH_SHORT).show();
            finish();

        } else {

            if (!people.equals(mAddress.getName())
                    || !contractPhone.equals(mAddress.getPhone())
                    || !detailAdress.equals(mAddress.getAddress())) {

                Address address = new Address(people, contractPhone, detailAdress);
                ArrayList<Address> dataFromLocal = mAddressProvider.getDataFromLocal();
                if (onToggle == true) {
                    for (int i = 0; i < dataFromLocal.size(); i++) {
                        Address item = dataFromLocal.get(i);
                        item.setStarder(false);
                    }
                }
                address.setStarder(onToggle);
                dataFromLocal.add(address);
                mAddressProvider.save(dataFromLocal);
                Toast.makeText(this, "保存新地址成功", Toast.LENGTH_SHORT).show();
                finish();

            } else {
                if (mIsOn != onToggle) {
                    ArrayList<Address> dataFromLocal = mAddressProvider.getDataFromLocal();
                    Address address = dataFromLocal.get(mPosion);
                    if (onToggle == true) {
                        for (int i = 0; i < dataFromLocal.size(); i++) {
                            Address item = dataFromLocal.get(i);
                            item.setStarder(false);
                        }
                    }
                    address.setStarder(onToggle);
                    mAddressProvider.save(dataFromLocal);
                    Toast.makeText(this, "更新地址成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }
}
