package com.cssrc.auth.rest;

import com.cssrc.auth.service.ClientService;
import com.cssrc.auth.util.ClientTokenUtil;
import com.cssrc.common.bean.ClientInfo;
import com.cssrc.common.msg.ObjectRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("client")
public class ClientRest {
    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientTokenUtil clientTokenUtil;

    @RequestMapping(value = "/token",method = RequestMethod.POST)
    public ObjectRestResponse getAccessToken(String clientId, String secret) throws Exception{
       ClientInfo apply = clientService.apply(clientId,secret);
       return new ObjectRestResponse<String>().data(clientTokenUtil.generateToken(apply));
    }


    @RequestMapping(value = "/myClient",method = RequestMethod.POST)
    public ObjectRestResponse getAllowClient(String serviceId,String secret){
        return new ObjectRestResponse<List<String>>().data(clientService.getAllowClient(serviceId,secret));
    }


}
