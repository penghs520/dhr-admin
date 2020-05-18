package com.qinjee.admin.model;

public enum ResultCode {
    SUCCESS(200, "操作成功！"),
    FAIL(500, "操作失败！"),
    INVALID_SESSION(501, "登录超时或未登录！"),
    PHONE_NOT_BE_NULL(500, "手机号不能为空！"),
    USERNAME_NOT_BE_NULL(500, "用户名不能为空！"),
    USER_GROUP_NOT_BE_NULL(500, "用户组不能为空！"),
    USER_GROUP_HAS_USER(500, "用户组下存在用户，不能删除！"),
    PHONE_ALREADY_USED(500, "手机号已被使用！"),
    NEWPHONE_NOT_BE_NULL(500, "新手机号不能为空！"),
    PASSWORD_NOT_BE_NULL(500, "密码不能为空！"),
    NEWPASSWORD_NOT_BE_NULL(500, "新密码不能为空！"),
    CODE_NOT_BE_NULL(500, "验证码不能为空！"),
    CODE_LOSE_EFFICACY (500, "验证码失效！"),
    CODE_ERR(500, "验证码错误！"),
    PASSWORD_ERR(500, "密码错误！"),
    ACCOUNT_NOT_EXSIT(500, "账号不存在！"),


    ;

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
