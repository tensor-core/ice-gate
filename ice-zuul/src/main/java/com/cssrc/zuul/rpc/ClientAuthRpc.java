package com.cssrc.zuul.rpc;

import com.cssrc.common.msg.ObjectRestResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("ice-auth")
public interface ClientAuthRpc {
    @RequestMapping(value = "/client/token",method = RequestMethod.POST)
    public ObjectRestResponse<String> getAccessToken(@RequestParam("clientId") String clientId,@RequestParam("secret") String secret);
}
