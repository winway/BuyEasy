package com.example.buyeasy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buyeasy.adapter.ReceiverListAdapter;
import com.example.buyeasy.bean.ReceiverInfoBean;
import com.example.buyeasy.databinding.ActivityReceiverInfoBinding;
import com.example.buyeasy.db.DBManager;
import com.example.buyeasy.dialog.AddReceiverDialog;
import com.example.buyeasy.view.TitleView;

import java.util.ArrayList;
import java.util.List;

public class ReceiverInfoActivity extends AppCompatActivity {

    public static final String EXTRA_RECEIVER_INFO = "receiver_info";
    private static final String TAG = "ReceiverInfoActivity";
    private ActivityReceiverInfoBinding mBinding;

    private ReceiverListAdapter mReceiverListAdapter;
    private List<ReceiverInfoBean> mReceiverListAdapterData;

    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityReceiverInfoBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mPreferences = getSharedPreferences("receiver_info", MODE_PRIVATE);

        setupTitleView();

        addReceiverListFooter();

        initReceiverListAdapter();

        refreshReceiverListAdapter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        int topId = mReceiverListAdapterData.get(0).getId();
        int defaultId = mReceiverListAdapter.getDefaultId();
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt("top", topId);
        editor.putInt("default", defaultId);
        editor.apply();

        Log.i(TAG, "onDestroy: " + topId + "," + defaultId);
    }

    private void addReceiverListFooter() {
        View view = getLayoutInflater().inflate(R.layout.footer_lv_receiver_info, null);
        Button addBTN = view.findViewById(R.id.footer_lv_receiver_info_add_btn);
        mBinding.receiverInfoLv.addFooterView(view);

        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddReceiverDialog();
            }
        });
    }

    private void showAddReceiverDialog() {
        AddReceiverDialog dialog = new AddReceiverDialog(this);
        dialog.show();
        dialog.setTitleText("添加收货地址");
        dialog.setOnSaveReceiverInfoListener(new AddReceiverDialog.OnSaveReceiverInfoListener() {
            @Override
            public void onSave(ReceiverInfoBean infoBean) {
                if (DBManager.isExist(infoBean)) {
                    Toast.makeText(ReceiverInfoActivity.this, "信息已存在", Toast.LENGTH_SHORT).show();
                } else {
                    long id = DBManager.add(infoBean);
                    if (id > 0) {
                        Toast.makeText(ReceiverInfoActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                        infoBean.setId((int) id);
                        mReceiverListAdapterData.add(infoBean);
                        mReceiverListAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    private void refreshReceiverListAdapter() {
        mReceiverListAdapterData.clear();
        List<ReceiverInfoBean> beanList = DBManager.queryAll();
        mReceiverListAdapterData.addAll(beanList);

        int topId = mPreferences.getInt("top", -1);
        int defaultId = mPreferences.getInt("default", -1);
        if (topId >= 0 || defaultId >= 0) {
            for (int i = 0; i < mReceiverListAdapterData.size(); i++) {
                ReceiverInfoBean bean = mReceiverListAdapterData.get(i);
                if (bean.getId() == topId) {
                    mReceiverListAdapterData.remove(bean);
                    mReceiverListAdapterData.add(0, bean);
                }

                if (bean.getId() == defaultId) {
                    bean.setDefault(true);
                }
            }
        }

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