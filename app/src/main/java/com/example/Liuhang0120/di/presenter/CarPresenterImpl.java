package com.example.Liuhang0120.di.presenter;

import com.example.Liuhang0120.di.constract.CarContract;
import com.example.Liuhang0120.di.model.CarModelImpl;

import java.lang.ref.SoftReference;

public class CarPresenterImpl implements CarContract.CarPresenter<CarContract.CarView> {

    CarContract.CarModel carModel;
    CarContract.CarView carView;
    private SoftReference<CarContract.CarView> softReference;

    @Override
    public void attchView(CarContract.CarView carView) {
        this.carView = carView;
        softReference = new SoftReference<>(carView);
        carModel = new CarModelImpl();
    }

    @Override
    public void detchView(CarContract.CarView carView) {
        softReference.clear();
    }

    @Override
    public void requestCarData() {
        carModel.containCarData(new CarContract.CarModel.CallBack() {
            @Override
            public void onCallBack(String message) {
                carView.showCarData(message);
            }
        });
    }
}
