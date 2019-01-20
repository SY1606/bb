package com.example.Liuhang0120.di.constract;

public interface CarContract {
    public interface CarView{
        public void showCarData(String message);
    }
    public interface CarPresenter<CarView>{
        public void attchView(CarView carView);
        public void detchView(CarView carView);

        public void requestCarData();
    }

    public interface CarModel{
        public void containCarData(CallBack callBack);
        public interface CallBack{
            public void onCallBack(String message);
        }
    }
}
