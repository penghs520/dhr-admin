package com.qinjee.admin.model.ao;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserAo implements Serializable {

    private Integer userId;

    private Integer userGroupId;

    private String userName;

    private String phone;

    private String email;

}
