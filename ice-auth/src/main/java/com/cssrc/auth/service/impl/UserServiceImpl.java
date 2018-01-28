package com.cssrc.auth.service.impl;


import com.cssrc.auth.rpc.IUserService;
import com.cssrc.auth.service.UserService;
import com.cssrc.auth.util.JwtUtil;
import com.cssrc.common.bean.UserInfo;
import com.cssrc.common.util.jwt.JwtInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private IUserService userService;

    @Autowired
    public UserServiceImpl(
            JwtUtil jwtUtil,
            IUserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    public String login(String username, String password) throws Exception {
        UserInfo info = userService.validate(username,password);
        String token = "";
        //if (!StringUtils.isEmpty(info.getId())) {
            token = jwtUtil.generateToken(new JwtInfo("admin", "1", info.getName()));
        //}
        return token;
    }
}
