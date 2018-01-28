package com.cssrc.zuul.service;

import com.cssrc.common.util.jwt.IJwtInfo;
import com.cssrc.zuul.config.JwtConfig;
import com.cssrc.zuul.service.IUserPrincipal;
import com.cssrc.zuul.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;



public class UserPrincipal implements IUserPrincipal {


    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private JwtConfig jwtConfig;

    @Override
    public String getName(HttpServletRequest request) {
        IJwtInfo infoFromToken = getJwtInfo(request);
        return infoFromToken == null ? null : infoFromToken.getUniqueName();
    }

    private IJwtInfo getJwtInfo(HttpServletRequest request) {
        IJwtInfo infoFromToken = null;
        try {
            String authToken = request.getHeader(jwtConfig.getTokenHeader());
            if(StringUtils.isEmpty(authToken)) {
                infoFromToken = null;
            } else {
                infoFromToken = jwtUtil.getInfoFromToken(authToken);
            }
        } catch (Exception e) {
            infoFromToken = null;
        }
        return infoFromToken;
    }

}
