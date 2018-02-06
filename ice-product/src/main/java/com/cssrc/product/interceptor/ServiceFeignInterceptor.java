package com.cssrc.product.interceptor;

import com.cssrc.common.context.BaseContextHandler;
import com.cssrc.product.config.ClientConfig;
import com.cssrc.product.config.JwtConfig;
import com.cssrc.product.util.ClientUtil;
import com.cssrc.product.util.JwtUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class ServiceFeignInterceptor implements RequestInterceptor {
    private Logger logger = LoggerFactory.getLogger(ServiceFeignInterceptor.class);
    @Autowired
    private ClientConfig clientConfig;
    @Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private ClientUtil clientUtil;
    @Autowired
    private JwtUtil jwtUtil;

    public ServiceFeignInterceptor() {
    }


    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(clientConfig.getTokenHeader(), clientUtil.getClientToken());
        requestTemplate.header(jwtConfig.getTokenHeader(), BaseContextHandler.getToken());
    }

    public void setClientConfig(ClientConfig clientConfig) {
        this.clientConfig = clientConfig;
    }

    public void setJwtConfig(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public void setClientUtil(ClientUtil clientUtil) {
        this.clientUtil = clientUtil;
    }

    public void setJwtUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
}