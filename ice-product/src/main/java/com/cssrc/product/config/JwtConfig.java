package com.cssrc.product.config;

import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;


public class JwtConfig {

    @Value("${jwt.token-header}")
    private String tokenHeader;

    public String getTokenHeader() {
        return tokenHeader;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }

    public String getToken(HttpServletRequest request){
        return request.getHeader(this.getTokenHeader());
    }

}
