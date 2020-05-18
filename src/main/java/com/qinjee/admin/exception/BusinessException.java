package com.qinjee.admin.exception;


import com.qinjee.admin.model.ResultCode;

/**
 * 自定义异常类型
 **/
public class BusinessException extends RuntimeException {

    private Integer code;
    private String message;

    public BusinessException(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
