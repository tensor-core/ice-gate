package com.cssrc.auth.service.impl;

import com.cssrc.auth.entity.Client;
import com.cssrc.auth.mapper.ClientMapper;
import com.cssrc.auth.service.ClientService;
import com.cssrc.common.bean.ClientInfo;
import com.cssrc.common.exception.auth.ClientInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBClientService implements ClientService{

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public List<String> getAllowClient(String serviceId, String secret) {
        ClientInfo info = this.apply(serviceId,secret);
        //该服务能被哪些服务调用
        return clientMapper.selectAllowClient(info.getId());
    }

    /**
     * 派发token
     * @param clientId
     * @param secret
     * @return
     */
    @Override
    public ClientInfo apply(String clientId, String secret) {
        Client client = new Client();
        client.setCode(clientId);
        client = clientMapper.selectOne(client);
        if(client==null||!client.getSecret().equals(secret)){
            throw new ClientInvalidException("Client not found or Client secret is error");
        }
        return new ClientInfo(client.getCode(),client.getName(),client.getId().toString());
    }
}
