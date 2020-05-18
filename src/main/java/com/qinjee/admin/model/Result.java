package com.qinjee.admin.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class Result<T> implements Serializable {

    private Integer code;

    private String message;

    private T data;



    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }
    public Result(Integer code,String message) {
        this.code = code;
        this.message = message;
    }


    public static Result success() {
        return new Result(ResultCode.SUCCESS);
    }

    public static Result result(Integer code,String message) {
        return new Result(code,message);
    }

    public static Result result(ResultCode resultCode) {
        return new Result(resultCode.getCode(),resultCode.getMessage());
    }

    public static Result success(Object data) {
        return new Result(ResultCode.SUCCESS).setData(data);
    }

    public static Result fail() {
        return new Result(ResultCode.FAIL);
    }

    public static Result fail(String message) {
        return new Result(ResultCode.FAIL).setMessage(message);
    }

}
