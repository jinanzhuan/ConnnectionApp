package com.guigu.test.connnectionapp.controller.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.guigu.test.connnectionapp.R;
import com.guigu.test.connnectionapp.model.Model;
import com.guigu.test.connnectionapp.model.bean.UserInfo;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

public class AddContactActivity extends Activity {
    private TextView tv_contact_add_search;
    private EditText et_coantact_add_searchName;
    private TextView tv_contact_add_name;
    private Button btn_coantact_add_add;
    private RelativeLayout rl_contact_add;
    private String searchName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        //初始化控件
        initView();

        //设置监听
        initListener();

    }

    private void initListener() {
        //为查找项添加监听
        tv_contact_add_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchName = et_coantact_add_searchName.getText().toString();
                if(TextUtils.isEmpty(searchName)){
                    Toast.makeText(AddContactActivity.this, "请输入想要查找的联系人", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    //如果输入项不为空，则得到用户对象，并将添加内容项添加用户名
                    UserInfo userInfo=new UserInfo(searchName);
                    tv_contact_add_name.setText(userInfo.getName());
                    //设置添加项为显示状态
                    rl_contact_add.setVisibility(View.VISIBLE);
                }

            }
        });

        //为添加按钮添加监听
        btn_coantact_add_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //联网发出添加好友信息
                            EMClient.getInstance().contactManager().addContact(searchName, "请求添加好友");
                            //提示添加好友成功
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(AddContactActivity.this, "添加好友信息发送成功", Toast.LENGTH_SHORT).show();
                                }
                            });


                        } catch (final HyphenateException e) {
                            e.printStackTrace();
                            //提示添加好友失败
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(AddContactActivity.this, "添加好友信息发送失败"+e, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    private void initView() {
        tv_contact_add_search = (TextView)findViewById(R.id.tv_contact_add_search);
        et_coantact_add_searchName = (EditText)findViewById(R.id.et_coantact_add_searchName);
        tv_contact_add_name = (TextView)findViewById(R.id.tv_contact_add_name);
        btn_coantact_add_add = (Button)findViewById(R.id.btn_coantact_add_add);
        rl_contact_add = (RelativeLayout)findViewById(R.id.rl_contact_add);

        //隐藏添加列表
        rl_contact_add.setVisibility(View.GONE);

    }
}
