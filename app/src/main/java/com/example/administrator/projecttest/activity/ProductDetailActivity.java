package com.example.administrator.projecttest.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.example.administrator.projecttest.R;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

public class ProductDetailActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.tv2)
    TextView mTv2;
    @BindView(R.id.tv3)
    TextView mTv3;
    @BindView(R.id.tv4)
    TextView mTv4;
    @BindView(R.id.tv5)
    TextView mTv5;
    @BindView(R.id.rl_content)
    RelativeLayout mRlContent;

    @BindView(R.id.id_btn_buy)
    Button mIdBtnBuy;
    @BindView(R.id.id_rl_contnet)
    RelativeLayout mIdRlContnet;
    @BindView(R.id.iv_back)
    RelativeLayout mIvBack;
    /*@BindView(R.id.slider)
    SliderLayout mSlider;
    @BindView(R.id.custom_indicator)
    PagerIndicator mCustomIndicator;*/
    /*@BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.indicator)
    ViewPagerIndicator mIndicator;*/
    private int mPosition;
    private Dialog mDialog;

    private String mColor = "浅灰色";
    private String mSize = "38";
    private int mCount = 38;
    private Button mBtnColorOne;
    private Button mBtnColorTwo;
    private Button mBtnColorThree;
    private Button mBtnSizeOne;
    private Button mBtnSizeTwo;
    private Button mBtnSizeThree;
    private Button mBtnSizeFour;
    private Button mBtnMinus;
    private Button mBtnValue;
    private Button mBtnAdd;
    private Button mBtnSure;

    private ArrayList<ImageView> list = new ArrayList<>();
    SliderLayout mDemoSlider;
    PagerIndicator mPagerIndicator;

    @Override
    public int setLayout() {
        return R.layout.activity_product_detail;
    }

    @Override
    public void init() {
        mPosition = getIntent().getIntExtra("position", -1);
        initView();
        initData();
        initDialog();
        initViewPager();
    }

    private void initViewPager() {

        mDemoSlider = (SliderLayout)findViewById(R.id.slider);
        mPagerIndicator = (PagerIndicator)findViewById(R.id.custom_indicator);

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("1",R.mipmap.food);
        file_maps.put("2",R.mipmap.food);
        file_maps.put("3",R.mipmap.food);
        file_maps.put("4", R.mipmap.food);

        for(String name : file_maps.keySet()){
            DefaultSliderView textSliderView = new DefaultSliderView(this);
            textSliderView.image(file_maps.get(name)).setScaleType(BaseSliderView.ScaleType.Fit);
            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
//        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomIndicator(mPagerIndicator);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(2000);
    }

    private void initView() {
        mTv3.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    private void initData() {
        if (mPosition != -1) {
            if (mPosition % 2 == 0) {
                mIdRlContnet.setBackgroundColor(Color.parseColor("#f53b37"));
                mTv4.setVisibility(View.VISIBLE);
                mTv5.setVisibility(View.GONE);
                mIdBtnBuy.setEnabled(true);
                mIdBtnBuy.setTextColor(Color.parseColor("#ffffff"));

            } else {
                mIdRlContnet.setBackgroundColor(Color.parseColor("#cccccc"));
                mTv4.setVisibility(View.GONE);
                mTv5.setVisibility(View.VISIBLE);
                mIdBtnBuy.setEnabled(false);
                mIdBtnBuy.setTextColor(Color.parseColor("#55ffffff"));
            }
        }
    }

    @OnClick({R.id.iv_back, R.id.id_btn_buy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.id_btn_buy:
                mDialog.show();
                break;
            case R.id.iv_delete:
                mDialog.dismiss();
                break;
            case R.id.id_btn_color_one:
                changeColor(1);
                break;
            case R.id.id_btn_color_two:
                changeColor(2);
                break;
            case R.id.id_btn_color_three:
                changeColor(3);
                break;
            case R.id.id_btn_size_one:
                chengeSizeColor(1);
                break;
            case R.id.id_btn_size_two:
                chengeSizeColor(2);
                break;
            case R.id.id_btn_size_three:
                chengeSizeColor(3);
                break;
            case R.id.id_btn_size_four:
                chengeSizeColor(4);
                break;
            case R.id.id_btn_minus:
                changeCountValue(1);
                break;
            case R.id.id_btn_add:
                changeCountValue(2);
                break;
            case R.id.id_sure:
                startActivity(new Intent(this,CommitActivity.class));
                break;
        }
    }

    private void changeCountValue(int value) {
        if (value == 1) {
            if (mCount > 0) {
                mCount = mCount - 1;
                mBtnValue.setText(mCount + "");
            }
        } else {
            mCount = mCount + 1;
            mBtnValue.setText(mCount + "");
        }
    }

    private void chengeSizeColor(int value) {
        changeSizeBtnColor();
        if (value == 1) {
            mSize = "35";
            mBtnSizeOne.setBackground(getResources().getDrawable(R.drawable.btn_shape_selected));
            mBtnSizeOne.setTextColor(Color.parseColor("#ffffff"));

        } else if (value == 2) {
            /*mSize = "36";
            mBtnSizeTwo.setBackground(getResources().getDrawable(R.drawable.btn_shape_selected));
            mBtnSizeTwo.setTextColor(Color.parseColor("#ffffff"));*/

        } else if (value == 3) {
            mSize = "37";
            mBtnSizeThree.setBackground(getResources().getDrawable(R.drawable.btn_shape_selected));
            mBtnSizeThree.setTextColor(Color.parseColor("#ffffff"));

        } else {
            mSize = "38";
            mBtnSizeFour.setBackground(getResources().getDrawable(R.drawable.btn_shape_selected));
            mBtnSizeFour.setTextColor(Color.parseColor("#ffffff"));
        }
    }

    private void changeSizeBtnColor() {
        mBtnSizeOne.setBackground(getResources().getDrawable(R.drawable.btn_shape_no_select));
//        mBtnSizeTwo.setBackground(getResources().getDrawable(R.drawable.btn_shape_no_select));
        mBtnSizeThree.setBackground(getResources().getDrawable(R.drawable.btn_shape_no_select));
        mBtnSizeFour.setBackground(getResources().getDrawable(R.drawable.btn_shape_no_select));

        mBtnSizeOne.setTextColor(Color.parseColor("#333333"));
//        mBtnSizeTwo.setTextColor(Color.parseColor("#333333"));
        mBtnSizeThree.setTextColor(Color.parseColor("#333333"));
        mBtnSizeFour.setTextColor(Color.parseColor("#333333"));
    }

    private void changeColor(int value) {
        changeColorBtnColor();
        if (value == 1) {
            mColor = "黑色";
            mBtnColorOne.setBackground(getResources().getDrawable(R.drawable.btn_shape_selected));
            mBtnColorOne.setTextColor(Color.parseColor("#ffffff"));

        } else if (value == 2) {
            mColor = "白色";
            mBtnColorTwo.setBackground(getResources().getDrawable(R.drawable.btn_shape_selected));
            mBtnColorTwo.setTextColor(Color.parseColor("#ffffff"));

        } else {
            mColor = "浅灰色";
            mBtnColorThree.setBackground(getResources().getDrawable(R.drawable.btn_shape_selected));
            mBtnColorThree.setTextColor(Color.parseColor("#ffffff"));

        }
    }

    private void changeColorBtnColor() {
        mBtnColorOne.setBackground(getResources().getDrawable(R.drawable.btn_shape_no_select));
        mBtnColorTwo.setBackground(getResources().getDrawable(R.drawable.btn_shape_no_select));
        mBtnColorThree.setBackground(getResources().getDrawable(R.drawable.btn_shape_no_select));
        mBtnColorOne.setTextColor(Color.parseColor("#333333"));
        mBtnColorTwo.setTextColor(Color.parseColor("#333333"));
        mBtnColorThree.setTextColor(Color.parseColor("#333333"));
    }

    private void initDialog() {
        mDialog = new Dialog(this, R.style.floag_dialog);
        mDialog.setContentView(R.layout.dialog_choose);
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        Window dlgwin = mDialog.getWindow();
        WindowManager.LayoutParams lp = dlgwin.getAttributes();
        dlgwin.setGravity(Gravity.BOTTOM);
        lp.width = (int) (display.getWidth()); //设置宽度
        mDialog.getWindow().setAttributes(lp);
        mDialog.findViewById(R.id.iv_delete).setOnClickListener(this);

        mBtnColorOne = (Button) mDialog.findViewById(R.id.id_btn_color_one);
        mBtnColorTwo = (Button) mDialog.findViewById(R.id.id_btn_color_two);
        mBtnColorThree = (Button) mDialog.findViewById(R.id.id_btn_color_three);
        mBtnSizeOne = (Button) mDialog.findViewById(R.id.id_btn_size_one);
        mBtnSizeTwo = (Button) mDialog.findViewById(R.id.id_btn_size_two);
        mBtnSizeThree = (Button) mDialog.findViewById(R.id.id_btn_size_three);
        mBtnSizeFour = (Button) mDialog.findViewById(R.id.id_btn_size_four);
        mBtnMinus = (Button) mDialog.findViewById(R.id.id_btn_minus);
        mBtnValue = (Button) mDialog.findViewById(R.id.id_btn_value);
        mBtnAdd = (Button) mDialog.findViewById(R.id.id_btn_add);
        mBtnSure = (Button) mDialog.findViewById(R.id.id_sure);

        mBtnColorOne.setOnClickListener(this);
        mBtnColorTwo.setOnClickListener(this);
        mBtnColorThree.setOnClickListener(this);
        mBtnSizeOne.setOnClickListener(this);
        mBtnSizeTwo.setOnClickListener(this);
        mBtnSizeThree.setOnClickListener(this);
        mBtnSizeFour.setOnClickListener(this);
        mBtnMinus.setOnClickListener(this);
        mBtnAdd.setOnClickListener(this);
        mBtnSure.setOnClickListener(this);

        mCount = Integer.parseInt(mBtnValue.getText().toString());

    }

}
