package com.example.buyeasy;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buyeasy.adapter.GoodsListAdapter;
import com.example.buyeasy.bean.GoodsBean;
import com.example.buyeasy.bean.GoodsData;
import com.example.buyeasy.view.TitleView;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    private TitleView mTitleTV;
    private ListView mGoodsLV;
    private TextView mTotalMoneyTV;
    private Button mBuyBTN;

    private GoodsListAdapter mGoodsListAdapter;
    private List<GoodsBean> mGoodsListAdapterData;

    private double mTotalMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initView();

        initGoodsListAdapter();

        refreshTotalMoney();
    }

    public void refreshTotalMoney() {
        mTotalMoney = GoodsData.calculateTotalMoney();
        mTotalMoneyTV.setText("" + mTotalMoney);
    }

    private void initGoodsListAdapter() {
        mGoodsListAdapterData = GoodsData.getBuyList();

        mGoodsListAdapter = new GoodsListAdapter(this, mGoodsListAdapterData, true);
        mGoodsLV.setAdapter(mGoodsListAdapter);
    }

    private void initView() {
        mTitleTV = findViewById(R.id.cart_title_tv);
        mGoodsLV = findViewById(R.id.cart_goolds_lv);
        mTotalMoneyTV = findViewById(R.id.cart_total_money_tv);
        mBuyBTN = findViewById(R.id.cart_buy_btn);

        setupTitleView();

        mBuyBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(PayActivity.newIntent(CartActivity.this, mTotalMoney));
            }
        });
    }

    private void setupTitleView() {
        mTitleTV.setBackgroundResource(R.color.red);
        mTitleTV.setTitleText("购物车");
        mTitleTV.setTitleTextColor(Color.WHITE);
        mTitleTV.setLeftImageResource(R.mipmap.icon_back);
        mTitleTV.setRightImageVisibility(false);

        mTitleTV.setOnLeftImageClickListener(new TitleView.OnImageClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
    }
}