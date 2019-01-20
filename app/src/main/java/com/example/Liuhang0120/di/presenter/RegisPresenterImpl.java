package com.example.Liuhang0120.di.presenter;

import com.example.Liuhang0120.di.constract.RegisContract;
import com.example.Liuhang0120.di.model.RegisModelImpl;

import java.lang.ref.SoftReference;

public class RegisPresenterImpl implements RegisContract.RegisPresenter<RegisContract.RegisView> {

    RegisContract.RegisModel regisModel;
    RegisContract.RegisView regisView;
    private SoftReference<RegisContract.RegisView> softReference;


    @Override
    public void attachView(RegisContract.RegisView regisView) {
        this.regisView = regisView;
        softReference = new SoftReference<>(regisView);
        regisModel = new RegisModelImpl();
    }

    @Override
    public void detachView(RegisContract.RegisView regisView) {
        softReference.clear();
    }

    @Override
    public void requestRegisData(String name, String password) {
        regisModel.containRegisData(name, password, new RegisContract.RegisModel.CallBack() {
            @Override
            public void onCallBack(String reponseData) {
                regisView.showRegisData(reponseData);
            }
        });
    }
}
