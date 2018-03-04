package com.cssrc.usercenter.rest;

import com.cssrc.common.rest.BaseController;
import com.cssrc.usercenter.biz.GateLogBiz;
import com.cssrc.usercenter.entity.GateLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("gateLog")
public class GateLogRest extends BaseController<GateLogBiz,GateLog> {
//    @RequestMapping(value = "/page",method = RequestMethod.GET)
//    @ResponseBody
//    public TableResultResponse<GateLog> page(@RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "1")int offset, String name){
//        Example example = new Example(GateLog.class);
//        if(StringUtils.isNotBlank(name)) {
//            example.createCriteria().andLike("menu", "%" + name + "%");
//        }
//        int count = baseBiz.selectCountByExample(example);
//        PageHelper.startPage(offset, limit);
//        return new TableResultResponse<GateLog>(count,baseBiz.selectByExample(example));
//    }
}
