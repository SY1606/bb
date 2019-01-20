package com.example.Liuhang0120.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Liuhang0120.R;
import com.example.Liuhang0120.data.bean.Zhu;
import com.example.Liuhang0120.di.constract.RegisContract;
import com.example.Liuhang0120.di.presenter.RegisPresenterImpl;
import com.google.gson.Gson;

public class ResigActivity extends AppCompatActivity implements RegisContract.RegisView,View.OnClickListener {

    private RegisContract.RegisPresenter regisPresenter;
    private EditText ed_pwd;
    private EditText ed_phone;
    private Button ed_zhuce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resig);

        regisPresenter = new RegisPresenterImpl();
        regisPresenter.attachView(this);

        ed_phone = findViewById(R.id.ed_phone);
        ed_pwd = findViewById(R.id.ed_pwd);

        ed_zhuce = findViewById(R.id.ed_zhuce);
        ed_zhuce.setOnClickListener(this);
    }

    @Override
    public void showRegisData(final String reponseData) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ResigActivity.this,reponseData,Toast.LENGTH_SHORT).show();
                Gson gson = new Gson();
                Zhu zhu = gson.fromJson(reponseData,Zhu.class);
                String status = zhu.getStatus();
                if (status.equals("0000")){
                    Toast.makeText(ResigActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ResigActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        String name = ed_phone.getText().toString();
        String password = ed_pwd.getText().toString();
        regisPresenter.requestRegisData(name,password);
    }
}
