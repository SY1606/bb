package com.example.Liuhang0120.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.example.Liuhang0120.R;
import com.example.Liuhang0120.ui.frag.FragCar;
import com.example.Liuhang0120.ui.frag.FragMy;

public class TwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        final FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        final FragCar fragCar = new FragCar();
        final FragMy fragMy = new FragMy();
        transaction.add(R.id.frag,fragCar);
        transaction.add(R.id.frag,fragMy);
        transaction.show(fragCar).hide(fragMy);
        transaction.commit();
        RadioGroup rg = findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction1 = manager.beginTransaction();
                switch (checkedId){
                    case 1:
                        transaction1.show(fragCar).hide(fragMy);
                        break;
                    case 2:
                        transaction1.show(fragMy).hide(fragCar);
                        break;

                }
                transaction1.commit();
            }
        });
    }
}
