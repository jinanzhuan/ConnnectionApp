package com.guigu.test.connnectionapp.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.guigu.test.connnectionapp.model.bean.UserInfo;
import com.guigu.test.connnectionapp.model.db.UserAccountDB;

/**
 * Created by shuwei on 2016/7/7.
 */
//对数据库进行操作（增删改）
public class UserAccountDAO {
    private Context mContext;
    private UserInfo userInfo;
    private final UserAccountDB mHelper;
    public UserAccountDAO(Context context){
        mContext=context;
        mHelper=new UserAccountDB(mContext);//在构造器中初始化
    }



    public void addUserInfo(UserInfo userInfo){
        //通过SQLiteOpenHelper的对象，得到SQLiteDatabase的对象
        SQLiteDatabase db = mHelper.getWritableDatabase();
        Log.e("TAG", "addUserInfo前");
        ContentValues values=new ContentValues();
        values.put(UserAccountTable.COL_NAME,userInfo.getName());
        values.put(UserAccountTable.COL_NICK,userInfo.getNick());
        values.put(UserAccountTable.COL_HXID,userInfo.getHxId());
        values.put(UserAccountTable.COL_ICON,userInfo.getIcon());

        //执行添加语句，本处通过使用replace
        db.replace(UserAccountTable.TAB_NAME,null,values);
    }

    public UserInfo getAccount(String name){
        //通过SQLiteOpenHelper的对象，得到SQLiteDatabase的对象
        SQLiteDatabase db = mHelper.getReadableDatabase();

        String sql="select * from "+UserAccountTable.TAB_NAME+
                " where "+UserAccountTable.COL_NAME+"=?";
        Cursor cursor = db.rawQuery(sql, new String[]{name});
        while(cursor.moveToNext()){
            userInfo=new UserInfo();
            userInfo.setName(cursor.getColumnName(0));
            userInfo.setNick(cursor.getColumnName(1));
            userInfo.setHxId(cursor.getColumnName(2));
            userInfo.setIcon(cursor.getColumnName(3));
        }
        return userInfo;
    }

    public UserInfo getAccountById(String hxId){
        //通过SQLiteOpenHelper的对象，得到SQLiteDatabase的对象
        SQLiteDatabase db = mHelper.getReadableDatabase();

        String sql="select * from "+UserAccountTable.TAB_NAME+
                " where "+UserAccountTable.COL_HXID+"=?";
        Cursor cursor = db.rawQuery(sql, new String[]{hxId});
        while(cursor.moveToNext()){
            userInfo=new UserInfo();
            userInfo.setName(cursor.getColumnName(0));
            userInfo.setNick(cursor.getColumnName(1));
            userInfo.setHxId(cursor.getColumnName(2));
            userInfo.setIcon(cursor.getColumnName(3));
        }
        return userInfo;
    }
}
