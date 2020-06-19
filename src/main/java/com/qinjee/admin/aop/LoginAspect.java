package com.qinjee.admin.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoginAspect {

    /**
     * 在方法调用之前，打印入参
     */
    @Before(value = "execution(public * com.qinjee.admin.controller.*.*.*(..))")
    public void before(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        StringBuilder params = new StringBuilder();
        for (Object arg : args) {
            params.append(arg).append(" ");
        }
        log.info(className + "的" + methodName + "入参为：" + params.toString());
    }

    /**
     * 返回之后，打印出参
     */
    @AfterReturning(value = "execution(public * com.qinjee.admin.controller.*.*.*(..))", returning = "returnVal")
    public void afterReturin(JoinPoint joinPoint, Object returnVal) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        if(returnVal!=null){
            log.info(className + "的" + methodName + "结果为：" + returnVal.toString());
        }
    }

}
