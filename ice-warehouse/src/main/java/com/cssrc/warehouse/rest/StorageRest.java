package com.cssrc.warehouse.rest;

import com.cssrc.common.rest.BaseController;
import com.cssrc.warehouse.biz.StorageInfoBiz;
import com.cssrc.warehouse.entity.StorageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/storage")
public class StorageRest extends BaseController<StorageInfoBiz,StorageInfo> {


}
