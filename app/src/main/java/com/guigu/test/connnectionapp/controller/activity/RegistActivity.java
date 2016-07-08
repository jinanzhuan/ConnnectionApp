package com.guigu.test.connnectionapp.controller.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.guigu.test.connnectionapp.R;
import com.guigu.test.connnectionapp.model.Model;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

public class RegistActivity extends AppCompatActivity {
    private EditText et_regist_name;
    private EditText et_regist_pwd;
    private EditText et_regist_repwd;
    private Button btn_regist_regist;
    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        //初始化控件对象
        initView();

        //设置监听
        initlistener();
    }

    private void initlistener() {
        btn_regist_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先获得用户名和密码
                final String registName = et_regist_name.getText().toString();
                final String registPwd = et_regist_pwd.getText().toString();
                String registRepwd = et_regist_repwd.getText().toString();
                if(TextUtils.isEmpty(registName)||TextUtils.isEmpty(registPwd)||TextUtils.isEmpty(registRepwd)){
                    Toast.makeText(RegistActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }else if(!registPwd.equals(registRepwd)){
                    Toast.makeText(RegistActivity.this, "两次输入的密码不相同", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    pd=new ProgressDialog(RegistActivity.this);
                    pd.setMessage("正在注册");
                    pd.setCancelable(false);
                    pd.show();
                    //将耗时操作放到全局线程池中
                    Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                //联网创建账号
                                EMClient.getInstance().createAccount(registName,registPwd);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(RegistActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                        //隐藏进度对话框
                                        pd.cancel();
                                    }
                                });
                            } catch (final HyphenateException e) {
                                e.printStackTrace();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(RegistActivity.this, "注册失败:"+e, Toast.LENGTH_SHORT).show();
                                        //隐藏进度对话框
                                        pd.cancel();
                                        //带值回传
                                        Intent intent = getIntent();
                                        intent.putExtra("name",registName);
                                        intent.putExtra("pwd", registPwd);
                                        setResult(100, intent);
                                    }
                                });
                            }
                        }
                    });


                }
            }
        });
    }

    private void initView() {
        et_regist_name = (EditText)findViewById(R.id.et_regist_name);
        et_regist_pwd = (EditText)findViewById(R.id.et_regist_pwd);
        et_regist_repwd = (EditText)findViewById(R.id.et_regist_repwd);
        btn_regist_regist = (Button)findViewById(R.id.btn_regist_regist);
    }
}
