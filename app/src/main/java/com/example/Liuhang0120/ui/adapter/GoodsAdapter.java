package com.example.Liuhang0120.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.Liuhang0120.R;
import com.example.Liuhang0120.data.bean.Shop;
import com.example.Liuhang0120.ui.wight.CaculatorView;
import java.util.List;

public class GoodsAdapter extends BaseQuickAdapter<Shop.DataBean.ListBean,BaseViewHolder> {

    //设置接口回调 点击事件
   onGoodsItemClickListener onGoodsItemClickListener;

   public void setOnGoodsItemClickListener(onGoodsItemClickListener onGoodsItemClickListener){
       this.onGoodsItemClickListener = onGoodsItemClickListener;
   }

   public interface onGoodsItemClickListener{
       public void onCallBack();
   }

    public GoodsAdapter(int layoutResId, @Nullable List<Shop.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final Shop.DataBean.ListBean item) {
        //找到控件
        helper.setText(R.id.goodsTitle,item.getTitle());
        helper.setText(R.id.goodsPrice,item.getPrice()+"");
        ImageView imageView = helper.getView(R.id.image);
        //图片切割
        String imagesString = item.getImages();
        String[] imageStr = imagesString.split("\\|");
        Glide.with(mContext).load(imageStr[0]).into(imageView);

        //找到商品的输入框控件
        CheckBox goods = helper.getView(R.id.goods);
        goods.setOnCheckedChangeListener(null);
        goods.setChecked(item.getGoodsChecked());

        goods.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setGoodsChecked(isChecked);
                onGoodsItemClickListener.onCallBack();
            }
        });
        CaculatorView caculatorView = helper.getView(R.id.cv_caculatorView);
        caculatorView.setOnCaculatorListener(new CaculatorView.onCaculatorListener() {
            @Override
            public void dis(int number) {
                item.setDefalutNumber(number);
                onGoodsItemClickListener.onCallBack();
            }

            @Override
            public void adds(int number) {
                item.setDefalutNumber(number);
                onGoodsItemClickListener.onCallBack();
            }
        });
    }
}
