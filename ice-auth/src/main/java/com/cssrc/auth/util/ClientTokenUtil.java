package com.cssrc.auth.util;

import com.cssrc.common.util.jwt.IJwtInfo;
import com.cssrc.common.util.jwt.JwtHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ace on 2017/9/10.
 */
@Configuration
public class ClientTokenUtil {
    private Logger logger = LoggerFactory.getLogger(ClientTokenUtil.class);

    @Value("${client.expire}")
    private int expire;
    @Value("${client.pri-key.path}")
    private String priKeyPath;
    @Value("${client.pub-key.path}")
    private String pubKeyPath;
    public String generateToken(IJwtInfo jwtInfo) throws Exception{
        return JwtHelper.generateToken(jwtInfo,priKeyPath,expire);
    }


    public  IJwtInfo getInfoFromToken(String token) throws Exception{
        return JwtHelper.getInfoFromToken(token,pubKeyPath);
    }

}
