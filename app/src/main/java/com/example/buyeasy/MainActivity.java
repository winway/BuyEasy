package com.example.buyeasy;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buyeasy.view.TitleView;

public class MainActivity extends AppCompatActivity {

    private TitleView mTitleTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitleTV = findViewById(R.id.main_title_tv);
        mTitleTV.setTitleText("日常用品");
    }
}