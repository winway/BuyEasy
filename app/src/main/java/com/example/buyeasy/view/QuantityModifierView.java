package com.example.buyeasy.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.buyeasy.R;

/**
 * @PackageName: com.example.buyeasy.view
 * @ClassName: QuantityModifierView
 * @Author: winwa
 * @Date: 2023/4/20 8:10
 * @Description:
 **/
public class QuantityModifierView extends LinearLayout implements View.OnClickListener {
    private static final String TAG = "QuantityModifierView";

    private Button mReduceBTN;
    private Button mIncreaseBTN;
    private EditText mQuantityET;

    private int mStock;

    private OnQuantityChangeListener mOnQuantityChangeListener;

    public QuantityModifierView(Context context) {
        this(context, null);
    }

    public QuantityModifierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_quantity_modifier, this);

        initView();
    }

    public void setOnQuantityChangeListener(OnQuantityChangeListener onQuantityChangeListener) {
        mOnQuantityChangeListener = onQuantityChangeListener;
    }

    public void setStock(int stock) {
        mStock = stock;
    }

    public int getQuantity() {
        String input = mQuantityET.getText().toString().trim();
        int quantity;
        try {
            quantity = Integer.parseInt(input);
        } catch (Exception e) {
            e.printStackTrace();
            quantity = 1;
        }

        return quantity;
    }

    private void initView() {
        mReduceBTN = findViewById(R.id.quantity_modifier_reduce_btn);
        mIncreaseBTN = findViewById(R.id.quantity_modifier_increase_btn);
        mQuantityET = findViewById(R.id.quantity_modifier_quantity_et);

        mReduceBTN.setOnClickListener(this);
        mIncreaseBTN.setOnClickListener(this);

        mQuantityET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String input = editable.toString().trim();
                int quantity;

                try {
                    quantity = Integer.parseInt(input);
                } catch (Exception e) {
                    e.printStackTrace();
                    quantity = 1;
                    mQuantityET.setText("" + quantity);
                    return;
                }

                if (quantity < 1) {
                    quantity = 1;
                    mQuantityET.setText("" + quantity);
                    return;
                } else if (quantity > mStock) {
                    quantity = mStock;
                    mQuantityET.setText("" + quantity);
                    return;
                }

                if (mOnQuantityChangeListener != null) {
                    mOnQuantityChangeListener.onQuantityChange(quantity);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        int quantity = getQuantity();

        switch (view.getId()) {
            case R.id.quantity_modifier_reduce_btn:
                quantity = Math.max(1, quantity - 1);
                break;
            case R.id.quantity_modifier_increase_btn:
                quantity = Math.min(mStock, quantity + 1);
                break;
        }

        mQuantityET.setText("" + quantity);
        mQuantityET.clearFocus();
    }

    public interface OnQuantityChangeListener {
        public void onQuantityChange(int quantity);
    }
}
