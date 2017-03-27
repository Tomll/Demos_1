package com.transsion.example.myapplication2.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.transsion.example.myapplication2.R;
import com.transsion.example.myapplication2.util.ToastUtil;

public class SMSActivity extends AppCompatActivity {
    private String stringMsg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stringMsg = getIntent().getStringExtra("stringMsg");
        TextView textView = (TextView) findViewById(R.id.tv1);
        textView.setText(stringMsg);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast(getApplicationContext(),"点击了");
            }
        });

    }
}
