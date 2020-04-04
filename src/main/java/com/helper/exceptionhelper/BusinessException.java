package com.helper.exceptionhelper;


public class BusinessException extends RuntimeException implements CommonError {

    private CommonError commonError;

    public BusinessException() {
        super();
    }

    public BusinessException(CommonError CommonError) {
        super();
        this.commonError = CommonError;
    }

    public BusinessException(CommonError CommonError, String ErrMsg) {
        super();
        this.commonError = CommonError;
        this.commonError.setErrorMsg(ErrMsg);
    }

    @Override
    public int getErrorCode() {
        return this.commonError.getErrorCode();
    }

    @Override
    public String getErrorMsg() {
        return this.commonError.getErrorMsg();
    }

    @Override
    public CommonError setErrorMsg(String errorMsg) {
        this.commonError.setErrorMsg(errorMsg);
        return this;
    }
}
