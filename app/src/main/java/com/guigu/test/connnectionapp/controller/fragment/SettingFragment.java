package com.guigu.test.connnectionapp.controller.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.guigu.test.connnectionapp.R;
import com.guigu.test.connnectionapp.controller.activity.LoginActivity;
import com.guigu.test.connnectionapp.model.Model;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

/**
 * Created by shuwei on 2016/7/7.
 */
public class SettingFragment extends Fragment {
    private Button btn_setting_out;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_setting, null);
        btn_setting_out= (Button) view.findViewById(R.id.btn_setting_out);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //更新button显示
        btn_setting_out.setText("退出登录("+EMClient.getInstance().getCurrentUser()+")");
        //为退出button设置监听
        btn_setting_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //联网退出
                Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        EMClient.getInstance().logout(false, new EMCallBack() {
                            @Override
                            public void onSuccess() {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //提示
                                        Toast.makeText(getActivity(), "退出成功", Toast.LENGTH_SHORT).show();
                                        //跳转到登录界面
                                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                                        startActivity(intent);
                                        //销毁当前activity
                                        getActivity().finish();
                                    }
                                });
                            }

                            @Override
                            public void onError(int i, String s) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getActivity(), "退出失败", Toast.LENGTH_SHORT).show();
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
}
