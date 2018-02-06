package com.cssrc.product.rest;

import com.cssrc.common.rest.BaseController;
import com.cssrc.product.biz.ProductInfoBiz;
import com.cssrc.product.entity.ProductInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductRest extends BaseController<ProductInfoBiz,ProductInfo> {


}
