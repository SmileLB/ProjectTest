package com.example.administrator.projecttest.activity;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.projecttest.R;
import com.example.administrator.projecttest.adapter.AddressAdapter;
import com.example.administrator.projecttest.bean.Address;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddressManagerActivity extends BaseActivity {


    @BindView(R.id.id_back_iv)
    RelativeLayout mIdBackIv;
    @BindView(R.id.id_tv_manager)
    TextView mIdTvManager;
    @BindView(R.id.id_toolbar)
    RelativeLayout mIdToolbar;
    @BindView(R.id.id_recyclerview)
    RecyclerView mRecyclerView;
    private List<Address> mDataFromLocal;
    private AddressAdapter mAdapter;

    @Override
    public int setLayout() {
        return R.layout.activity_address_manager;
    }

    @Override
    public void init() {
        initData();
        initRecyclerView();
    }

    private void initData() {
        mDataFromLocal = mAddressProvider.getDataFromLocal();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

//        mRecyclerView.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

//        mRecyclerView.addItemDecoration(new RecyclerViewDivider(
//                this, LinearLayoutManager.VERTICAL, R.drawable.decoration));
        mAdapter = new AddressAdapter(this, mDataFromLocal);

        mRecyclerView.setAdapter(mAdapter);

    }

    @OnClick({R.id.id_back_iv, R.id.id_tv_manager})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_back_iv:
                EventBus.getDefault().post(new Address("111","",""));
                finish();
                break;
            case R.id.id_tv_manager:
                startActivity(new Intent(this, ManagerActivity.class));
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDataFromLocal.clear();
        initData();
        mAdapter.setData(mDataFromLocal);
        mAdapter.notifyDataSetChanged();
    }
}
