package com.example.Liuhang0120.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Liuhang0120.R;
import com.example.Liuhang0120.data.bean.Login;
import com.example.Liuhang0120.di.constract.LoginConstract;
import com.example.Liuhang0120.di.presenter.LoginPresenterImpl;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements LoginConstract.LoginView,View.OnClickListener {

    private LoginConstract.LoginPresenter loginPresenter;

    private CheckBox jizhu;
    private Button zhuce;
    private Button login;
    private EditText phone;
    private EditText pwd;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginPresenter = new LoginPresenterImpl();
        loginPresenter.attachView(this);

        phone = findViewById(R.id.phone);
        pwd = findViewById(R.id.pwd);
        jizhu = findViewById(R.id.jizhu);
        login = findViewById(R.id.login);
        zhuce = findViewById(R.id.zhuce);
        login.setOnClickListener(this);
        zhuce.setOnClickListener(this);
        jizhu.setOnClickListener(this);

        sp = getSharedPreferences("login",MODE_PRIVATE);
        if ( sp.getBoolean("ca",false)){
            phone.setText(sp.getString("name",""));
            pwd.setText(sp.getString("password",""));
            jizhu.setChecked(true);

        }


}

    @Override
    public void showLoginData(final String reponseData) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,reponseData,Toast.LENGTH_LONG).show();
                Gson gson = new Gson();
                Login login = gson.fromJson(reponseData,Login.class);
                String status = login.getStatus();
                if (status.equals("0000")){
                    Intent intent = new Intent(MainActivity.this,TwoActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                String name = phone.getText().toString();
                String password = pwd.getText().toString();
                loginPresenter.requestLoginData(name,password);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("name",name);
                editor.putString("password",password);
                editor.putBoolean("ca",jizhu.isChecked());
                editor.commit();
                break;
            case R.id.zhuce:
                Intent intent = new Intent(MainActivity.this,ResigActivity.class);
                startActivity(intent);
                break;
        }
    }
}
