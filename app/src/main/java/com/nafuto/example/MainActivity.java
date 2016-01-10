package com.nafuto.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nafuto.countdownview.CountDownView;

public class MainActivity extends AppCompatActivity {
    private CountDownView countDownView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countDownView = (CountDownView) findViewById(R.id.countDownView);

        countDownView.initCountDownView(2);
        countDownView.start();
    }
}
