package com.qinjee.admin.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserSession implements Serializable {

    private Integer userId=1;

    private String userName;

    private String phone;

    private String email;
}
