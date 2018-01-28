package com.cssrc.uc.util;

import com.cssrc.common.exception.auth.JwtIllegalArgumentException;
import com.cssrc.common.exception.auth.JwtSignatureException;
import com.cssrc.common.exception.auth.JwtTokenExpiredException;
import com.cssrc.common.msg.BaseResponse;
import com.cssrc.common.msg.ObjectRestResponse;
import com.cssrc.common.util.jwt.IJwtInfo;
import com.cssrc.common.util.jwt.JwtHelper;
import com.cssrc.uc.config.ClientConfig;
import com.cssrc.uc.event.AuthRemoteEvent;
import com.cssrc.uc.interceptor.ServiceFeignInterceptor;
import com.cssrc.uc.rpc.ClientAuthRpc;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
public class ClientUtil implements ApplicationListener<AuthRemoteEvent> {
    private Logger log = LoggerFactory.getLogger(ClientUtil.class);
    @Value("${client.id}")
    private String clientId;
    @Value("${client.secret}")
    private String secret;
    @Value("${client.pub-key.path}")
    private String pubKeyPath;

    @Autowired
    private ClientConfig clientConfig;

    @Autowired
    private ClientAuthRpc clientAuthRpc;
    private String clientToken;
    private List<String> allowedClient;
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

    @Scheduled(cron = "0/30 * * * * ?")
    public void refreshAllowedClient() {
        log.debug("refresh allowedClient.....");
        BaseResponse resp = clientAuthRpc.getAllowedClient(clientConfig.getClientId(), clientConfig.getClientSecret());
        if (resp.getStatus() == 200) {
            ObjectRestResponse<List<String>> allowedClient = (ObjectRestResponse<List<String>>) resp;
            this.allowedClient = allowedClient.getData();
        }
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    public void refreshClientToken() {
        log.debug("refresh client token.....");
        BaseResponse resp = clientAuthRpc.getAccessToken(clientConfig.getClientId(), clientConfig.getClientSecret());
        if (resp.getStatus() == 200) {
            ObjectRestResponse<String> clientToken = (ObjectRestResponse<String>) resp;
            this.clientToken = clientToken.getData();
        }
    }


    public String getClientToken() {
        if (this.clientToken == null) {
            this.refreshClientToken();
        }
        return clientToken;
    }

    public List<String> getAllowedClient() {
        if (this.allowedClient == null) {
            this.refreshAllowedClient();
        }
        return allowedClient;
    }

    @Override
    public void onApplicationEvent(AuthRemoteEvent authRemoteEvent) {
        //this.allowedClient = authRemoteEvent.getAllowedClient();
    }
}
