package com.qinjee.admin.exception;


import com.qinjee.admin.model.ResultCode;

/**
 *异常抛出类
 */
public class ExceptionCast {


    public static void cast(ResultCode resultCode){
        throw new BusinessException(resultCode);
    }
}
