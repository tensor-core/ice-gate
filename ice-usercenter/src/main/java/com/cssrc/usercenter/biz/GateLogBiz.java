package com.cssrc.usercenter.biz;

import com.cssrc.common.biz.BaseBiz;
import com.cssrc.usercenter.entity.GateLog;
import com.cssrc.usercenter.mapper.GateLogMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class GateLogBiz extends BaseBiz<GateLogMapper,GateLog> {

    @Override
    public void insert(GateLog entity) {
        mapper.insert(entity);
    }

    @Override
    public void insertSelective(GateLog entity) {
        mapper.insertSelective(entity);
    }
}
