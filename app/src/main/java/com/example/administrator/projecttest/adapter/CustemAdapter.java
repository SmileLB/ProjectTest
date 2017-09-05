package com.example.administrator.projecttest.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.projecttest.R;
import com.example.administrator.projecttest.activity.ProductDetailActivity;
import com.example.administrator.projecttest.bean.Product;
import com.example.loginlibrary.impl.SuperInterface;
import com.example.loginlibrary.manager.SuperManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CustemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    public static final int TYPE_BANNER = 1;
    private static final int TYPE_ICON = 2;

    private LayoutInflater mLayoutInflater;

    private Context mContext;
    private ArrayList<Product> dataList;

    public CustemAdapter(Context context, ArrayList<Product> dataList) {
        mContext = context;
        this.dataList = dataList;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setData(Product product) {
        product = product;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AppViewHolder(mLayoutInflater.inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        AppViewHolder mHolder = (AppViewHolder) holder;

        Product product = dataList.get(position);

        if (position % 2 == 0) {
            mHolder.mIdItemBtn.setVisibility(View.VISIBLE);
            mHolder.mIdItemBtnTwo.setVisibility(View.GONE);
            mHolder.mIdItemBtn.setOnClickListener(this);

        } else {
            mHolder.mIdItemBtn.setVisibility(View.GONE);
            mHolder.mIdItemBtnTwo.setVisibility(View.VISIBLE);
            mHolder.mIdItemBtnTwo.setOnClickListener(this);
        }

//        Glide.with(mContext).load(product.getUrl()).into(mHolder.mIdItemIv);
        mHolder.mIdItemContent.setText(product.getContent());
        mHolder.mIdItemPrice.setText(product.getPrice());
        mHolder.mIdItemOldPrice.setText(product.getOldPrice());
        mHolder.mIdItemOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        mHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProductDetailActivity.class);
                intent.putExtra("position", position);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_BANNER;
        } else if (position == 1) {
            return TYPE_ICON;
        }
        return 0;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.id_item_btn:

                SuperManager.startLoginActivity(mContext, new SuperInterface() {
                    @Override
                    public void onSuccess(String user, AppCompatActivity activity) {
                        Toast.makeText(mContext, user, Toast.LENGTH_SHORT).show();
                        activity.finish();
                    }
                });

                break;
            case R.id.id_item_btn_two:

                Toast.makeText(mContext,"已售罄",Toast.LENGTH_SHORT).show();

                break;
        }
    }

    class AppViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.id_item_iv)
        ImageView mIdItemIv;
        @BindView(R.id.id_item_content)
        TextView mIdItemContent;
        @BindView(R.id.id_item_price)
        TextView mIdItemPrice;
        @BindView(R.id.id_item_old_price)
        TextView mIdItemOldPrice;
        @BindView(R.id.id_item_btn)
        Button mIdItemBtn;
        @BindView(R.id.id_item_btn_two)
        Button mIdItemBtnTwo;
        View view;


        public AppViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;

        }

    }

}
