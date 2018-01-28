package com.cssrc.uc.biz;

import com.cssrc.common.biz.BaseBiz;
import com.cssrc.uc.entity.Element;
import com.cssrc.uc.mapper.ElementMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ElementBiz extends BaseBiz<ElementMapper,Element> {

    public List<Element> getAuthorityElementByUserId(String userId){
       return mapper.selectAuthorityElementByUserId(userId);
    }
    public List<Element> getAuthorityElementByUserId(String userId,String menuId){
        return mapper.selectAuthorityMenuElementByUserId(userId,menuId);
    }


    public List<Element> selectListAll() {
        return super.selectListAll();
    }


    public void insertSelective(Element entity) {
        super.insertSelective(entity);
    }

    public void updateSelectiveById(Element entity) {
        super.updateSelectiveById(entity);
    }
}
