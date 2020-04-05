package com.helper.exceptionhelper;

/**
 * <p>ParamValidException class.</p>
 *
 * @author 59575
 * @version $Id: $Id
 */
public class ParamValidException extends RuntimeException implements CommonError {
    private CommonError commonError;

    /**
     * <p>Constructor for ParamValidException.</p>
     */
    public ParamValidException() {
        super();
    }

    /**
     * <p>Constructor for ParamValidException.</p>
     *
     * @param CommonError a {@link com.helper.exceptionhelper.CommonError} object.
     */
    public ParamValidException(CommonError CommonError) {
        super();
        this.commonError = CommonError;
    }

    /**
     * 参数校验异常 column为出错的参数名
     *
     * @param CommonError a {@link com.helper.exceptionhelper.CommonError} object.
     * @param column a {@link java.lang.String} object.
     */
    public ParamValidException(CommonError CommonError, String column) {
        super();
        this.commonError = CommonError;
        this.commonError.setErrorMsg(column+"参数校验异常");
    }

    /** {@inheritDoc} */
    @Override
    public int getErrorCode() {
        return this.commonError.getErrorCode();
    }

    /** {@inheritDoc} */
    @Override
    public String getErrorMsg() {
        return this.commonError.getErrorMsg();
    }

    /** {@inheritDoc} */
    @Override
    public CommonError setErrorMsg(String errorMsg) {
        this.commonError.setErrorMsg(errorMsg);
        return this;
    }
}
