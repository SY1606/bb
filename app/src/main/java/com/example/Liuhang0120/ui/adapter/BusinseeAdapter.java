package com.example.Liuhang0120.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.Liuhang0120.R;
import com.example.Liuhang0120.data.bean.Shop;


import java.util.List;

public class BusinseeAdapter extends BaseQuickAdapter<Shop.DataBean,BaseViewHolder> {

    private RecyclerView recyclerView1;

    OnBussinessItemClickListener onBussinessItemClickListener;

    public void setOnBussinessItemClickListener(OnBussinessItemClickListener onBussinessItemClickListener){
        this.onBussinessItemClickListener = onBussinessItemClickListener;
    }

    public interface OnBussinessItemClickListener{
        public void onCallBack();
    }

    public BusinseeAdapter(int layoutResId, @Nullable List<Shop.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final Shop.DataBean item) {
        //找到商家的控件
        helper.setText(R.id.business_name,item.getSellerName());

        //避免焦点抢占
        final CheckBox checkBox = helper.getView(R.id.c_business);
        checkBox.setOnCheckedChangeListener(null);
        //条目是否选中状态
        checkBox.setChecked(item.getBusinessChecked());


        recyclerView1 = helper.getView(R.id.recyclerView1);
        //子商品条目数据
       List<Shop.DataBean.ListBean> list = item.getList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);

        final GoodsAdapter goodsAdapter = new GoodsAdapter(R.layout.frag_goods, list);
        recyclerView1.setLayoutManager(linearLayoutManager);
        recyclerView1.setAdapter(goodsAdapter);



        goodsAdapter.setOnGoodsItemClickListener(new GoodsAdapter.onGoodsItemClickListener() {
            @Override
            public void onCallBack() {
                boolean result = true;
                for (int i =0;i<item.getList().size();i++){
                    result = result&item.getList().get(i).getGoodsChecked();
                }
                checkBox.setChecked(result);
                goodsAdapter.notifyDataSetChanged();
                onBussinessItemClickListener.onCallBack();
            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<item.getList().size();i++){
                    item.getList().get(i).setGoodsChecked(checkBox.isChecked());
                }
                item.setBusinessChecked(checkBox.isChecked());
                notifyDataSetChanged();
                onBussinessItemClickListener.onCallBack();
            }
        });

    }
}
