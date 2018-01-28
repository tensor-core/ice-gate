package com.cssrc.auth.service;

import com.cssrc.common.bean.ClientInfo;

import java.util.List;

public interface ClientService {
    ClientInfo apply(String clientId, String secret);

    /**
     * 获取授权的客户端列表
     * @param serviceId
     * @param secret
     * @return
     */
    public List<String> getAllowClient(String serviceId,String secret);
}
