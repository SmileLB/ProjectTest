package com.example.administrator.projecttest.activity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.projecttest.R;
import com.example.administrator.projecttest.adapter.CustemAdapter;
import com.example.administrator.projecttest.bean.Address;
import com.example.administrator.projecttest.bean.Product;
import com.example.administrator.projecttest.common.DividerGridItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private static final String url = "https://img6.bdstatic.com/img/image/xiaolu/wujingzhanlang2.jpg";

    @BindView(R.id.id_back_iv)
    ImageView mIdBackIv;

    @BindView(R.id.id_recyclerview)
    RecyclerView mRecyclerView;

    private CustemAdapter mAdatper;

    private ArrayList<Product> dataList = new ArrayList<>();

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {

        initRecyclerView();
        initData();
        saveAddress();
    }

    private void saveAddress() {
        ArrayList<Address> dataFromLocal = mAddressProvider.getDataFromLocal();
        if (!(dataFromLocal != null && dataFromLocal.size() != 0)) {
            ArrayList<Address> list = new ArrayList<>();
            Address address1 = new Address("罗莲1", "13951733422", "江苏省苏州市旺敦路邮政大厦1066室，有更多文字，最多为2排");
            Address address2 = new Address("罗莲2", "13951733422", "安徽省苏州市旺敦路邮政大厦1066室");
            Address address3 = new Address("罗莲3", "13951733422", "芜湖市旺敦路邮政大厦1066室");
            Address address4 = new Address("罗莲4", "13951733422", "江苏省苏州市旺敦路邮政大厦1066室");

            address1.setStarder(true);

            list.add(address1);
            list.add(address2);
            list.add(address3);
            list.add(address4);

            mAddressProvider.save(list);
        }
    }

    private void initData() {

        for (int i = 0; i < 50; i++) {
            dataList.add(new Product(url, "馒头(5个)", "¥6.00", "¥5.00"));
        }
        mAdatper.notifyDataSetChanged();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

//        mRecyclerView.addItemDecoration(new GridDivider(this, 20));
//        mRecyclerView.addItemDecoration(new GridDivider(20, Color.parseColor("#55cccccc")));
//
//        mRecyclerView.addItemDecoration(new GridDivider(this));
//        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this, 2));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdatper = new CustemAdapter(this, dataList);

        mRecyclerView.setAdapter(mAdatper);
    }

    @OnClick({R.id.id_back_iv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_back_iv:
                finish();

                break;
        }
    }
}
