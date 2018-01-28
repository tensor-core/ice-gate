package com.cssrc.uc.biz;

import com.cssrc.common.biz.BaseBiz;
import com.cssrc.uc.entity.GroupType;
import com.cssrc.uc.mapper.GroupTypeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class GroupTypeBiz extends BaseBiz<GroupTypeMapper,GroupType> {
}
