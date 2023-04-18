package com.example.buyeasy;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buyeasy.view.TitleView;

public class MainActivity extends AppCompatActivity {

    private TitleView mTitleTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitleTV = findViewById(R.id.main_title_tv);

        setupTitleView();

    }

    private void setupTitleView() {
        mTitleTV.setTitleText("日常用品");
        mTitleTV.setLeftImageResource(R.mipmap.icon_back);
        mTitleTV.setRightImageResource(R.mipmap.icon_setting);

        mTitleTV.setOnLeftImageClickListener(new TitleView.OnImageClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
        mTitleTV.setOnRightImageClickListener(new TitleView.OnImageClickListener() {
            @Override
            public void onClick() {
                Toast.makeText(MainActivity.this, "Clicked right image", Toast.LENGTH_SHORT).show();
            }
        });
    }
}