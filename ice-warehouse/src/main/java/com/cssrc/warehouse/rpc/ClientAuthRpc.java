package com.cssrc.warehouse.rpc;

import com.cssrc.common.msg.ObjectRestResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "${jwt.serviceId}",configuration = {})
public interface ClientAuthRpc {
    @RequestMapping(value = "/client/myClient",method = RequestMethod.POST)
    public ObjectRestResponse<List<String>> getAllowedClient(@RequestParam("serviceId") String serviceId, @RequestParam("secret") String secret);

    @RequestMapping(value = "/client/token",method = RequestMethod.POST)
    public ObjectRestResponse<String> getAccessToken(@RequestParam("clientId") String clientId, @RequestParam("secret") String secret);
}
