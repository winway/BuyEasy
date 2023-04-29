package com.example.buyeasy;

import android.app.Application;

import com.example.buyeasy.db.DBManager;

/**
 * @PackageName: com.example.buyeasy
 * @ClassName: MyApplication
 * @Author: winwa
 * @Date: 2023/4/29 8:06
 * @Description:
 **/
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DBManager.initDB(getApplicationContext());
    }
}
