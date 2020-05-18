package com.qinjee.admin.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户详情，包含分组、权限
 */
@Data
public class UserDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String userName;

    private String userGroupName;

    private List<String> menus;

    private String logo;

    private String phone;

    private String email;
}
