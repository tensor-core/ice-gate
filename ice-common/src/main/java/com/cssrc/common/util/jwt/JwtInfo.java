package com.cssrc.common.util.jwt;

import java.io.Serializable;
import java.util.Objects;

public class JwtInfo implements Serializable,IJwtInfo {
    private String username;
    private String userId;
    private String name;

    public JwtInfo(String username, String userId, String name) {
        this.username = username;
        this.userId = userId;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JwtInfo jwtInfo = (JwtInfo) o;
        return Objects.equals(username, jwtInfo.username) &&
                Objects.equals(userId, jwtInfo.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(username, userId);
    }

    public String getUsername() {
        return username;

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String getUniqueName() {
        return username;
    }

    @Override
    public String getId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
