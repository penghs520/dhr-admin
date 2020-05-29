package com.qinjee.admin.entity;

import com.qinjee.admin.model.vo.UserInfoVo;
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
public class UserGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userGroupId;

    private String userGroupName;

    private Integer sort;

    private List<User> users =new ArrayList<>();


}
