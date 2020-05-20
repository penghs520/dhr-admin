package com.qinjee.admin.service;

import com.qinjee.admin.entity.SysMenu;
import com.qinjee.admin.entity.UserGroup;
import com.qinjee.admin.model.PageResult;
import com.qinjee.admin.model.ao.UserGroupAo;
import com.qinjee.admin.model.ao.UserPageAo;
import com.qinjee.admin.model.vo.UserGroupVo;
import com.qinjee.admin.model.vo.UserUnderGroupVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author penghs
 * @since 2020-05-09
 */
public interface IUserGroupService {

    List<SysMenu> getMenus(Integer userId);

    Boolean addUserGroup(UserGroupAo userGroupAo);

    Boolean editUserGroup(UserGroupAo userGroupAo);

    Boolean removeUserGroup(Integer userGroupId);


    List<UserGroup> list();

    Boolean sort(List<Integer> userGroupIds);

    PageResult<UserUnderGroupVo> listUser(UserPageAo pageAo);

    UserGroupVo getUserGroupById(Integer userGroupId);
}
