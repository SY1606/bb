package com.example.Liuhang0120.di.model;

import com.example.Liuhang0120.data.Contant;
import com.example.Liuhang0120.di.constract.CarContract;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class CarModelImpl implements CarContract.CarModel {
    @Override
    public void containCarData(final CallBack callBack) {

        OkGo.<String>get(Contant.Car).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String responseData = response.body().toString();
                callBack.onCallBack(responseData);
            }
        });
    }
}
