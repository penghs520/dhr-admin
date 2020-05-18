package com.qinjee.admin.controller.user;

import com.qinjee.admin.controller.BaseController;
import com.qinjee.admin.entity.SysMenu;
import com.qinjee.admin.entity.user.UserGroup;
import com.qinjee.admin.model.PageResult;
import com.qinjee.admin.model.Result;
import com.qinjee.admin.model.UserSession;
import com.qinjee.admin.model.ao.UserGroupAo;
import com.qinjee.admin.model.ao.UserPageAo;
import com.qinjee.admin.model.vo.UserGroupVo;
import com.qinjee.admin.model.vo.UserUnderGroupVo;
import com.qinjee.admin.service.user.IUserGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/user_group")
@Api(tags = "【权限管理-用户组】")
public class UserGroupController extends BaseController {

    @Autowired
    private IUserGroupService userGroupService;

    @ApiOperation(value = "获取菜单")
    @GetMapping(value = "/getMenus")
    public Result<List<SysMenu>> getMenus() {
        UserSession session = getSession();
        List<SysMenu> menus = userGroupService.getMenus(session.getUserId());
        return Result.success(menus);
    }

    @ApiOperation(value = "查看用户组信息")
    @GetMapping(value = "/getInfo")
    public Result<UserGroupVo> getInfo(Integer userGroupId) {
        UserGroupVo userGroup = userGroupService.getUserGroupById(userGroupId);
        return Result.success(userGroup);
    }


    @ApiOperation(value = "新增用户组")
    @PostMapping(value = "/save")
    public Result save(@RequestBody UserGroupAo userGroupAo) {
        boolean bool = userGroupService.addUserGroup(userGroupAo);
        if (bool) {
            return Result.success();
        }
        return Result.fail();
    }

    @ApiOperation(value = "编辑用户组")
    @PostMapping(value = "/update")
    public Result update(@RequestBody UserGroupAo userGroupAo) {
        boolean bool = userGroupService.editUserGroup(userGroupAo);
        if (bool) {
            return Result.success();
        }
        return Result.fail();
    }

    @ApiOperation(value = "删除用户组")
    @GetMapping(value = "/removeUserGroup")
    public Result remove(Integer userGroupId) {
        boolean bool = userGroupService.removeUserGroup(userGroupId);
        if (bool) {
            return Result.success();
        }
        return Result.fail();
    }


    @ApiOperation(value = "查询用户组列表")
    @GetMapping(value = "/list")
    public Result<List<UserGroup>> list() {
        List<UserGroup> list=userGroupService.list();
        return Result.success(list);
    }


    @ApiOperation(value = "查询用户组下的用户列表")
    @PostMapping(value = "/listUser")
    public Result<PageResult<UserUnderGroupVo>> listUser(@RequestBody UserPageAo pageAo) {
        PageResult<UserUnderGroupVo> list=userGroupService.listUser(pageAo);
        return Result.success(list);
    }

    @ApiOperation(value = "用户组排序")
    @PostMapping(value = "/sort")
    public Result sort(@RequestBody List<Integer> userGroupIds) {
        Boolean bool=userGroupService.sort(userGroupIds);
        if (bool) {
            return Result.success();
        }
        return Result.fail();
    }


}
