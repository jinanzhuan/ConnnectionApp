package com.guigu.test.connnectionapp;

import android.app.Application;

import com.guigu.test.connnectionapp.model.Model;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.controller.EaseUI;

/**
 * Created by shuwei on 2016/7/7.
 */
public class IMApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化环信EaseUI参数
        EMOptions options = new EMOptions();
        options.setAutoAcceptGroupInvitation(false);
        options.setAcceptInvitationAlways(false);

        EaseUI.getInstance().init(this, options);

        //通过Model初始化数据库
        Model.getInstance().init(this);
    }
}
