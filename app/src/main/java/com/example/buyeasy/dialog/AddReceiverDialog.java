package com.example.buyeasy.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.buyeasy.R;
import com.example.buyeasy.bean.ReceiverInfoBean;
import com.example.buyeasy.databinding.DialogReceiverInfoBinding;

/**
 * @PackageName: com.example.buyeasy.dialog
 * @ClassName: AddReceiverDialog
 * @Author: winwa
 * @Date: 2023/4/30 8:51
 * @Description:
 **/
public class AddReceiverDialog extends Dialog implements View.OnClickListener {

    private DialogReceiverInfoBinding mBinding;

    private OnSaveReceiverInfoListener mOnSaveReceiverInfoListener;

    public AddReceiverDialog(@NonNull Context context) {
        super(context);
    }

    public void setOnSaveReceiverInfoListener(OnSaveReceiverInfoListener onSaveReceiverInfoListener) {
        mOnSaveReceiverInfoListener = onSaveReceiverInfoListener;
    }

    public void setTitleText(String title) {
        mBinding.dialogReceiverInfoTitleTv.setText(title);
    }

    public void loadData(ReceiverInfoBean receiverInfoBean) {
        mBinding.dialogReceiverInfoNameEt.setText(receiverInfoBean.getName());
        mBinding.dialogReceiverInfoPhoneEt.setText(receiverInfoBean.getPhone());
        mBinding.dialogReceiverInfoCityEt.setText(receiverInfoBean.getCity());
        mBinding.dialogReceiverInfoStreetEt.setText(receiverInfoBean.getStreet());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DialogReceiverInfoBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.dialogReceiverInfoCloseIv.setOnClickListener(this);
        mBinding.dialogReceiverInfoAddBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_receiver_info_add_btn:
                ReceiverInfoBean receiverInfoBean = validateInput();
                if (receiverInfoBean != null) {
                    if (mOnSaveReceiverInfoListener != null) {
                        mOnSaveReceiverInfoListener.onSave(receiverInfoBean);
                    }
                    cancel();
                }
                break;
            case R.id.dialog_receiver_info_close_iv:
                cancel();
                break;
        }
    }

    public ReceiverInfoBean validateInput() {
        String name = mBinding.dialogReceiverInfoNameEt.getText().toString().trim();
        String phone = mBinding.dialogReceiverInfoPhoneEt.getText().toString().trim();
        String city = mBinding.dialogReceiverInfoCityEt.getText().toString().trim();
        String street = mBinding.dialogReceiverInfoStreetEt.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(city) || TextUtils.isEmpty(street)) {
            Toast.makeText(getContext(), "输入信息不能为空", Toast.LENGTH_SHORT).show();
            return null;
        }

        String phoneRegex = "^1(3\\d|4[5-9]|5[0-35-9]|6[567]|7[0-8]|8\\d|9[0-35-9])\\d{8}$";
        if (!phone.matches(phoneRegex)) {
            Toast.makeText(getContext(), "手机号无效", Toast.LENGTH_SHORT).show();
            return null;
        }

        return new ReceiverInfoBean(name, phone, city, street);
    }

    public interface OnSaveReceiverInfoListener {
        public void onSave(ReceiverInfoBean infoBean);
    }
}
