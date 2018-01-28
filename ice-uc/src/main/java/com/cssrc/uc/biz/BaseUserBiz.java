package com.cssrc.uc.biz;

import com.cssrc.common.biz.BaseBiz;
import com.cssrc.common.constant.UserConstant;
import com.cssrc.uc.entity.BaseUser;
import com.cssrc.uc.mapper.BaseUserMapper;
import com.cssrc.uc.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BaseUserBiz extends BaseBiz<BaseUserMapper,BaseUser> {

    @Autowired
    private MenuMapper menuMapper;

    public void insertSelective(BaseUser entity) {
        String password = new BCryptPasswordEncoder(UserConstant.PW_ENCORDER_SALT).encode(entity.getPassword());
        entity.setPassword(password);
        super.insertSelective(entity);
    }


    public void updateSelectiveById(BaseUser entity) {
        super.updateSelectiveById(entity);
    }


    public BaseUser getUserByUsername(String username){
        BaseUser user = new BaseUser();
        user.setUsername(username);
        return mapper.selectOne(user);
    }

}
