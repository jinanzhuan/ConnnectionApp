package com.guigu.test.connnectionapp.controller.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.guigu.test.connnectionapp.R;
import com.guigu.test.connnectionapp.controller.fragment.ContactFragment;
import com.guigu.test.connnectionapp.controller.fragment.ConversationFragment;
import com.guigu.test.connnectionapp.controller.fragment.SettingFragment;

public class MainActivity extends FragmentActivity {
    private FrameLayout fl_main;
    private RadioButton rb_main_conversation;
    private RadioButton rb_main_contact;
    private RadioButton rb_main_setting;
    private RadioGroup rg_mian;
    private ConversationFragment mCoversationFragment;
    private ContactFragment mContactFragment;
    private SettingFragment mSettingFragment;
    private int mCheckedId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        initView();

        //初始化Fragment
        initFragment();

        //设置监听
        initListener();
    }

    //初始化Fragment
    private void initFragment() {
        mCoversationFragment = new ConversationFragment();
        mContactFragment = new ContactFragment();
        mSettingFragment = new SettingFragment();
    }

    private void initListener() {
        //为radioGroup设置监听
        rg_mian.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(mCheckedId!=checkedId){
                    mCheckedId=checkedId;
                    Fragment fragment=null;
                    switch (checkedId) {
                        case R.id.rb_main_conversation :
                            fragment=mCoversationFragment;
                            break;
                        case R.id.rb_main_contact:
                            fragment=mContactFragment;
                            break;
                        case R.id.rb_main_setting:
                            fragment=mSettingFragment;
                            break;
                        default:
                            fragment=mCoversationFragment;
                            break;
                    }
                    //加载Fragment到framelayout
                    switchFragment(fragment);
                }
            }
        });
        //设置默认页面
        mCheckedId=R.id.rb_main_conversation;
        rg_mian.check(mCheckedId);
        //切换到默认页面
        switchFragment(mCoversationFragment);

    }

    private void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_main,fragment);
        transaction.commit();
    }

    private void initView() {
        fl_main = (FrameLayout)findViewById(R.id.fl_main);
        rb_main_conversation = (RadioButton)findViewById(R.id.rb_main_conversation);
        rb_main_contact = (RadioButton)findViewById(R.id.rb_main_contact);
        rb_main_setting = (RadioButton)findViewById(R.id.rb_main_setting);
        rg_mian = (RadioGroup)findViewById(R.id.rg_mian);
    }
}
