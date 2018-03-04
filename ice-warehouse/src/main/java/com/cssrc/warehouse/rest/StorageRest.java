package com.cssrc.warehouse.rest;

import com.cssrc.common.blockchain.ExecuteCommands;
import com.cssrc.common.msg.ObjectRestResponse;
import com.cssrc.common.rest.BaseController;
import com.cssrc.warehouse.biz.StorageInfoBiz;
import com.cssrc.warehouse.entity.StorageInfo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/storage")
public class StorageRest extends BaseController<StorageInfoBiz,StorageInfo> {

    @RequestMapping(value = "/{id}/inWarehouse", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<StorageInfo> inWarehouse(@PathVariable int id) {
        System.out.println(id);
        Object o = baseBiz.selectById(id);
        StorageInfo info = (StorageInfo)o;
        //在区块链链条上增加区块
        ExecuteCommands client = new ExecuteCommands(8888,"client","warehouse");
        return new ObjectRestResponse<StorageInfo>();
    }
}
