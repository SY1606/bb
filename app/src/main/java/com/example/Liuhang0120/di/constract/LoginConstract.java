package com.example.Liuhang0120.di.constract;

public interface LoginConstract {

    public interface LoginView{
        public void showLoginData(String reponseData);
    }

    public interface LoginPresenter<LoginView>{
        public void attachView(LoginView loginView);
        public void detachView(LoginView loginView);

        public void requestLoginData(String name,String password);
    }

    public interface LoginModel{
        public void containLoginData(String name,String password,CallBack callBack);

        public interface CallBack{
            public void onCallBack(String reponseData);
        }
    }
}
