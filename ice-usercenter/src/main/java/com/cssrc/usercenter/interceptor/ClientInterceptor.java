package com.cssrc.usercenter.interceptor;

import com.cssrc.common.exception.auth.ClientForbiddenException;
import com.cssrc.common.msg.BaseResponse;
import com.cssrc.common.msg.ObjectRestResponse;
import com.cssrc.common.util.jwt.IJwtInfo;
import com.cssrc.usercenter.annotation.IgnoreClientToken;
import com.cssrc.usercenter.config.ClientConfig;
import com.cssrc.usercenter.rpc.ClientAuthRpc;
import com.cssrc.usercenter.util.ClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class ClientInterceptor extends HandlerInterceptorAdapter{

    @Value("${client.id}")
    private String clientId;
    @Value("${client.secret}")
    private String secret;

    @Autowired
    private ClientConfig clientConfig;
    @Autowired
    private ClientUtil clientUtil;
    @Autowired
    private ClientAuthRpc clientAuthRpc;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 配置该注解，说明不进行服务拦截
        IgnoreClientToken annotation = handlerMethod.getBeanType().getAnnotation(IgnoreClientToken.class);
        if (annotation == null) {
            annotation = handlerMethod.getMethodAnnotation(IgnoreClientToken.class);
        }
        if(annotation!=null) {
            return super.preHandle(request, response, handler);
        }

        String token = request.getHeader(clientConfig.getTokenHeader());
        IJwtInfo infoFromToken = clientUtil.getInfoFromToken(token);
        String uniqueName = infoFromToken.getUniqueName();
        BaseResponse resp = clientAuthRpc.getAllowedClient(clientId,secret);
        if(resp.getStatus()==200) {
            ObjectRestResponse<List<String>> allowedClient = (ObjectRestResponse<List<String>>) resp;
            for (String client : allowedClient.getData()) {
                if(client.equals(uniqueName)){
                    return super.preHandle(request, response, handler);
                }
            }
        }
       throw new ClientForbiddenException("Client is forbidden");
    }
}
