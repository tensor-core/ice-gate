package com.cssrc.generator.biz;

import com.cssrc.common.biz.BaseBiz;
import com.cssrc.common.exception.BaseException;
import com.cssrc.generator.entity.BaseUser;
import com.cssrc.generator.mapper.BaseUserMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BaseUserBiz extends BaseBiz<BaseUserMapper,BaseUser> {

}
