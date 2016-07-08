package com.guigu.test.connnectionapp.model.dao;

/**
 * Created by shuwei on 2016/7/7.
 */
public class UserAccountTable {
    public static final String TAB_NAME="tab_account";
    public static final String COL_NAME="name";
    public static final String COL_NICK="nick";
    public static final String COL_HXID="hxId";
    public static final String COL_ICON="icon";

    //创建表格语句
    public static final String CREAT_TAB="create table "+
            TAB_NAME+" ( "+
            COL_HXID+" text primary key,"+
            COL_NAME+" text,"+
            COL_NICK+" text,"+
            COL_ICON+" text);";
}
