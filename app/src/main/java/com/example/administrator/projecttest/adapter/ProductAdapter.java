package com.example.administrator.projecttest.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.administrator.projecttest.R;
import com.example.administrator.projecttest.bean.Product;

import java.util.List;


public class ProductAdapter extends SimpleAdapter<Product> {

    public ProductAdapter(Context context, List<Product> datas) {
        super(context, R.layout.item_view, datas);
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, Product item) {

        TextView tvAddress=viewHoder.getTextView(R.id.id_address);
        TextView tvpz=viewHoder.getTextView(R.id.phone);
        TextView tvOldPrice=viewHoder.getTextView(R.id.id_detail_address);



        /*viewHoder.getTextView(R.id.text_title).setText(item.getName());
        viewHoder.getTextView(R.id.text_price).setText("￥"+item.getPrice());
        SimpleDraweeView draweeView = (SimpleDraweeView) viewHoder.getView(R.id.drawee_view);
        draweeView.setImageURI(Uri.parse(item.getImgUrl()));*/
    }
}
