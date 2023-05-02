package com.example.buyeasy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.buyeasy.R;
import com.example.buyeasy.bean.ReceiverInfoBean;
import com.example.buyeasy.databinding.ItemLvReceiverInfoBinding;
import com.example.buyeasy.db.DBManager;
import com.example.buyeasy.dialog.AddReceiverDialog;

import java.util.List;

/**
 * @PackageName: com.example.buyeasy.adapter
 * @ClassName: ReceiverListAdapter
 * @Author: winwa
 * @Date: 2023/4/29 7:49
 * @Description:
 **/
public class ReceiverListAdapter extends BaseAdapter {
    private Context mContext;
    private List<ReceiverInfoBean> mData;

    public ReceiverListAdapter(Context context, List<ReceiverInfoBean> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_lv_receiver_info, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        ReceiverInfoBean receiverInfoBean = mData.get(i);

        holder.mBinding.itemLvReceiverInfoNameTv.setText(receiverInfoBean.getName());
        holder.mBinding.itemLvReceiverInfoPhoneTv.setText(receiverInfoBean.getPhone());
        holder.mBinding.itemLvReceiverInfoCityTv.setText(receiverInfoBean.getCity());
        holder.mBinding.itemLvReceiverInfoStreetTv.setText(receiverInfoBean.getStreet());

        holder.mBinding.itemLvReceiverInfoModifyTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddReceiverDialog dialog = new AddReceiverDialog(mContext);
                dialog.show();
                dialog.setTitleText("修改收货地址");
                dialog.loadData(receiverInfoBean);
                dialog.setOnSaveReceiverInfoListener(new AddReceiverDialog.OnSaveReceiverInfoListener() {
                    @Override
                    public void onSave(ReceiverInfoBean infoBean) {
                        DBManager.updateById(infoBean);
                        receiverInfoBean.copyDataFromObject(infoBean);
                        notifyDataSetChanged();
                    }
                });
            }
        });

        return view;
    }

    class ViewHolder {
        ItemLvReceiverInfoBinding mBinding;

        public ViewHolder(View view) {
            mBinding = ItemLvReceiverInfoBinding.bind(view);
        }
    }
}
