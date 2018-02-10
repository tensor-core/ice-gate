package com.cssrc.warehouse.biz;

import com.cssrc.common.biz.BaseBiz;
import com.cssrc.warehouse.entity.StorageInfo;
import com.cssrc.warehouse.entity.WarehouseInfo;
import com.cssrc.warehouse.mapper.StorageInfoMapper;
import com.cssrc.warehouse.mapper.WarehouseInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WarehouseInfoBiz extends BaseBiz<WarehouseInfoMapper,WarehouseInfo> {

}
