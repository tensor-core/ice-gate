package com.cssrc.usercenter.service;

import com.cssrc.common.bean.UserInfo;
import com.cssrc.common.constant.CommonConstants;
import com.cssrc.common.util.TreeUtil;
import com.cssrc.common.util.jwt.IJwtInfo;
import com.cssrc.common.vo.PermissionInfo;
import com.cssrc.usercenter.bean.FrontUser;
import com.cssrc.usercenter.bean.MenuTree;
import com.cssrc.usercenter.biz.BaseUserBiz;
import com.cssrc.usercenter.biz.ElementBiz;
import com.cssrc.usercenter.biz.MenuBiz;
import com.cssrc.usercenter.constant.AdminCommonConstant;
import com.cssrc.usercenter.entity.BaseUser;
import com.cssrc.usercenter.entity.Element;
import com.cssrc.usercenter.entity.Menu;
import com.cssrc.usercenter.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class PermissionService {
    @Autowired
    private BaseUserBiz userBiz;
    @Autowired
    private MenuBiz menuBiz;
    @Autowired
    private ElementBiz elementBiz;
    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    public UserInfo getUserByUsername(String username) {
        UserInfo info = new UserInfo();
        BaseUser user = userBiz.getUserByUsername(username);
        BeanUtils.copyProperties(user, info);
        info.setUserId(user.getId().toString());
        return info;
    }

    public UserInfo validate(String username,String password){
        UserInfo info = new UserInfo();
        BaseUser user = userBiz.getUserByUsername(username);
        if (encoder.matches(password, user.getPassword())) {
            info = new UserInfo(user.getUsername(),user.getId().toString(),user.getName(),user.getPassword());
        }

        return info;
    }

    public List<PermissionInfo> getAllPermission() {
        List<Menu> menus = menuBiz.selectListAll();
        List<PermissionInfo> result = new ArrayList<PermissionInfo>();
        PermissionInfo info = null;
        menu2permission(menus, result);
        List<Element> elements = elementBiz.selectListAll();
        element2permission(result, elements);
        return result;
    }

    private void menu2permission(List<Menu> menus, List<PermissionInfo> result) {
        PermissionInfo info;
        for (Menu menu : menus) {
            if (StringUtils.isBlank(menu.getHref())) {
                menu.setHref("/" + menu.getCode());
            }
            info = new PermissionInfo();
            info.setCode(menu.getCode());
            info.setType(AdminCommonConstant.RESOURCE_TYPE_MENU);
            info.setName(AdminCommonConstant.RESOURCE_ACTION_VISIT);
            String uri = menu.getHref();
            if (!uri.startsWith("/")) {
                uri = "/" + uri;
            }
            info.setUri(uri);
            info.setMethod(AdminCommonConstant.RESOURCE_REQUEST_METHOD_GET);
            result.add(info
            );
            info.setMenu(menu.getTitle());
        }
    }

    public List<PermissionInfo> getPermissionByUsername(String username) {
        BaseUser user = userBiz.getUserByUsername(username);
        List<Menu> menus = menuBiz.getUserAuthorityMenuByUserId(user.getId());
        List<PermissionInfo> result = new ArrayList<PermissionInfo>();
        PermissionInfo info = null;
        menu2permission(menus, result);
        List<Element> elements = elementBiz.getAuthorityElementByUserId(user.getId() + "");
        element2permission(result, elements);
        return result;
    }

    private void element2permission(List<PermissionInfo> result, List<Element> elements) {
        PermissionInfo info;
        for (Element element : elements) {
            info = new PermissionInfo();
            info.setCode(element.getCode());
            info.setType(element.getType());
            info.setUri(element.getUri());
            info.setMethod(element.getMethod());
            info.setName(element.getName());
            info.setMenu(element.getMenuId());
            result.add(info);
        }
    }


    private List<MenuTree> getMenuTree(List<Menu> menus, int root) {
        List<MenuTree> trees = new ArrayList<MenuTree>();
        MenuTree node = null;
        for (Menu menu : menus) {
            node = new MenuTree();
            BeanUtils.copyProperties(menu, node);
            trees.add(node);
        }
        return TreeUtil.bulid(trees, root);
    }

    public FrontUser getUserInfo(String token) throws Exception {
        IJwtInfo info = jwtUtil.getInfoFromToken(token);
        String username = jwtUtil.getInfoFromToken(token).getUniqueName();
        if (username == null) {
            return null;
        }
        UserInfo user = this.getUserByUsername(username);
        FrontUser frontUser = new FrontUser();
        BeanUtils.copyProperties(user, frontUser);
        List<PermissionInfo> permissionInfos = this.getPermissionByUsername(username);
        Stream<PermissionInfo> menus = permissionInfos.parallelStream().filter((permission) -> {
            return permission.getType().equals(CommonConstants.RESOURCE_TYPE_MENU);
        });
        frontUser.setMenus(menus.collect(Collectors.toList()));
        Stream<PermissionInfo> elements = permissionInfos.parallelStream().filter((permission) -> {
            return !permission.getType().equals(CommonConstants.RESOURCE_TYPE_MENU);
        });
        frontUser.setElements(elements.collect(Collectors.toList()));
        return frontUser;
    }

    public List<MenuTree> getMenusByUsername(String token) throws Exception {
        String username = jwtUtil.getInfoFromToken(token).getUniqueName();
        if (username == null) {
            return null;
        }
        BaseUser user = userBiz.getUserByUsername(username);
        List<Menu> menus = menuBiz.getUserAuthorityMenuByUserId(user.getId());
        return getMenuTree(menus, AdminCommonConstant.ROOT);
    }

}
