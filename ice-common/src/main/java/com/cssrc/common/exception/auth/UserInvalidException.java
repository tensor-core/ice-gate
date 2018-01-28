package com.cssrc.common.exception.auth;


import com.cssrc.common.constant.CommonConstants;
import com.cssrc.common.exception.BaseException;

/**
 * Created by ace on 2017/9/10.
 */
public class UserInvalidException extends BaseException {
    public UserInvalidException(String message) {
        super(message, CommonConstants.EX_USER_INVALID_CODE);
    }
}
