package com.guigu.test.connnectionapp.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.guigu.test.connnectionapp.model.dao.UserAccountTable;

/**
 * Created by shuwei on 2016/7/7.
 */
public class UserAccountDB extends SQLiteOpenHelper {
    private static final int DB_VERSION=1;

    public UserAccountDB(Context context) {
        super(context, "account2.db", null, DB_VERSION);
        Log.e("TAG", "UserAccountDB构造器");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("TAG", "创建表格");
        db.execSQL(UserAccountTable.CREAT_TAB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
