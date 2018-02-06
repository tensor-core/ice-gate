package com.cssrc.product.biz;

import com.cssrc.common.biz.BaseBiz;
import com.cssrc.product.entity.ProductInfo;
import com.cssrc.product.mapper.ProductInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductInfoBiz extends BaseBiz<ProductInfoMapper,ProductInfo> {

}
