package com.cssrc.uc.rest;

import com.cssrc.common.bean.UserInfo;
import com.cssrc.common.context.BaseContextHandler;
import com.cssrc.common.rest.BaseController;
import com.cssrc.common.vo.PermissionInfo;
import com.cssrc.uc.bean.FrontUser;
import com.cssrc.uc.bean.MenuTree;
import com.cssrc.uc.biz.BaseUserBiz;
import com.cssrc.uc.entity.BaseUser;
import com.cssrc.uc.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRest  extends BaseController<BaseUserBiz,BaseUser> {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/who")
    public String getCurrentUser() {
        return BaseContextHandler.getUsername();
    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public @ResponseBody
    UserInfo validate(String username, String password){
        return permissionService.validate(username,password);
    }


    @RequestMapping(value = "/permissions", method = RequestMethod.GET)
    public @ResponseBody
    List<PermissionInfo> getAllPermission(){
        return permissionService.getAllPermission();
    }

    @RequestMapping(value = "/user/un/{username}/permissions", method = RequestMethod.GET)
    public @ResponseBody List<PermissionInfo> getPermissionByUsername(@PathVariable("username") String username){
        return permissionService.getPermissionByUsername(username);
    }

    @RequestMapping(value = "/front/info", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUserInfo(String token) throws Exception {
        FrontUser userInfo = permissionService.getUserInfo(token);
        if(userInfo==null) {
            return ResponseEntity.status(401).body(false);
        } else {
            return ResponseEntity.ok(userInfo);
        }
    }

    @RequestMapping(value = "/front/menus", method = RequestMethod.GET)
    public @ResponseBody
    List<MenuTree> getMenusByUsername(String token) throws Exception {
        return permissionService.getMenusByUsername(token);
    }

}
