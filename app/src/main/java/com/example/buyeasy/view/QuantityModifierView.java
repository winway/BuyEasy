package com.example.buyeasy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
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
public class QuantityModifierView extends LinearLayout {
    public QuantityModifierView(Context context) {
        this(context, null);
    }

    public QuantityModifierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_quantity_modifier, this);
    }
}
