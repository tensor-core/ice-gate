package com.cssrc.uc.mapper;

import com.cssrc.uc.entity.BaseUser;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BaseUserMapper extends Mapper<BaseUser> {
    public List<BaseUser> selectMemberByGroupId(@Param("groupId") int groupId);
    public List<BaseUser> selectLeaderByGroupId(@Param("groupId") int groupId);
}