package com.cssrc.uc.biz;

import com.cssrc.common.biz.BaseBiz;
import com.cssrc.uc.entity.ResourceAuthority;
import com.cssrc.uc.mapper.ResourceAuthorityMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ResourceAuthorityBiz extends BaseBiz<ResourceAuthorityMapper,ResourceAuthority> {
}
