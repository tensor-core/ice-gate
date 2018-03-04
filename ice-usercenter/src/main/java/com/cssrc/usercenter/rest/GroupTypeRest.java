package com.cssrc.usercenter.rest;

import com.cssrc.common.rest.BaseController;
import com.cssrc.usercenter.biz.GroupTypeBiz;
import com.cssrc.usercenter.entity.GroupType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("groupType")
public class GroupTypeRest extends BaseController<GroupTypeBiz,GroupType> {
//
//    @RequestMapping(value = "/page",method = RequestMethod.GET)
//    @ResponseBody
//    public TableResultResponse<Object> page(@RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "1")int page, String name){
//        Example example = new Example(GroupType.class);
//        if(StringUtils.isNotBlank(name))
//            example.createCriteria().andLike("name", "%" + name + "%");
//        Page<Object> result = PageHelper.startPage(page, limit);
//        baseBiz.selectByExample(example);
//        return new TableResultResponse<Object>(result.getTotal(),result.getResult());
//    }

}
