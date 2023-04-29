package com.example.buyeasy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.buyeasy.bean.ReceiverInfoBean;
import com.example.buyeasy.databinding.ActivityPayBinding;
import com.example.buyeasy.view.TitleView;

public class PayActivity extends AppCompatActivity {

    private static final String EXTRA_TOTAL_MONEY = "total_money";
    private static final int REQUEST_RECEIVER_INFO = 100;

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
                Intent intent = new Intent(this, ReceiverInfoActivity.class);
                startActivityIfNeeded(intent, REQUEST_RECEIVER_INFO);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_RECEIVER_INFO && resultCode == RESULT_OK) {
            ReceiverInfoBean receiverInfoBean = (ReceiverInfoBean) data.getSerializableExtra(ReceiverInfoActivity.EXTRA_RECEIVER_INFO);
            mBinding.payReceiverEt.setText(receiverInfoBean.getName());
            mBinding.payPhoneEt.setText(receiverInfoBean.getPhone());
            mBinding.payAddressEt.setText(receiverInfoBean.getCity() + "|" + receiverInfoBean.getStreet());
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