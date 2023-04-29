package com.example.buyeasy.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.buyeasy.bean.ReceiverInfoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @PackageName: com.example.buyeasy.db
 * @ClassName: DBManager
 * @Author: winwa
 * @Date: 2023/4/29 7:32
 * @Description:
 **/
public class DBManager {

    private static SQLiteDatabase sDatabase;

    public static void initDB(Context context) {
        DBOpenHelper helper = new DBOpenHelper(context);
        sDatabase = helper.getWritableDatabase();
    }

    public static List<ReceiverInfoBean> queryAll() {
        List<ReceiverInfoBean> beanList = new ArrayList<>();

        Cursor cursor = sDatabase.rawQuery("select * from receiver_info", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            String city = cursor.getString(cursor.getColumnIndex("city"));
            String street = cursor.getString(cursor.getColumnIndex("street"));

            beanList.add(new ReceiverInfoBean(id, name, phone, city, street));
        }

        return beanList;
    }
}
