package com.qinjee.admin.model.vo;

import com.qinjee.admin.entity.SysMenu;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserGroupVo implements Serializable {

    private String userGroupName;

    private Integer userGroupId;

    private List<SysMenu> menus;

}
