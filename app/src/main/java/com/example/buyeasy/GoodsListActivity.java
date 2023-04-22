package com.example.buyeasy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buyeasy.adapter.GoodsGridAdapter;
import com.example.buyeasy.adapter.GoodsListAdapter;
import com.example.buyeasy.bean.GoodsBean;
import com.example.buyeasy.bean.GoodsData;
import com.example.buyeasy.view.TitleView;

import java.util.ArrayList;
import java.util.List;

public class GoodsListActivity extends AppCompatActivity {

    private static final String ARG_KIND = "kind";

    private TitleView mTitleTV;
    private Button mSearchBTN;
    private Button mSortBTN;
    private Spinner mShowFormatSP;
    private ListView mGoodsLV;
    private GridView mGoodsGV;

    private GoodsListAdapter mGoodsListAdapter;
    private GoodsGridAdapter mGoodsGridAdapter;
    private List<GoodsBean> mGoodsAdapterData;

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

        initGoodsAdapter();

        refreshGoodsAdapter();
    }

    private void refreshGoodsAdapter() {
        mGoodsAdapterData.clear();
        for (int i = 0; i < GoodsData.goodsDataList.size(); i++) {
            GoodsBean goodsBean = GoodsData.goodsDataList.get(i);
            if (goodsBean.getKind().equals(mKind)) {
                mGoodsAdapterData.add(goodsBean);
            }
        }
        mGoodsListAdapter.notifyDataSetChanged();
        mGoodsGridAdapter.notifyDataSetChanged();
    }

    private void initGoodsAdapter() {
        mGoodsAdapterData = new ArrayList<>();

        mGoodsListAdapter = new GoodsListAdapter(this, mGoodsAdapterData);
        mGoodsLV.setAdapter(mGoodsListAdapter);

        mGoodsGridAdapter = new GoodsGridAdapter(this, mGoodsAdapterData);
        mGoodsGV.setAdapter(mGoodsGridAdapter);
    }

    private void initView() {
        mTitleTV = findViewById(R.id.goods_list_title_tv);
        mSearchBTN = findViewById(R.id.goods_list_search_btn);
        mSortBTN = findViewById(R.id.goods_list_sort_btn);
        mShowFormatSP = findViewById(R.id.goods_list_show_format_sp);
        mGoodsLV = findViewById(R.id.goods_list_lv);
        mGoodsGV = findViewById(R.id.goods_list_gv);

        setupTitleView();

        setupShowFormatSpinner();
    }

    private void setupShowFormatSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new String[]{"列表展示", "网格展示"});
        mShowFormatSP.setAdapter(adapter);

        mShowFormatSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        mGoodsLV.setVisibility(View.VISIBLE);
                        mGoodsGV.setVisibility(View.GONE);
                        break;
                    case 1:
                        mGoodsLV.setVisibility(View.GONE);
                        mGoodsGV.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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