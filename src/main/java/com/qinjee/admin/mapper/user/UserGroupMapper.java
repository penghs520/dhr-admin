package com.qinjee.admin.mapper.user;

import com.qinjee.admin.entity.SysMenu;
import com.qinjee.admin.entity.user.UserGroup;
import com.qinjee.admin.entity.user.UserGroupMenu;
import com.qinjee.admin.model.ao.UserPageAo;
import com.qinjee.admin.model.vo.UserGroupVo;
import com.qinjee.admin.model.vo.UserUnderGroupVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author penghs
 * @since 2020-05-09
 */
public interface UserGroupMapper {
    List<SysMenu> selectMenus(Integer userId);

    int insertGroupMenu(UserGroupMenu groupMenu);

    int deleteGroupMenu(Integer userGroupId);

    List<UserUnderGroupVo> selectUserPage(UserPageAo pageAo);

    UserGroupVo selectById(Integer userGroupId);

    List<SysMenu> selectMenusByGroupId(Integer userGroupId);

    int insert(UserGroup userGroupEntity);

    int updateById(UserGroup userGroup);

    int deleteById(Integer userGroupId);

    List<UserGroup> list();

}
