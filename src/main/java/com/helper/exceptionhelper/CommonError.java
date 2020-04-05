package com.helper.exceptionhelper;

/**
 * <p>CommonError interface.</p>
 *
 * @author 59575
 * @version $Id: $Id
 */
public interface CommonError {
     /**
      * <p>getErrorCode.</p>
      *
      * @return a int.
      */
     int getErrorCode();

     /**
      * <p>getErrorMsg.</p>
      *
      * @return a {@link java.lang.String} object.
      */
     String getErrorMsg();

     /**
      * <p>setErrorMsg.</p>
      *
      * @param errorMsg a {@link java.lang.String} object.
      * @return a {@link com.helper.exceptionhelper.CommonError} object.
      */
     CommonError setErrorMsg(String errorMsg);
}
