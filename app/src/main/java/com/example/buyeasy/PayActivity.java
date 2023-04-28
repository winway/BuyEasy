package com.example.buyeasy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buyeasy.databinding.ActivityPayBinding;
import com.example.buyeasy.view.TitleView;

public class PayActivity extends AppCompatActivity {

    private static final String EXTRA_TOTAL_MONEY = "total_money";

    private ActivityPayBinding mBinding;

    public static Intent newIntent(Context context, double money) {
        Intent intent = new Intent(context, PayActivity.class);
        intent.putExtra(EXTRA_TOTAL_MONEY, money);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityPayBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        setupTitleView();

        double totalMoney = getIntent().getDoubleExtra(EXTRA_TOTAL_MONEY, 0);
        mBinding.payTotalEt.setText("" + totalMoney);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pay_address_edit_iv:
                break;
        }
    }

    private void setupTitleView() {
        mBinding.payTitleTv.setBackgroundResource(R.color.red);
        mBinding.payTitleTv.setTitleText("支付");
        mBinding.payTitleTv.setTitleTextColor(Color.WHITE);
        mBinding.payTitleTv.setLeftImageResource(R.mipmap.icon_back);
        mBinding.payTitleTv.setRightImageVisibility(false);

        mBinding.payTitleTv.setOnLeftImageClickListener(new TitleView.OnImageClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
    }
}