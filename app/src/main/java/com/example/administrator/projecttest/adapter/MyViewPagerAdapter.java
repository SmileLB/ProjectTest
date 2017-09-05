package com.example.administrator.projecttest.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by LiBing
 * on 2017/8/10 0010
 * describe:
 */

public class MyViewPagerAdapter extends PagerAdapter{

    private Context mContext;
    ArrayList<ImageView> list;

    public MyViewPagerAdapter(Context context, ArrayList<ImageView> list) {
        this.mContext=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager)container).removeView(list.get(position%list.size()));

    }


    @Override
    public Object instantiateItem(View container, int position) {
        ((ViewPager)container).addView(list.get(position%list.size()));
        return list.get(position%list.size());
    }

}
