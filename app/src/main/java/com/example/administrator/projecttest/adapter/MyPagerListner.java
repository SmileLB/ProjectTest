package com.example.administrator.projecttest.adapter;

import android.support.v4.view.ViewPager;

import com.example.administrator.projecttest.widget.ViewPagerIndicator;


public class MyPagerListner implements ViewPager.OnPageChangeListener{

    private ViewPagerIndicator mIndicator;

    public MyPagerListner(ViewPagerIndicator indicator) {
        mIndicator = indicator;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mIndicator.setOffX(position%4, positionOffset);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
