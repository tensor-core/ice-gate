package com.cssrc.common.exception.auth;


import com.cssrc.common.constant.CommonConstants;
import com.cssrc.common.exception.BaseException;


/**
 * Created by ace on 2017/9/8.
 */
public class TokenErrorException extends BaseException {
    public TokenErrorException(String message, int status) {
        super(message, CommonConstants.EX_TOKEN_ERROR_CODE);
    }
}
