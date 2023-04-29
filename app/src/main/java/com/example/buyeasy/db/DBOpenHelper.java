package com.example.buyeasy.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * @PackageName: com.example.buyeasy.db
 * @ClassName: DBOpenHelper
 * @Author: winwa
 * @Date: 2023/4/28 9:15
 * @Description:
 **/
public class DBOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "buy_easy";
    private static final int DB_VERSION = 1;

    public DBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table receiver_info(id integer primary key autoincrement, " +
                "name varchar(32) not null, phone varchar(20) not null," +
                "city varchar(64), street varchar(128))";
        sqLiteDatabase.execSQL(sql);

        sqLiteDatabase.execSQL("insert into receiver_info values (1, 'aa1', '1111', 'bb1', 'cc1')");
        sqLiteDatabase.execSQL("insert into receiver_info values (2, 'aa2', '2222', 'bb2', 'cc2')");
        sqLiteDatabase.execSQL("insert into receiver_info values (3, 'aa3', '3333', 'bb3', 'cc3')");
        sqLiteDatabase.execSQL("insert into receiver_info values (4, 'aa4', '4444', 'bb4', 'cc4')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
