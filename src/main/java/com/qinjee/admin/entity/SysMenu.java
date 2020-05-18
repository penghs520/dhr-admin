package com.qinjee.admin.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author penghs
 * @since 2020-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("菜单id")
    private Integer menuId;

    @ApiModelProperty("菜单名称")
    private String menuName;

    @ApiModelProperty("菜单code")
    private String menuCode;

    @ApiModelProperty("功能类型：module、menu")
    private String funcType;

    @ApiModelProperty("上级菜单id")
    private Integer parentId;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("是否有权")
    private short hasRole;

    @ApiModelProperty("子菜单")
    private List<SysMenu> childMenus =new ArrayList<>();



}
