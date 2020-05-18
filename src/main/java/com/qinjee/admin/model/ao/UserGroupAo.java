package com.qinjee.admin.model.ao;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserGroupAo implements Serializable {

    @ApiModelProperty(notes = "用户组名称",required = true)
    private String userGroupName;
    @ApiModelProperty(notes = "用户组id，编辑时为必填")
    private Integer userGroupId;
    @ApiModelProperty(notes = "菜单权限id",required = true)
    private List<Integer> menuIds;
}
