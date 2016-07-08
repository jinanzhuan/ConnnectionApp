package com.guigu.test.connnectionapp.model;

import android.content.Context;

import com.guigu.test.connnectionapp.model.dao.UserAccountDAO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by shuwei on 2016/7/7.
 */
public class Model {
    private UserAccountDAO mUserAccountDAO;
    private Context mContext;

    //创建一个全局缓冲线程池
    private ExecutorService executorService= Executors.newCachedThreadPool();

    //获得单例对象
    private static Model instance=new Model();
    public static Model getInstance(){

        return instance;
    }

    //初始化数据库操作类，具体是在IMApplication中进行
    public void init(Context context){
        mContext=context;
        mUserAccountDAO=new UserAccountDAO(mContext);
    }

    //得到数据库操作类对象
    public UserAccountDAO getUserAccountDAO(){
        return mUserAccountDAO;
    }

    //得到全局线程池，防止多个线程同时启动，导致系统崩溃
    public ExecutorService getGlobalThreadPool(){
        return executorService;
    }
}
