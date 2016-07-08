package com.guigu.test.connnectionapp.controller.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.guigu.test.connnectionapp.R;
import com.guigu.test.connnectionapp.model.Model;
import com.guigu.test.connnectionapp.model.bean.UserInfo;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

public class LoginActivity extends AppCompatActivity {
    private EditText et_login_user;
    private EditText et_login_pwd;
    private Button btn_login_regist;
    private Button btn_login_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //初始化控件
        initView();
        //给button设置监听
        initListener();
    }

    //给button设置监听
    private void initListener() {
        //注册按钮监听
        btn_login_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到注册界面，然后带结果回调
                Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivityForResult(intent, 1);
            }
        });


        //登录按钮监听
        btn_login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取用户信息
                final String loginName = et_login_user.getText().toString();
                final String loginPwd = et_login_pwd.getText().toString();
                //设置进度对话框
                final ProgressDialog pd=new ProgressDialog(LoginActivity.this);
                pd.setMessage("正在登录");
                pd.setCancelable(false);
                pd.show();
                //联网访问
                Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        EMClient.getInstance().login(loginName, loginPwd, new EMCallBack() {
                            @Override
                            public void onSuccess() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Log.e("TAG", "保存到数据库前");
                                        //保存到数据库中
                                        Model.getInstance().getUserAccountDAO().addUserInfo(new UserInfo(loginName));
                                        Log.e("TAG", "保存到数据库后");
                                        //提示
                                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                        //进度对话框取消
                                        pd.cancel();
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                });

                            }

                            @Override
                            public void onError(int i, final String s) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //提示
                                        Toast.makeText(LoginActivity.this, "登录失败" + s, Toast.LENGTH_SHORT).show();
                                        //进度对话框取消
                                        pd.cancel();
                                    }
                                });

                            }

                            @Override
                            public void onProgress(int i, String s) {

                            }
                        });
                    }
                });
            }
        });
    }

    //注册成功后，将注册信息显示在登录页面
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==100){
            String registName = data.getStringExtra("name");
            String registPwd = data.getStringExtra("pwd");
            //设置用户名及密码
            et_login_user.setText(registName);
            et_login_pwd.setText(registPwd);
        }

    }

    //初始化控件
    private void initView() {
        et_login_user = (EditText)findViewById(R.id.et_login_user);
        et_login_pwd = (EditText)findViewById(R.id.et_login_pwd);
        btn_login_regist = (Button)findViewById(R.id.btn_login_regist);
        btn_login_login = (Button)findViewById(R.id.btn_login_login);
    }
}
