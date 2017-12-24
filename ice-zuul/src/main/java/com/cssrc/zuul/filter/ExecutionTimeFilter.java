package com.cssrc.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component
@Slf4j
public class ExecutionTimeFilter extends ZuulFilter {
    //private static final Logger LOGGER = LoggerFactory.getLogger(ExecutionTimeFilter.class);

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER + 100;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        long startTime = (Long) ctx.get("startAccessTime");
        long duration = System.currentTimeMillis() - startTime;
        //LOGGER.info("{} ==> {} duration:{}ms", request.getMethod(), request.getRequestURL().toString(), duration);
        log.info("{} ==> {} duration:{}ms", request.getMethod(), request.getRequestURL().toString(), duration);
        return null;
    }
}
