package com.cssrc.auth.util;


import com.cssrc.common.util.jwt.IJwtInfo;
import com.cssrc.common.util.jwt.JwtHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    @Value("${jwt.expire}")
    private int expire;
    @Value("${jwt.pri-key.path}")
    private String priKeyPath;
    @Value("${jwt.pub-key.path}")
    private String pubKeyPath;
    public String generateToken(IJwtInfo jwtInfo) throws Exception{
        return JwtHelper.generateToken(jwtInfo,priKeyPath,expire);
    }


    public  IJwtInfo getInfoFromToken(String token) throws Exception{
        return JwtHelper.getInfoFromToken(token,pubKeyPath);
    }









}
