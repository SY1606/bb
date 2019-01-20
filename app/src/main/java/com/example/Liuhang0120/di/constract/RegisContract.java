package com.example.Liuhang0120.di.constract;

public interface RegisContract {

    public interface RegisView{
        public void showRegisData(String reponseData);
    }

    public interface RegisPresenter<RegisView>{
        public void attachView(RegisView regisView);
        public void detachView(RegisView regisView);

        public void requestRegisData(String name,String password);
    }

    public interface RegisModel{
        public void containRegisData(String name,String password,CallBack callBack);

        public interface CallBack{
            public void onCallBack(String reponseData);
        }
    }

}
