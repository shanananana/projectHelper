package com.helper.exceptionhelper;

public class ParamValidException extends RuntimeException implements CommonError {
    private CommonError commonError;

    public ParamValidException() {
        super();
    }

    public ParamValidException(CommonError CommonError) {
        super();
        this.commonError = CommonError;
    }

    /**
     * 参数校验异常 column为出错的参数名
     * */
    public ParamValidException(CommonError CommonError, String column) {
        super();
        this.commonError = CommonError;
        this.commonError.setErrorMsg(column+"参数校验异常");
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
