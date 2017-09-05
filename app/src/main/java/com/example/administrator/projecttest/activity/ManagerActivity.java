package com.example.administrator.projecttest.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.administrator.projecttest.R;
import com.example.administrator.projecttest.adapter.CustemAddressAdapter;
import com.example.administrator.projecttest.bean.Address;
import com.example.administrator.projecttest.common.MyDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class ManagerActivity extends BaseActivity {


    @BindView(R.id.id_back_iv)
    RelativeLayout mIdBackIv;
    @BindView(R.id.id_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.add_address)
    RelativeLayout mAddAddress;

    private CustemAddressAdapter mAdapter;
    private ArrayList<Address> mDataFromLocal;

    @Override
    public int setLayout() {
        return R.layout.activity_manager;
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

        mRecyclerView.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));

//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

//        mRecyclerView.addItemDecoration(new RecyclerViewDivider(
//                this, LinearLayoutManager.VERTICAL, R.drawable.decoration));

        mAdapter = new CustemAddressAdapter(this, (ArrayList<Address>) mDataFromLocal);

        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mDataFromLocal.clear();
        initData();
        mAdapter.setData(mDataFromLocal);
        mAdapter.notifyDataSetChanged();
    }


    @OnClick({R.id.id_back_iv, R.id.add_address})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_back_iv:
                finish();
                break;
            case R.id.add_address:
                Intent intent=new Intent(this,AddNewAdressActivity.class);
                startActivity(intent);
                break;
        }
    }
}
