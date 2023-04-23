package com.example.buyeasy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.buyeasy.R;
import com.example.buyeasy.bean.GoodsBean;
import com.example.buyeasy.bean.GoodsData;
import com.example.buyeasy.view.QuantityModifierView;
import com.squareup.picasso.Picasso;

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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_gv_goods_list, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        GoodsBean goodsBean = mData.get(i);
        holder.mNameTV.setText(goodsBean.getTitle());
        holder.mPriceTV.setText("¥ " + goodsBean.getPrice());
        Picasso.with(mContext).load(goodsBean.getPic()).into(holder.mPictureIV);
        holder.mQuantityQMV.setStock(goodsBean.getCount());

        ViewHolder finalHolder = holder;
        holder.mBuyBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = finalHolder.mQuantityQMV.getQuantity();
                GoodsBean goodsBeanCopy = GoodsBean.copy(goodsBean);
                goodsBeanCopy.setBuycount(quantity);
                GoodsData.addGoodsToBuyList(goodsBeanCopy);

                GoodsData.printBuyList();
            }
        });

        return view;
    }

    class ViewHolder {
        ImageView mPictureIV;
        TextView mNameTV;
        TextView mPriceTV;
        QuantityModifierView mQuantityQMV;
        Button mBuyBTN;

        public ViewHolder(View view) {
            mPictureIV = view.findViewById(R.id.item_gv_goods_list_picture_iv);
            mNameTV = view.findViewById(R.id.item_gv_goods_list_name_tv);
            mPriceTV = view.findViewById(R.id.item_gv_goods_list_price_tv);
            mQuantityQMV = view.findViewById(R.id.item_gv_goods_list_quantity_qmv);
            mBuyBTN = view.findViewById(R.id.item_gv_goods_list_buy_btn);
        }
    }
}
