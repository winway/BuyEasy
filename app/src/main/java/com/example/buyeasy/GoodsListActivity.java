package com.example.buyeasy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buyeasy.view.TitleView;

public class GoodsListActivity extends AppCompatActivity {

    private static final String ARG_KIND = "kind";

    private TitleView mTitleTV;
    private Button mSearchBTN;
    private Button mSortBTN;
    private Spinner mShowFormatSP;
    private ListView mGoodsLV;
    private GridView mGoodsGV;

    private String mKind;

    public static Intent newIntent(Context context, String kind) {
        Intent intent = new Intent(context, GoodsListActivity.class);
        intent.putExtra(ARG_KIND, kind);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);

        mKind = getIntent().getStringExtra(ARG_KIND);

        initView();
    }

    private void initView() {
        mTitleTV = findViewById(R.id.goods_list_title_tv);
        mSearchBTN = findViewById(R.id.goods_list_search_btn);
        mSortBTN = findViewById(R.id.goods_list_sort_btn);
        mShowFormatSP = findViewById(R.id.goods_list_show_format_sp);
        mGoodsLV = findViewById(R.id.goods_list_lv);
        mGoodsGV = findViewById(R.id.goods_list_gv);

        setupTitleView();
    }

    private void setupTitleView() {
        mTitleTV.setBackgroundResource(R.color.red);
        mTitleTV.setTitleText(mKind);
        mTitleTV.setTitleTextColor(Color.WHITE);
        mTitleTV.setLeftImageResource(R.mipmap.icon_back);
        mTitleTV.setRightImageResource(R.mipmap.icon_gwc);

        mTitleTV.setOnLeftImageClickListener(new TitleView.OnImageClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });

        mTitleTV.setOnRightImageClickListener(new TitleView.OnImageClickListener() {
            @Override
            public void onClick() {

            }
        });
    }
}