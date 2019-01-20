package com.example.Liuhang0120.ui.wight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.Liuhang0120.R;

public class CaculatorView extends LinearLayout implements View.OnClickListener {

    private Button jia;
    private Button jian;
    private TextView numbers;

    public CaculatorView(Context context) {
        super(context);
    }

    public CaculatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.caculator,this);
        jia = view.findViewById(R.id.jia);
        jian = view.findViewById(R.id.jian);
        numbers = view.findViewById(R.id.number);
        jia.setOnClickListener(this);
        jian.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String numberString = numbers.getText().toString();
        int number = Integer.parseInt(numberString);
        switch (v.getId()){
            case R.id.jian:
                number = number-1;
                if (number<0){
                    number=0;
                    numbers.setText(String.valueOf(number));
                }
                numbers.setText(String.valueOf(number));
                onCaculatorListener.dis(number);
                break;
            case R.id.jia:
                number = number+1;
                numbers.setText(String.valueOf(number));
                onCaculatorListener.adds(number);
                break;
        }
    }

    onCaculatorListener onCaculatorListener;

    public interface onCaculatorListener{
        public void dis(int number);
        public void adds(int number);

    }

    public void setOnCaculatorListener(CaculatorView.onCaculatorListener onCaculatorListener) {
        this.onCaculatorListener = onCaculatorListener;
    }
}
