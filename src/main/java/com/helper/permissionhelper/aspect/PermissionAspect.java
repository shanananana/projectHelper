package com.helper.permissionhelper.aspect;

import com.helper.exceptionhelper.BusinessException;
import com.helper.exceptionhelper.EmBusinessError;
import com.helper.permissionhelper.annotation.PermissionAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.List;

@Aspect
@Component
@Slf4j
public class PermissionAspect {
    @Autowired
    HttpServletRequest request;
    /**
     * 切点
     * */
    @Pointcut("@annotation(com.helper.permissionhelper.annotation.PermissionAnnotation)")
    public void permisionPointCut(){ }
    /**
     * 鉴权
     * */
    @Before("permisionPointCut()")
    public void beforePointcut(JoinPoint joinPoint) {
        MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        PermissionAnnotation annotation = method.getAnnotation(PermissionAnnotation.class);
        String value = annotation.value();
        HttpSession session=request.getSession();
        if(session==null){throw new BusinessException(EmBusinessError.NOT_LOGIN);}
        List<String> permissionList= (List<String>) session.getAttribute("permissionList");
        if(!permissionList.contains(value)){throw new BusinessException(EmBusinessError.PERMISSION_ERROR);}
    }



}
