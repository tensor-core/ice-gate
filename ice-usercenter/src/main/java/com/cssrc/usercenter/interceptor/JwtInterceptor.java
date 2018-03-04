package com.cssrc.usercenter.interceptor;

import com.cssrc.common.context.BaseContextHandler;
import com.cssrc.common.util.jwt.IJwtInfo;
import com.cssrc.common.util.jwt.JwtHelper;
import com.cssrc.usercenter.annotation.IgnoreUserToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Value("${jwt.token-header}")
    private String tokenHeader;
    @Value("${jwt.pub-key.path}")
    private String pubKeyPath;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 配置该注解，说明不进行用户拦截
        IgnoreUserToken annotation = handlerMethod.getBeanType().getAnnotation(IgnoreUserToken.class);
        if (annotation == null) {
            annotation = handlerMethod.getMethodAnnotation(IgnoreUserToken.class);
        }
        if(annotation!=null) {
            return super.preHandle(request, response, handler);
        }
        String token = request.getHeader(tokenHeader);
        IJwtInfo infoFromToken = JwtHelper.getInfoFromToken(token,pubKeyPath);
        BaseContextHandler.setUsername(infoFromToken.getUniqueName());
        BaseContextHandler.setUserID(infoFromToken.getId());
        BaseContextHandler.setName(infoFromToken.getName());
        return super.preHandle(request, response, handler);
    }





}
