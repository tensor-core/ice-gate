package com.cssrc.warehouse.rest;

import com.cssrc.common.rest.BaseController;
import com.cssrc.warehouse.biz.WarehouseInfoBiz;
import com.cssrc.warehouse.entity.WarehouseInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/warehouse")
public class WarehouseRest extends BaseController<WarehouseInfoBiz,WarehouseInfo> {


}
