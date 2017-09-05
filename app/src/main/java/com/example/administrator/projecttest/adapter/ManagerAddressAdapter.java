package com.example.administrator.projecttest.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.administrator.projecttest.R;
import com.example.administrator.projecttest.activity.EditAdressActivity;
import com.example.administrator.projecttest.bean.Address;

import java.util.List;


public class ManagerAddressAdapter extends SimpleAdapter<Address> {

    public ManagerAddressAdapter(Context context, List<Address> datas) {
        super(context, R.layout.manager_item_view, datas);
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, final Address item) {

        TextView tvAddress=viewHoder.getTextView(R.id.id_address);
        TextView tvPhone=viewHoder.getTextView(R.id.phone);
        TextView tvDetailAddress=viewHoder.getTextView(R.id.id_detail_address);
        TextView edit=viewHoder.getTextView(R.id.edit);
        TextView delete=viewHoder.getTextView(R.id.delete);
        final CheckBox cb=viewHoder.getCheckBox(R.id.cb_stard_address);

        tvAddress.setText(item.getName());
        tvPhone.setText(item.getPhone());
        tvDetailAddress.setText(item.getAddress());

        if(item.isStarder()){
            cb.setBackground(context.getResources().getDrawable(R.drawable.radio_select));
        }else{
            cb.setBackground(context.getResources().getDrawable(R.drawable.radio_unselect));
        }

        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.isStarder()){
                    item.setStarder(!item.isStarder());
                    cb.setBackground(context.getResources().getDrawable(R.drawable.radio_unselect));

                }else{
                    item.setStarder(!item.isStarder());
                    cb.setBackground(context.getResources().getDrawable(R.drawable.radio_select));
                }
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, EditAdressActivity.class));
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, EditAdressActivity.class));
            }
        });
    }


}
