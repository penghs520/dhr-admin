package com.qinjee.admin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 权限级别：菜单级
 * </p>
 *
 * @author penghs
 * @since 2020-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserGroupMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer userGroupId;

    private Integer menuId;


}
