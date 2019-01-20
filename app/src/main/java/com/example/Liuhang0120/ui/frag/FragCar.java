package com.example.Liuhang0120.ui.frag;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.Liuhang0120.R;
import com.example.Liuhang0120.data.bean.Shop;
import com.example.Liuhang0120.di.constract.CarContract;
import com.example.Liuhang0120.di.presenter.CarPresenterImpl;
import com.example.Liuhang0120.ui.adapter.BusinseeAdapter;
import com.google.gson.Gson;

import java.util.List;

public class FragCar extends Fragment implements CarContract.CarView,View.OnClickListener {

    private RecyclerView recycler_zong;
    private CheckBox all;
    private TextView price;
    private CarContract.CarPresenter carPresenter;

    private List<Shop.DataBean> list;
    private BusinseeAdapter businseeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.farg_car,container,false);
        carPresenter = new CarPresenterImpl();
        carPresenter.attchView(this);
        carPresenter.requestCarData();
        recycler_zong = view.findViewById(R.id.recycler_zong);
        all = view.findViewById(R.id.all);
        price = view.findViewById(R.id.price);

        return view;
    }

        @Override
        public void showCarData(final String message) {
            all.setOnCheckedChangeListener(null);
             all.setOnClickListener(this);
             Gson gson = new Gson();
             Shop shop = gson.fromJson(message,Shop.class);

            list = shop.getData();

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                recycler_zong.setLayoutManager(linearLayoutManager);

            businseeAdapter = new BusinseeAdapter(R.layout.farg_car, list);
                recycler_zong.setAdapter(businseeAdapter);


            businseeAdapter.setOnBussinessItemClickListener(new BusinseeAdapter.OnBussinessItemClickListener() {
                @Override
                public void onCallBack() {
                    boolean result = true;
                    for (int i=0;i<list.size();i++){
                        boolean businessChecked = list.get(i).getBusinessChecked();
                        result = result&businessChecked;
                        for (int j=0;j<list.get(i).getList().size();j++){
                            boolean goodChecked = list.get(i).getList().get(j).getGoodsChecked();
                            result = result&goodChecked;
                        }
                    }
                    all.setChecked(result);
                    calculateTotalCount();
                }
            });
            }

    private void calculateTotalCount() {
        double totalCount = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).getList().size(); j++) {
                if (list.get(i).getList().get(j).getGoodsChecked() == true){
                    double price = list.get(i).getList().get(j).getPrice();
                    int defalutNumber = list.get(i).getList().get(j).getDefalutNumber();
                    double goodsPrice = price * defalutNumber;
                    totalCount = totalCount+goodsPrice;
                }
            }
        }
        price.setText("合计："+String.valueOf(totalCount));

    }


    @Override
    public void onClick(View v) {
        for (int i=0;i<list.size();i++){
            list.get(i).setBusinessChecked(all.isChecked());
            for (int j=0;j<list.get(i).getList().size();j++){
                list.get(i).getList().get(j).setGoodsChecked(all.isChecked());
            }
            businseeAdapter.notifyDataSetChanged();
            calculateTotalCount();
        }
    }
}
