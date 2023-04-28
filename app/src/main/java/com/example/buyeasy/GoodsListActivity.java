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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GoodsListActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String EXTRA_KIND = "kind";

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
    private int mSortOrder = 1;

    public static Intent newIntent(Context context, String kind) {
        Intent intent = new Intent(context, GoodsListActivity.class);
        intent.putExtra(EXTRA_KIND, kind);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);

        mKind = getIntent().getStringExtra(EXTRA_KIND);

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

        mSearchBTN.setOnClickListener(this);
        mSortBTN.setOnClickListener(this);
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
                startActivity(new Intent(GoodsListActivity.this, CartActivity.class));
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.goods_list_search_btn:
                break;
            case R.id.goods_list_sort_btn:
                sort();
                break;
        }
    }

    private void sort() {
        mSortOrder = mSortOrder * -1;

        Collections.sort(mGoodsAdapterData, new Comparator<GoodsBean>() {
            @Override
            public int compare(GoodsBean t1, GoodsBean t2) {
                if (t1.getPrice() > t2.getPrice()) {
                    return 1 * mSortOrder;
                } else if (t1.getPrice() < t2.getPrice()) {
                    return -1 * mSortOrder;
                } else {
                    return 0;
                }
            }
        });

        mGoodsListAdapter.notifyDataSetChanged();
        mGoodsGridAdapter.notifyDataSetChanged();
    }
}