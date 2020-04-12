package com.helper.exceptionhelper;

import com.helper.exceptionhelper.CommonError;

/**
 * 异常枚举类
 *
 * @author 59575
 * @version $Id: $Id
 */
public enum EmBusinessError implements CommonError {

    PARAM_ERROR(400001,"参数校验异常"),
    CONVERT_ERROR(500001,"对象转换失败"),
    PERMISSION_ERROR(500002,"权限不足"),
    NOT_LOGIN(500003,"未登录");

    private int errCode;
    private String errMsg;
    private EmBusinessError(int errCode,String errMsg ) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }


    /** {@inheritDoc} */
    @Override
    public int getErrorCode() {
        return this.errCode;
    }

    /** {@inheritDoc} */
    @Override
    public String getErrorMsg() {
        return this.errMsg;
    }

    /** {@inheritDoc} */
    @Override
    public CommonError setErrorMsg(String errMsg) {
        this.errMsg= errMsg;
        return this;
    }
}
