package com.example.Liuhang0120.di.presenter;

import com.example.Liuhang0120.di.constract.LoginConstract;
import com.example.Liuhang0120.di.model.LoginModelImpl;

import java.lang.ref.SoftReference;

public class LoginPresenterImpl implements LoginConstract.LoginPresenter<LoginConstract.LoginView> {

    LoginConstract.LoginModel loginModel;
    LoginConstract.LoginView loginView;
    private SoftReference<LoginConstract.LoginView> softReference;

    @Override
    public void attachView(LoginConstract.LoginView loginView) {
        this.loginView = loginView;
        softReference = new SoftReference<>(loginView);
        loginModel = new LoginModelImpl();
    }

    @Override
    public void detachView(LoginConstract.LoginView loginView) {
        softReference.clear();
    }

    @Override
    public void requestLoginData(String phone, String password) {
        loginModel.containLoginData(phone, password, new LoginConstract.LoginModel.CallBack() {
            @Override
            public void onCallBack(String reponseData) {
                loginView.showLoginData(reponseData);
            }
        });
    }
}
