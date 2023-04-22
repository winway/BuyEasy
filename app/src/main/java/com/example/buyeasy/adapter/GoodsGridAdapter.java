package com.example.buyeasy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.buyeasy.bean.GoodsBean;

import java.util.List;

/**
 * @PackageName: com.example.buyeasy.adapter
 * @ClassName: GoodsGridView
 * @Author: winwa
 * @Date: 2023/4/22 10:01
 * @Description:
 **/
public class GoodsGridAdapter extends BaseAdapter {
    private Context mContext;
    private List<GoodsBean> mData;

    public GoodsGridAdapter(Context context, List<GoodsBean> data) {
        mContext = context;
        mData = data;
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
