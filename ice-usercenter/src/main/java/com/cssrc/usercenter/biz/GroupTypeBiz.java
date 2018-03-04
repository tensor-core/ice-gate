package com.cssrc.usercenter.biz;

import com.cssrc.common.biz.BaseBiz;
import com.cssrc.usercenter.entity.GroupType;
import com.cssrc.usercenter.mapper.GroupTypeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class GroupTypeBiz extends BaseBiz<GroupTypeMapper,GroupType> {
}
