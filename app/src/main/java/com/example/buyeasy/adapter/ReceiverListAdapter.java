package com.example.buyeasy.adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.buyeasy.R;
import com.example.buyeasy.bean.ReceiverInfoBean;
import com.example.buyeasy.databinding.ItemLvReceiverInfoBinding;
import com.example.buyeasy.db.DBManager;
import com.example.buyeasy.dialog.AddReceiverDialog;
import com.example.buyeasy.utils.DialogUtils;

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

    private int mDefaultId = -1;

    public ReceiverListAdapter(Context context, List<ReceiverInfoBean> data) {
        mContext = context;
        mData = data;
    }

    public int getDefaultId() {
        return mDefaultId;
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

        holder.mBinding.itemLvReceiverInfoDefaultRb.setChecked(receiverInfoBean.isDefault());
        if (receiverInfoBean.isDefault()) {
            mDefaultId = receiverInfoBean.getId();
        }

        holder.mBinding.itemLvReceiverInfoDefaultRb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!receiverInfoBean.isDefault()) {
                    for (int j = 0; j < mData.size(); j++) {
                        mData.get(j).setDefault(false);
                    }
                    receiverInfoBean.setDefault(true);
                    mDefaultId = receiverInfoBean.getId();
                    notifyDataSetChanged();
                }
            }
        });

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

        holder.mBinding.itemLvReceiverInfoDeleteTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.showCommonAlertDialog(mContext, "提示信息", "确认删除吗？",
                        "取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        },
                        "确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DBManager.deleteById(receiverInfoBean.getId());
                                mData.remove(receiverInfoBean);
                                notifyDataSetChanged();
                                dialogInterface.cancel();
                            }
                        });
            }
        });

        holder.mBinding.itemLvReceiverInfoCopyTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copyToClipBoard(receiverInfoBean.getClipData());
            }
        });

        holder.mBinding.itemLvReceiverInfoTopTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mData.remove(receiverInfoBean);
                mData.add(0, receiverInfoBean);
                notifyDataSetChanged();
            }
        });

        return view;
    }

    private void copyToClipBoard(String content) {
        ClipboardManager manager = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData data = ClipData.newPlainText("receiver", content);
        manager.setPrimaryClip(data);
    }

    class ViewHolder {
        ItemLvReceiverInfoBinding mBinding;

        public ViewHolder(View view) {
            mBinding = ItemLvReceiverInfoBinding.bind(view);
        }
    }
}
