package com.cssrc.zuul.util;

import com.cssrc.common.exception.auth.JwtIllegalArgumentException;
import com.cssrc.common.exception.auth.JwtSignatureException;
import com.cssrc.common.exception.auth.JwtTokenExpiredException;
import com.cssrc.common.msg.BaseResponse;
import com.cssrc.common.msg.ObjectRestResponse;
import com.cssrc.common.util.jwt.IJwtInfo;
import com.cssrc.common.util.jwt.JwtHelper;
import com.cssrc.zuul.config.ClientConfig;
import com.cssrc.zuul.rpc.ClientAuthRpc;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientUtil {
    @Value("${client.id}")
    private String clientId;
    @Value("${client.secret}")
    private String secret;
    @Value("${client.pub-key.path}")
    private String pubKeyPath;
    private String clientToken;
    @Autowired
    private ClientAuthRpc clientAuthRpc;
    public IJwtInfo getInfoFromToken(String token) throws Exception {
        try {
            return JwtHelper.getInfoFromToken(token, pubKeyPath);
        } catch (ExpiredJwtException ex) {
            throw new JwtTokenExpiredException("Client token expired!");
        } catch (SignatureException ex) {
            throw new JwtSignatureException("Client token signature error!");
        } catch (IllegalArgumentException ex) {
            throw new JwtIllegalArgumentException("Client token is null or empty!");
        }
    }

    public String getClientToken() {
        BaseResponse resp = clientAuthRpc.getAccessToken(clientId, secret);
        if (resp.getStatus() == 200) {
            ObjectRestResponse<String> clientToken = (ObjectRestResponse<String>) resp;
            this.clientToken = clientToken.getData();
        }
        return clientToken;
    }

    public String getClientId() {
        return clientId;
    }

    public String getSecret() {
        return secret;
    }
}
