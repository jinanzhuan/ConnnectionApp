package com.guigu.test.connnectionapp.controller.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.guigu.test.connnectionapp.R;
import com.guigu.test.connnectionapp.controller.activity.AddContactActivity;
import com.hyphenate.easeui.ui.EaseContactListFragment;

/**
 * Created by shuwei on 2016/7/7.
 */
//继承EaseContactListFragment后，自带listView等
public class ContactFragment extends EaseContactListFragment {
    private LinearLayout ll_contact_friend;
    private LinearLayout ll_contact_group;
    //初始化控件
    @Override
    protected void initView() {
        super.initView();
        //给listView添加表头
        View headerView = View.inflate(getActivity(), R.layout.contact_list_header, null);
        listView.addHeaderView(headerView);

        //初始化控件
        ll_contact_friend = (LinearLayout) headerView.findViewById(R.id.ll_contact_friend);
        ll_contact_group = (LinearLayout) headerView.findViewById(R.id.ll_contact_group);


        //为titileBar添加添加按钮
        titleBar.setRightImageResource(R.drawable.em_add);
    }

    //处理控件的相关动作等
    @Override
    protected void setUpView() {
        super.setUpView();

        //为添加按钮设置监听
        titleBar.getRightLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddContactActivity.class);
                startActivity(intent);
            }
        });
    }
}
