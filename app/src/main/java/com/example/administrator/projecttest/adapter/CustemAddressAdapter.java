package com.example.administrator.projecttest.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.projecttest.MyApplication;
import com.example.administrator.projecttest.R;
import com.example.administrator.projecttest.activity.EditAdressActivity;
import com.example.administrator.projecttest.bean.Address;
import com.example.administrator.projecttest.util.AddressProvider;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CustemAddressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Address address;

    private LayoutInflater mLayoutInflater;
    public AddressProvider mAddressProvider;

    private Context mContext;
    private ArrayList<Address> dataList;

    public CustemAddressAdapter(Context context, ArrayList<Address> dataList) {
        mContext = context;
        this.dataList = dataList;
        mLayoutInflater = LayoutInflater.from(context);
        mAddressProvider = ((MyApplication)context.getApplicationContext()).getAddressProvider();;
    }

    public void setData(ArrayList<Address> dataList) {
        this.dataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AppViewHolder(mLayoutInflater.inflate(R.layout.manager_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final AppViewHolder mHolder = (AppViewHolder) holder;
        final Address item = dataList.get(position);

        mHolder.tvAddress.setText(item.getName());
        mHolder.tvPhone.setText(item.getPhone());
        mHolder.tvDetailAddress.setText(item.getAddress());

        if(item.isStarder()){
            mHolder.cb.setBackground(mContext.getResources().getDrawable(R.drawable.radio_select));
        }else{
            mHolder.cb.setBackground(mContext.getResources().getDrawable(R.drawable.radio_unselect));
        }

        mHolder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.isStarder()){
                    chengeSelect(position);
                    mHolder.cb.setBackground(mContext.getResources().getDrawable(R.drawable.radio_unselect));

                }else{
                    chengeSelect(position);
                    mHolder.cb.setBackground(mContext.getResources().getDrawable(R.drawable.radio_select));

                }
            }
        });

        mHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EditAdressActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("isOn",item.isStarder());
                mContext.startActivity(intent);
            }
        });

        mHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Address> dataFromLocal = mAddressProvider.getDataFromLocal();
                dataFromLocal.remove(position);
                mAddressProvider.save(dataFromLocal);
                dataList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    private void chengeSelect(int value) {
        for(int i=0;i<dataList.size();i++){
            Address address = dataList.get(i);
            if(i==value){
                address.setStarder(true);
            }else{
                address.setStarder(false);
            }
        }
        mAddressProvider.save(dataList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    class AppViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.id_address)
        TextView tvAddress;
        @BindView(R.id.phone)
        TextView tvPhone;
        @BindView(R.id.id_detail_address)
        TextView tvDetailAddress;
        @BindView(R.id.rl_address)
        RelativeLayout mRlAddress;
        @BindView(R.id.cb_stard_address)
        CheckBox cb;
        @BindView(R.id.edit)
        TextView edit;
        @BindView(R.id.delete)
        TextView delete;
        View view;

        public AppViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view=itemView;

        }
    }
}
