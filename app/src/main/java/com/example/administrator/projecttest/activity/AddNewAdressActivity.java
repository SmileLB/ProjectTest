package com.example.administrator.projecttest.activity;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.projecttest.R;
import com.example.administrator.projecttest.bean.Address;
import com.example.administrator.projecttest.widget.LoadingDialog;
import com.zcw.togglebutton.ToggleButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class AddNewAdressActivity extends BaseActivity {


    @BindView(R.id.id_back_iv)
    RelativeLayout mIdBackIv;
    @BindView(R.id.id_tv_delete)
    TextView mIdTvManager;
    @BindView(R.id.id_toolbar)
    RelativeLayout mIdToolbar;
    @BindView(R.id.id_people)
    TextView mIdPeople;
    @BindView(R.id.et_people)
    EditText mEtPeople;
    @BindView(R.id.id_contract)
    TextView mIdContract;
    @BindView(R.id.et_contract)
    EditText mEtContract;
    @BindView(R.id.id_detail_adress)
    TextView mIdDetailAdress;
    @BindView(R.id.et_detail_adress)
    EditText mEtDetailAdress;
    @BindView(R.id.id_stard_adress)
    TextView mIdStardAdress;
    @BindView(R.id.id_toggle)
    ToggleButton mIdToggle;
    private boolean mToggle = false;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    public int setLayout() {
        return R.layout.activity_add_new_adress;
    }

    @Override
    public void init() {
        initView();
        initDate();
        initEvent();
    }

    private void initDate() {


    }

    private void initEvent() {
        mIdToggle.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                mToggle = on;
            }
        });
    }

    private void initView() {
        mIdToggle.setToggleOff();

    }


    @OnClick({R.id.id_back_iv, R.id.id_tv_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_back_iv:
                LoadingDialog.Builder builder1=new LoadingDialog.Builder(this)
                        .setMessage("正在提交")
                        .setCancelable(false);
                final LoadingDialog dialog1=builder1.create();
                dialog1.show();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        addAddress();
                        dialog1.dismiss();
                        finish();
                    }
                },1500);
                break;
            case R.id.id_tv_delete:
                clear();
                finish();
                break;
        }
    }

    private void addAddress() {
        ArrayList<Address> dataFromLocal = mAddressProvider.getDataFromLocal();
        String people = mEtPeople.getText().toString().trim();
        String contract = mEtContract.getText().toString().trim();
        String detailAdress = mEtDetailAdress.getText().toString().trim();
        if(!TextUtils.isEmpty(people)&&!TextUtils.isEmpty(contract)&&!TextUtils.isEmpty(detailAdress)){
            Address address=new Address(people,contract,detailAdress);
            if(mToggle){
                for (int i = 0; i < dataFromLocal.size(); i++) {
                    Address item = dataFromLocal.get(i);
                    item.setStarder(false);
                }
                address.setStarder(mToggle);
                dataFromLocal.add(address);
                mAddressProvider.save(dataFromLocal);
            }else{
                address.setStarder(false);
                dataFromLocal.add(address);
                mAddressProvider.save(dataFromLocal);
            }
        }
    }

    private void clear() {
        mEtPeople.setText("");
        mEtContract.setText("");
        mEtDetailAdress.setText("");
    }
}
