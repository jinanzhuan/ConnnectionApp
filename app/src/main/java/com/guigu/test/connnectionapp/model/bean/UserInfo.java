package com.guigu.test.connnectionapp.model.bean;

import java.io.Serializable;

/**
 * Created by shuwei on 2016/7/7.
 */
public class UserInfo implements Serializable {
    private String name;
    private String nick;
    private String hxId;
    private String icon;

    public UserInfo() {
    }

    public UserInfo(String name) {
        this.name = name;
        this.nick = name;
        this.hxId = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getHxId() {
        return hxId;
    }

    public void setHxId(String hxId) {
        this.hxId = hxId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", nick='" + nick + '\'' +
                ", hxId='" + hxId + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
