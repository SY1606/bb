package com.example.Liuhang0120.di.model;

import com.example.Liuhang0120.data.Contant;
import com.example.Liuhang0120.di.constract.RegisContract;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class RegisModelImpl implements RegisContract.RegisModel {
    @Override
    public void containRegisData(String name, String password, final CallBack callBack) {
        requestRegisDataEnqueue(name,password,callBack);
    }

    private void requestRegisDataEnqueue(String name, String password, final CallBack callBack) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        FormBody formBody = new FormBody.Builder().build();

        Request request = new Request.Builder()
                .method("POST",formBody)
                .url(Contant.Zhuce+"?phone="+name+"&pwd="+password)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String responseData = e.getMessage();
                callBack.onCallBack(responseData);
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                String reponseData = response.body().string();
                callBack.onCallBack(reponseData);
            }
        });
    }
}