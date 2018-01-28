package com.cssrc.zuul.filter;


import com.alibaba.fastjson.JSON;
import com.cssrc.common.constant.CommonConstants;
import com.cssrc.common.context.BaseContextHandler;
import com.cssrc.common.exception.auth.ClientInvalidException;
import com.cssrc.common.msg.BaseResponse;
import com.cssrc.common.msg.ObjectRestResponse;
import com.cssrc.common.util.jwt.IJwtInfo;
import com.cssrc.common.vo.PermissionInfo;

import com.cssrc.zuul.config.ClientConfig;
import com.cssrc.zuul.config.JwtConfig;
import com.cssrc.zuul.rpc.ClientAuthRpc;
import com.cssrc.zuul.rpc.IUserService;
import com.cssrc.zuul.util.ClientUtil;
import com.cssrc.zuul.util.JwtUtil;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Component
@Slf4j
public class AcessFilter extends ZuulFilter {

    @Value("${gate.ignore.startWith}")
    private String startWith;

    @Value("${zuul.prefix}")
    private String zuulPrefix;


    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ClientConfig clientConfig;

    @Autowired
    private ClientUtil clientUtil;

    @Autowired
    private ClientAuthRpc clientAuthRpc;

    @Autowired
    private EurekaClient discoveryClient;


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        final String requestUri = request.getRequestURI().substring(zuulPrefix.length());
        final String method = request.getMethod();
        BaseContextHandler.setToken(null);
        // 不进行拦截的地址
        if (isStartWith(requestUri)) {
            return null;
        }
        IJwtInfo user = null;
        try {
            user = getJWTUser(request, ctx);
        } catch (Exception e) {
            ctx.setResponseBody(JSON.toJSONString(new BaseResponse(CommonConstants.EX_TOKEN_ERROR_CODE,"Token error or Token is expired!")));
            return null;
        }
        //List<PermissionInfo> permissionIfs = userService.getAllPermissionInfo();
        // 判断资源是否启用权限约束
        //Stream<PermissionInfo> stream = getPermissionIfs(requestUri, method, permissionIfs);
        //List<PermissionInfo> result = stream.collect(Collectors.toList());
        //PermissionInfo[] permissions = result.toArray(new PermissionInfo[]{});
        //if (permissions.length > 0) {
        //   checkUserPermission(permissions, ctx, user);
        //}
        // 申请客户端密钥头

        //申请客户端密钥头
        BaseResponse resp = clientAuthRpc.getAccessToken(clientUtil.getClientId(),clientUtil.getSecret());
        if(resp.getStatus()==200){
            ObjectRestResponse<String> clientToken = (ObjectRestResponse<String>)resp;
            ctx.addZuulRequestHeader(clientConfig.getTokenHeader(),clientToken.getData());
        }
        else {
            throw new ClientInvalidException("gate client secret is error");
        }
        BaseContextHandler.remove();
        return null;
    }

    /**
     * 获取目标权限资源
     *
     * @param requestUri
     * @param method
     * @param serviceInfo
     * @return
     */
    private Stream<PermissionInfo> getPermissionIfs(final String requestUri, final String method, List<PermissionInfo> serviceInfo) {
        return serviceInfo.parallelStream().filter(new Predicate<PermissionInfo>() {
            @Override
            public boolean test(PermissionInfo permissionInfo) {
                String url = permissionInfo.getUri();
                String uri = url.replaceAll("\\{\\*\\}", "[a-zA-Z\\\\d]+");
                String regEx = "^" + uri + "$";
                return (Pattern.compile(regEx).matcher(requestUri).find() || requestUri.startsWith(url + "/"))
                        && method.equals(permissionInfo.getMethod());
            }
        });
    }


    /**
     * 返回session中的用户信息
     *
     * @param request
     * @param ctx
     * @return
     */
    private IJwtInfo getJWTUser(HttpServletRequest request, RequestContext ctx) throws Exception {
        String authToken = request.getHeader(jwtConfig.getTokenHeader());
        if (StringUtils.isBlank(authToken)) {
            authToken = request.getParameter("token");
        }
        ctx.addZuulRequestHeader(jwtConfig.getTokenHeader(), authToken);
        BaseContextHandler.setToken(authToken);
        return jwtUtil.getInfoFromToken(authToken);
    }

    /**
     * URI是否以什么打头
     *
     * @param requestUri
     * @return
     */
    private boolean isStartWith(String requestUri) {
        boolean flag = false;
        for (String s : startWith.split(",")) {
            if (requestUri.startsWith(s)) {
                return true;
            }
        }
        return flag;
    }

    /**
     * 网关抛异常
     *
     * @param body
     * @param code
     */
    private void setFailedRequest(String body, int code) {
        log.debug("Reporting error ({}): {}", code, body);
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setResponseStatusCode(code);
        if (ctx.getResponseBody() == null) {
            ctx.setResponseBody(body);
            ctx.setSendZuulResponse(false);
        }
    }
}
