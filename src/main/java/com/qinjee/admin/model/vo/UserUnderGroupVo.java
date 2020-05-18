package com.qinjee.admin.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserUnderGroupVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String userNo;

    private String userName;

    private String phone;

    private String email;

    private String userGroupName;

    private Integer userGroupId;

    private List<String> menusNames;

}
