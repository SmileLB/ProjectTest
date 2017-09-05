package com.example.administrator.projecttest.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.administrator.projecttest.R;
import com.example.administrator.projecttest.bean.Address;

import java.util.List;


public class AddressAdapter extends SimpleAdapter<Address> {

    public AddressAdapter(Context context, List<Address> datas) {
        super(context, R.layout.address_manager_item_view, datas);
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, Address item) {

        TextView tvAddress=viewHoder.getTextView(R.id.id_address);
        TextView tvPhone=viewHoder.getTextView(R.id.phone);
        TextView tvDetailAddress=viewHoder.getTextView(R.id.id_detail_address);

        tvAddress.setText(item.getName());
        tvPhone.setText(item.getPhone());
        tvDetailAddress.setText(item.getAddress());
    }
}
