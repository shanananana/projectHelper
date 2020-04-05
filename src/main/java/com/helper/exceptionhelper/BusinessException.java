package com.helper.exceptionhelper;


/**
 * <p>BusinessException class.</p>
 *
 * @author 59575
 * @version $Id: $Id
 */
public class BusinessException extends RuntimeException implements CommonError {

    private CommonError commonError;

    /**
     * <p>Constructor for BusinessException.</p>
     */
    public BusinessException() {
        super();
    }

    /**
     * <p>Constructor for BusinessException.</p>
     *
     * @param CommonError a {@link com.helper.exceptionhelper.CommonError} object.
     */
    public BusinessException(CommonError CommonError) {
        super();
        this.commonError = CommonError;
    }

    /**
     * <p>Constructor for BusinessException.</p>
     *
     * @param CommonError a {@link com.helper.exceptionhelper.CommonError} object.
     * @param ErrMsg a {@link java.lang.String} object.
     */
    public BusinessException(CommonError CommonError, String ErrMsg) {
        super();
        this.commonError = CommonError;
        this.commonError.setErrorMsg(ErrMsg);
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
