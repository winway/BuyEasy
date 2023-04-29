package com.example.buyeasy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buyeasy.adapter.ReceiverListAdapter;
import com.example.buyeasy.bean.ReceiverInfoBean;
import com.example.buyeasy.databinding.ActivityReceiverInfoBinding;
import com.example.buyeasy.db.DBManager;
import com.example.buyeasy.view.TitleView;

import java.util.ArrayList;
import java.util.List;

public class ReceiverInfoActivity extends AppCompatActivity {

    public static final String EXTRA_RECEIVER_INFO = "receiver_info";

    private ActivityReceiverInfoBinding mBinding;

    private ReceiverListAdapter mReceiverListAdapter;
    private List<ReceiverInfoBean> mReceiverListAdapterData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityReceiverInfoBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        setupTitleView();

        initReceiverListAdapter();

        refreshReceiverListAdapter();
    }

    private void refreshReceiverListAdapter() {
        mReceiverListAdapterData.clear();
        List<ReceiverInfoBean> beanList = DBManager.queryAll();
        mReceiverListAdapterData.addAll(beanList);
        mReceiverListAdapter.notifyDataSetChanged();
    }

    private void initReceiverListAdapter() {
        mReceiverListAdapterData = new ArrayList<>();
        mReceiverListAdapter = new ReceiverListAdapter(this, mReceiverListAdapterData);
        mBinding.receiverInfoLv.setAdapter(mReceiverListAdapter);

        mBinding.receiverInfoLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ReceiverInfoBean receiverInfoBean = mReceiverListAdapterData.get(i);
                Intent intent = new Intent();
                intent.putExtra(EXTRA_RECEIVER_INFO, receiverInfoBean);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void setupTitleView() {
        mBinding.receiverInfoTitleTv.setBackgroundResource(R.color.red);
        mBinding.receiverInfoTitleTv.setTitleText("地址管理");
        mBinding.receiverInfoTitleTv.setTitleTextColor(Color.WHITE);
        mBinding.receiverInfoTitleTv.setLeftImageResource(R.mipmap.icon_back);
        mBinding.receiverInfoTitleTv.setRightImageVisibility(false);

        mBinding.receiverInfoTitleTv.setOnLeftImageClickListener(new TitleView.OnImageClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
    }
}