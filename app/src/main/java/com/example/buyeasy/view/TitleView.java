package com.example.buyeasy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.buyeasy.R;

/**
 * @PackageName: com.example.buyeasy.view
 * @ClassName: TitleView
 * @Author: winwa
 * @Date: 2023/4/17 8:33
 * @Description:
 **/
public class TitleView extends RelativeLayout {
    private RelativeLayout mRL;
    private ImageView mLeftIV;
    private ImageView mRightIV;
    private TextView mTitleTV;

    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_title, this);
        initView();
    }

    private void initView() {
        mRL = findViewById(R.id.view_title_rl);
        mLeftIV = findViewById(R.id.view_title_left_iv);
        mRightIV = findViewById(R.id.view_title_right_iv);
        mTitleTV = findViewById(R.id.view_title_tv);
    }

    public void setTitleText(String s) {
        mTitleTV.setText(s);
    }

    public void setTitleText(int resId) {
        mTitleTV.setText(resId);
    }

    public void setTitleTextColor(int color) {
        mTitleTV.setTextColor(color);
    }

    public void setBackgroundResource(int resId) {
        mRL.setBackgroundResource(resId);
    }

    public void setBackgroundColor(int color) {
        mRL.setBackgroundColor(color);
    }
}
