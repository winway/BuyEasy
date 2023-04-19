package com.example.buyeasy;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buyeasy.bean.GoodsData;
import com.example.buyeasy.view.TitleView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TitleView mTitleTV;
    private ListView mKindLV;

    private ArrayAdapter<String> mKindListAdapter;
    private List<String> mKindListAdapterData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        refreshKindListAdapter();
    }

    private void refreshKindListAdapter() {
        mKindListAdapterData.addAll(GoodsData.dailyKindList);
        mKindListAdapter.notifyDataSetChanged();
    }

    private void initView() {
        mTitleTV = findViewById(R.id.main_title_tv);
        mKindLV = findViewById(R.id.main_kind_lv);

        setupTitleView();

        initKindListAdapter();
    }

    private void initKindListAdapter() {
        mKindListAdapterData = new ArrayList<>();
        mKindListAdapter = new ArrayAdapter<>(this, R.layout.item_lv_main_kind, R.id.item_lv_main_kind_tv, mKindListAdapterData);
        mKindLV.setAdapter(mKindListAdapter);
    }

    private void setupTitleView() {
        mTitleTV.setBackgroundResource(R.color.red);
        mTitleTV.setTitleText("日常用品");
        mTitleTV.setTitleTextColor(Color.WHITE);
        mTitleTV.setLeftImageVisibility(true);
        mTitleTV.setRightImageVisibility(false);
        mTitleTV.setLeftImageResource(R.mipmap.icon_back);

        mTitleTV.setOnLeftImageClickListener(new TitleView.OnImageClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
    }
}