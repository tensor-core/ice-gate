package com.cssrc.usercenter.rest;

import com.cssrc.common.vo.LogInfo;
import com.cssrc.usercenter.biz.GateLogBiz;
import com.cssrc.usercenter.entity.GateLog;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RequestMapping("api")
@RestController
public class LogRest {
    @Autowired
    private GateLogBiz gateLogBiz;
    @RequestMapping(value="/log/save",method = RequestMethod.POST)
    public @ResponseBody void saveLog(@RequestBody LogInfo info){
        GateLog log = new GateLog();
        BeanUtils.copyProperties(info,log);
        gateLogBiz.insertSelective(log);
    }
}
