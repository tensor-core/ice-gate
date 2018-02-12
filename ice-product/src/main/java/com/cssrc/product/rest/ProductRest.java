package com.cssrc.product.rest;

import com.cssrc.common.MessageInfo.Message;
import com.cssrc.common.msg.ObjectRestResponse;
import com.cssrc.common.rest.BaseController;
import com.cssrc.product.biz.ProductInfoBiz;
import com.cssrc.product.entity.ProductInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductRest extends BaseController<ProductInfoBiz,ProductInfo> {
    @Autowired
    public ProductInfoBiz biz;

    @Autowired
    @Qualifier("outputStorage")
    MessageChannel outputStorage;

    @RequestMapping(value = "/{id}/inStorage", method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<ProductInfo> inStorage(@PathVariable int id) {
        System.out.println(id);
        Object o = baseBiz.selectById(id);
        ProductInfo info = (ProductInfo)o;
        if(!"已入库".equals(info.getGoodstypeid())) {
            info.setGoodstypeid("已入库");
            biz.updateById(info);
            //给仓库部门发送消息
            sendMessage(info);
        }
        return new ObjectRestResponse<ProductInfo>();
    }


    public void sendMessage(ProductInfo info) {
        // 实体类型发送MQ
        System.out.println("产品入库实体发送");
        Message message = new Message();
        message.setProductId(info.getId());
        message.setProductName(info.getName());
        message.setQty(info.getQty());
        message.setCreateAt(new Date());
        // 使用 注入 MessageChannel 方式发送
        outputStorage.send(MessageBuilder.withPayload(message).build());
    }
}
