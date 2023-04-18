package com.example.buyeasy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
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
public class TitleView extends RelativeLayout implements View.OnClickListener {
    private RelativeLayout mRL;
    private ImageView mLeftIV;
    private ImageView mRightIV;
    private TextView mTitleTV;

    private OnImageClickListener mOnLeftImageClickListener;
    private OnImageClickListener mOnRightImageClickListener;

    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_title, this);

        initView();

        mLeftIV.setOnClickListener(this);
        mRightIV.setOnClickListener(this);
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

    public void setLeftImageVisibility(Boolean show) {
        if (show) {
            mLeftIV.setVisibility(View.VISIBLE);
        } else {
            mLeftIV.setVisibility(View.INVISIBLE);
        }
    }

    public void setRightImageVisibility(Boolean show) {
        if (show) {
            mRightIV.setVisibility(View.VISIBLE);
        } else {
            mRightIV.setVisibility(View.INVISIBLE);
        }
    }

    public void setLeftImageResource(int resId) {
        mLeftIV.setImageResource(resId);
    }

    public void setRightImageResource(int resId) {
        mRightIV.setImageResource(resId);
    }

    public void setOnLeftImageClickListener(OnImageClickListener onLeftImageClickListener) {
        mOnLeftImageClickListener = onLeftImageClickListener;
    }

    public void setOnRightImageClickListener(OnImageClickListener onRightImageClickListener) {
        mOnRightImageClickListener = onRightImageClickListener;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_title_left_iv:
                if (mOnLeftImageClickListener != null) {
                    mOnLeftImageClickListener.onClick();
                }
                break;
            case R.id.view_title_right_iv:
                if (mOnRightImageClickListener != null) {
                    mOnRightImageClickListener.onClick();
                }
                break;
        }
    }

    public interface OnImageClickListener {
        public void onClick();
    }
}
