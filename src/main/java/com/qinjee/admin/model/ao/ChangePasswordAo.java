package com.qinjee.admin.model.ao;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChangePasswordAo implements Serializable {
    /**
     * 手机号
     */
    private String phone;

    /**
     * 新密码
     */
    private String pwd1;

    /**
     * 确认新密码
     */
    private String pwd2;
}
