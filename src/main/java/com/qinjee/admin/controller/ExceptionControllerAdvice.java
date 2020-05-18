package com.qinjee.admin.controller;


import com.google.common.collect.ImmutableMap;
import com.qinjee.admin.exception.BusinessException;
import com.qinjee.admin.model.Result;
import com.qinjee.admin.model.ResultCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class ExceptionControllerAdvice {

    private static final Logger LOGGER = LogManager.getLogger(ExceptionControllerAdvice.class);


    /**
     * 捕获BusinessException此类异常
     *
     * @param be
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result customException(BusinessException be) {

        //记录日志
        LOGGER.error("catch customException！code={};message={}",be.getCode(),be.getMessage());

        Result result = Result.result(be.getCode(),be.getMessage());
        return result;
    }

    /**
     * 捕获Exception此类异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exception(Exception e, Throwable ex) {

        //记录日志
        LOGGER.error("catch exception ex:", ex);
        return Result.fail();
    }


}
