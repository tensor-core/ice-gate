package com.cssrc.auth.rest;


import com.cssrc.auth.service.UserService;
import com.cssrc.auth.util.JwtAuthenticationRequest;
import com.cssrc.auth.util.JwtAuthenticationResponse;
import com.cssrc.auth.util.JwtUtil;
import com.cssrc.common.msg.ObjectRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("jwt")
public class JwtRest {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;
    /**
     * 登陆验证
     * @param authenticationRequest
     * @return
     */
    @RequestMapping(value = "/token",method = RequestMethod.POST)
    public ResponseEntity<?> authorize(@RequestBody JwtAuthenticationRequest authenticationRequest) throws Exception {
        //1、检验账户合法性
        final String token = userService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

}
