package com.helper.exceptionhelper;

public interface CommonError {
     int getErrorCode();

     String getErrorMsg();

     CommonError setErrorMsg(String errorMsg);
}
