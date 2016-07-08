package com.guigu.test.connnectionapp.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.guigu.test.connnectionapp.R;
import com.hyphenate.chat.EMClient;

public class SplashActivity extends Activity {
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){

            jumpTOLoginOrMain();
        }
    };

    private void jumpTOLoginOrMain() {
        //如果登陆过，则直接跳到主界面
        if(EMClient.getInstance().isLoggedInBefore()){
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }else{//如果没有登陆过，则跳到注册界面
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        //关闭当前页面
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler.sendMessageDelayed(Message.obtain(), 2000);

    }
}
