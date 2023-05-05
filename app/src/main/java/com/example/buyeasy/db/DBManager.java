package com.example.buyeasy.db;

import android.content.ContentValues;
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

    public static boolean isExist(ReceiverInfoBean infoBean) {
        String sql = "select * from receiver_info where name='" + infoBean.getName() + "' and " +
                "phone='" + infoBean.getPhone() + "' and " +
                "city='" + infoBean.getCity() + "' and " +
                "street='" + infoBean.getStreet() + "'";
        Cursor cursor = sDatabase.rawQuery(sql, null);
        int count = cursor.getCount();
        if (count > 0) {
            return true;
        }
        return false;
    }

    public static long add(ReceiverInfoBean infoBean) {
        ContentValues values = new ContentValues();
        values.put("name", infoBean.getName());
        values.put("phone", infoBean.getPhone());
        values.put("city", infoBean.getCity());
        values.put("street", infoBean.getStreet());

        long l = sDatabase.insert("receiver_info", null, values);
        return l;
    }

    public static int updateById(ReceiverInfoBean infoBean) {
        ContentValues values = new ContentValues();
        values.put("name", infoBean.getName());
        values.put("phone", infoBean.getPhone());
        values.put("city", infoBean.getCity());
        values.put("street", infoBean.getStreet());

        int id = sDatabase.update("receiver_info", values, "id=" + infoBean.getId(), null);
        return id;
    }

    public static int deleteById(int id) {
        int i = sDatabase.delete("receiver_info", "id=" + id, null);
        return i;
    }

    public static ReceiverInfoBean queryByID(int defaultId) {
        Cursor cursor = sDatabase.query("receiver_info", null, "id=" + defaultId, null, null, null, null);
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            String city = cursor.getString(cursor.getColumnIndex("city"));
            String street = cursor.getString(cursor.getColumnIndex("street"));
            return new ReceiverInfoBean(id, name, phone, city, street);
        }

        return null;
    }
}
