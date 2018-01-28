package com.cssrc.common.util.jwt;

public interface IJwtInfo {
    /**
     * 获取用户名
     * @return
     */
    String getUniqueName();

    /**
     * 获取用户id
     * @return
     */

    String getId();

    /**
     * 获取名称
     * @return
     */
    String getName();
}
