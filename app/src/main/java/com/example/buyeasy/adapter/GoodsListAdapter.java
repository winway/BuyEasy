package com.example.buyeasy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.buyeasy.CartActivity;
import com.example.buyeasy.R;
import com.example.buyeasy.bean.GoodsBean;
import com.example.buyeasy.bean.GoodsData;
import com.example.buyeasy.view.QuantityModifierView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @PackageName: com.example.buyeasy.adapter
 * @ClassName: GoodsListAdapter
 * @Author: winwa
 * @Date: 2023/4/22 9:38
 * @Description:
 **/
public class GoodsListAdapter extends BaseAdapter {
    private Context mContext;
    private List<GoodsBean> mData;
    private boolean isCart = false;

    public GoodsListAdapter(Context context, List<GoodsBean> data) {
        mContext = context;
        mData = data;
    }

    public GoodsListAdapter(Context context, List<GoodsBean> data, boolean isCart) {
        mContext = context;
        mData = data;
        this.isCart = isCart;
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_lv_goods_list, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        GoodsBean goodsBean = mData.get(i);

        holder.mNameTV.setText(goodsBean.getTitle());
        holder.mKindTV.setText(goodsBean.getKind());
        holder.mPriceTV.setText("¥ " + goodsBean.getPrice());
        Picasso.with(mContext).load(goodsBean.getPic()).into(holder.mPictureIV);
        holder.mQuantityQMV.setStock(goodsBean.getCount());
        holder.mQuantityQMV.setQuantityETText("" + goodsBean.getBuyCount());

        ViewHolder finalHolder = holder;
        if (isCart) {
            holder.mQuantityQMV.setOnQuantityChangeListener(new QuantityModifierView.OnQuantityChangeListener() {
                @Override
                public void onQuantityChange(int quantity) {
                    GoodsData.printBuyList();
                    goodsBean.setBuyCount(quantity);
                    GoodsData.printBuyList();
                    ((CartActivity) mContext).refreshTotalMoney();
                }
            });

            holder.mDeleteIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GoodsData.printBuyList();
                    mData.remove(goodsBean);
                    GoodsData.printBuyList();
                    notifyDataSetChanged();
                    ((CartActivity) mContext).refreshTotalMoney();
                }
            });
        } else {
            holder.mBuyBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int quantity = finalHolder.mQuantityQMV.getQuantity();
                    GoodsBean goodsBeanCopy = GoodsBean.copy(goodsBean);
                    goodsBeanCopy.setBuyCount(quantity);
                    GoodsData.addGoodsToBuyList(goodsBeanCopy);

                    GoodsData.printBuyList();
                }
            });
        }

        return view;
    }

    class ViewHolder {
        ImageView mPictureIV;
        TextView mNameTV;
        TextView mKindTV;
        TextView mPriceTV;
        QuantityModifierView mQuantityQMV;
        ImageView mDeleteIV;
        Button mBuyBTN;

        public ViewHolder(View view) {
            mPictureIV = view.findViewById(R.id.item_lv_goods_list_picture_iv);
            mNameTV = view.findViewById(R.id.item_lv_goods_list_name_tv);
            mKindTV = view.findViewById(R.id.item_lv_goods_list_kind_tv);
            mPriceTV = view.findViewById(R.id.item_lv_goods_list_price_tv);
            mQuantityQMV = view.findViewById(R.id.item_lv_goods_list_quantity_qmv);
            mDeleteIV = view.findViewById(R.id.item_lv_goods_list_delete_iv);
            mBuyBTN = view.findViewById(R.id.item_lv_goods_list_buy_btn);

            if (isCart) {
                mDeleteIV.setVisibility(View.VISIBLE);
                mBuyBTN.setVisibility(View.GONE);
            } else {
                mDeleteIV.setVisibility(View.GONE);
                mBuyBTN.setVisibility(View.VISIBLE);
            }
        }
    }
}
