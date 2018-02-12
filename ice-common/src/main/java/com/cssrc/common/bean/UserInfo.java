package com.cssrc.common.bean;

import com.cssrc.common.constant.ChatConstants;
import com.cssrc.common.util.jwt.IJwtInfo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserInfo implements IJwtInfo{
    String username;
    String userId;
    String name;
    String password;
    String code;
    String headImg;
    Long id;

    public static Map<String, UserInfo> map = new ConcurrentHashMap<>();

    public UserInfo() {
    }

    public UserInfo(String username, String userId, String name, String password) {
        this.username = username;
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.headImg = ChatConstants.headImg();
        this.code = ChatConstants.code();
        this.id = System.currentTimeMillis();
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
