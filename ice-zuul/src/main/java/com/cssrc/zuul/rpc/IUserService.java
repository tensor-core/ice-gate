package com.cssrc.zuul.rpc;

import com.cssrc.common.vo.PermissionInfo;
import feign.Param;
import feign.RequestLine;

import java.util.List;



public interface IUserService {
  @RequestLine(value = "GET /api/user/un/{username}/permissions")
  public List<PermissionInfo> getPermissionByUsername(@Param("username") String username);
  @RequestLine(value = "GET /api/permissions")
  List<PermissionInfo> getAllPermissionInfo();
}
