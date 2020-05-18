package com.qinjee.admin.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qinjee.admin.entity.SysMenu;
import com.qinjee.admin.entity.user.User;
import com.qinjee.admin.entity.user.UserGroup;
import com.qinjee.admin.entity.user.UserGroupMenu;
import com.qinjee.admin.exception.ExceptionCast;
import com.qinjee.admin.mapper.user.UserGroupMapper;
import com.qinjee.admin.mapper.user.UserMapper;
import com.qinjee.admin.model.PageResult;
import com.qinjee.admin.model.ResultCode;
import com.qinjee.admin.model.ao.UserGroupAo;
import com.qinjee.admin.model.ao.UserPageAo;
import com.qinjee.admin.model.vo.UserGroupVo;
import com.qinjee.admin.model.vo.UserUnderGroupVo;
import com.qinjee.admin.service.user.IUserGroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 用户组以及菜单权限相关的服务
 * </p>
 *
 * @author penghs
 * @since 2020-05-09
 */
@Service
public class UserGroupServiceImpl implements IUserGroupService {

    @Resource
    private UserGroupMapper userGroupMapper;

    @Resource
    private UserMapper userMapper;
    @Override
    public List<SysMenu> getMenus(Integer userId) {
        List<SysMenu> menus = userGroupMapper.selectMenus(userId);
        List<SysMenu> menuTree = buildMenuTree(menus);
        return menuTree;
    }

    /**
     * 将菜单列表构造为菜单树
     *
     * @param menus
     */
    private List<SysMenu> buildMenuTree(List<SysMenu> menus) {
        Map<Integer, SysMenu> menusMap = new HashMap<>();
        for (SysMenu menu : menus) {
            menusMap.put(menu.getMenuId(), menu);
        }
        for (SysMenu menu : menus) {
            if (menu.getParentId() != null) {
                menusMap.get(menu.getParentId()).getChildMenus().add(menu);
            }
        }
        Iterator<Map.Entry<Integer, SysMenu>> it = menusMap.entrySet().iterator();
        while (it.hasNext()) {
            Integer parentId = it.next().getValue().getParentId();
            if (parentId != null) {
                it.remove();
            }
        }
        List<SysMenu> menuTree = new ArrayList<>();
        Iterator<Map.Entry<Integer, SysMenu>> it2 = menusMap.entrySet().iterator();
        while (it2.hasNext()) {
            menuTree.add(it2.next().getValue());
        }
        return menuTree;
    }

    @Override
    @Transactional
    public Boolean addUserGroup(UserGroupAo userGroupAo) {
        UserGroup userGroupEntity = new UserGroup();
        userGroupEntity.setUserGroupName(userGroupAo.getUserGroupName());
        int i = userGroupMapper.insert(userGroupEntity);

        List<Integer> menuIds = userGroupAo.getMenuIds();
        if (!CollectionUtils.isEmpty(menuIds)) {
            for (Integer menuId : menuIds) {
                UserGroupMenu groupMenu = new UserGroupMenu();
                groupMenu.setMenuId(menuId);
                groupMenu.setUserGroupId(userGroupEntity.getUserGroupId());
                userGroupMapper.insertGroupMenu(groupMenu);
            }
        }
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean editUserGroup(UserGroupAo userGroupAo) {
        UserGroup userGroup = new UserGroup();
        userGroup.setUserGroupName(userGroupAo.getUserGroupName());
        userGroup.setUserGroupId(userGroupAo.getUserGroupId());
        int i = userGroupMapper.updateById(userGroup);

        userGroupMapper.deleteGroupMenu(userGroupAo.getUserGroupId());

        List<Integer> menuIds = userGroupAo.getMenuIds();
        if (!CollectionUtils.isEmpty(menuIds)) {
            for (Integer menuId : menuIds) {
                UserGroupMenu groupMenu = new UserGroupMenu();
                groupMenu.setMenuId(menuId);
                groupMenu.setUserGroupId(userGroupAo.getUserGroupId());
                userGroupMapper.insertGroupMenu(groupMenu);
            }
        }
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean removeUserGroup(Integer userGroupId) {

        //判断用户组下是否存在用户
        User user = new User();
        user.setUserGroupId(userGroupId);
        List<User> users = userMapper.listByUser(user);
        if(!CollectionUtils.isEmpty(users)){
            ExceptionCast.cast(ResultCode.USER_GROUP_HAS_USER);
        }

        int i = userGroupMapper.deleteById(userGroupId);
        userGroupMapper.deleteGroupMenu(userGroupId);
        if (i > 0) {
            return true;
        }
        return false;
    }



    @Override
    public List<UserGroup> list() {
        return userGroupMapper.list();
    }

    @Override
    public Boolean sort(List<Integer> userGroupIds) {
        Integer sortId=1;
        int sum=0;
        for (Integer id : userGroupIds) {
            UserGroup userGroup = new UserGroup();
            userGroup.setUserGroupId(id);
            userGroup.setSort(sortId++);
            sum+=userGroupMapper.updateById(userGroup);//TODO 建议替换成批量更新
        }
        if(sum>0){
            return true;
        }
        return false;
    }

    @Override
    public PageResult<UserUnderGroupVo> listUser(UserPageAo pageAo) {
        if (pageAo.getCurrentPage() != null && pageAo.getPageSize() != null) {
            PageHelper.startPage(pageAo.getCurrentPage(), pageAo.getPageSize());
        }
        List<UserUnderGroupVo> userList= userGroupMapper.selectUserPage(pageAo);
        PageInfo<UserUnderGroupVo> pageInfo = new PageInfo<>(userList);
        PageResult<UserUnderGroupVo> pageResult = new PageResult<>();
        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setList(userList);
        return pageResult;
    }

    @Override
    public UserGroupVo getUserGroupById(Integer userGroupId) {
        List<SysMenu> menus =userGroupMapper.selectMenusByGroupId(userGroupId);
        List<SysMenu> menuTree = buildMenuTree(menus);
        UserGroupVo userGroup = userGroupMapper.selectById(userGroupId);
        userGroup.setMenus(menuTree);
        return userGroup;
    }
}
