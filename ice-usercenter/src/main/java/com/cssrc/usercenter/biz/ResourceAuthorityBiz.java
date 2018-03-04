package com.cssrc.usercenter.biz;

import com.cssrc.common.biz.BaseBiz;
import com.cssrc.usercenter.entity.ResourceAuthority;
import com.cssrc.usercenter.mapper.ResourceAuthorityMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ResourceAuthorityBiz extends BaseBiz<ResourceAuthorityMapper,ResourceAuthority> {
}
