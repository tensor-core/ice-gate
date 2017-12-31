package com.cssrc.generator.rest;

import com.cssrc.common.rest.BaseController;
import com.cssrc.generator.biz.BaseUserBiz;
import com.cssrc.generator.entity.BaseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRest  extends BaseController<BaseUserBiz,BaseUser> {


}
