package com.cssrc.common.bean;

import com.cssrc.common.util.jwt.IJwtInfo;

public class UserInfo implements IJwtInfo{
    String username;
    String userId;
    String name;
    String password;

    public UserInfo() {
    }

    public UserInfo(String username, String userId, String name, String password) {
        this.username = username;
        this.userId = userId;
        this.name = name;
        this.password = password;
    }


    @Override
    public String getUniqueName() {
        return username;
    }

    @Override
    public String getId() {
        return userId;
    }

    @Override
    public String getName() {
        return name;
    }


    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
