package com.cssrc.warehouse.biz;

import com.cssrc.common.biz.BaseBiz;
import com.cssrc.warehouse.entity.StorageInfo;
import com.cssrc.warehouse.mapper.StorageInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StorageInfoBiz extends BaseBiz<StorageInfoMapper,StorageInfo> {

}
