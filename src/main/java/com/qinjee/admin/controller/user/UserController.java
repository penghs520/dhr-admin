package com.qinjee.admin.controller.user;


import com.qinjee.admin.config.redis.RedisClusterService;
import com.qinjee.admin.controller.BaseController;
import com.qinjee.admin.entity.User;
import com.qinjee.admin.entity.UserGroup;
import com.qinjee.admin.model.Result;
import com.qinjee.admin.model.ResultCode;
import com.qinjee.admin.model.ao.UserAo;
import com.qinjee.admin.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户模块
 * </p>
 *
 * @author penghs
 * @since 2020-05-09
 */
@RestController
@RequestMapping("/admin/user")
@Api(tags = "【权限管理-用户】")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;
    @Autowired
    private RedisClusterService redisClusterService;


    @ApiOperation(value = "查看用户信息")
    @GetMapping(value = "/getInfo")
    public Result getInfo() {
        User user = userService.getUserById(getSession().getUserId());
        if (user==null){
            return Result.fail();
        }
        return Result.success(user);

    }
    @ApiOperation(value = "新增用户")
    @PostMapping(value = "/save")
    public Result saveUser(@RequestBody UserAo userAo) {
        if(StringUtils.isBlank(userAo.getUserName())){
            return Result.result(ResultCode.USERNAME_NOT_BE_NULL);
        }
        if(StringUtils.isBlank(userAo.getPhone())){
            Result.result(ResultCode.PHONE_NOT_BE_NULL);
        }
        if(null==userAo.getUserGroupId()){
            return Result.result(ResultCode.USER_GROUP_NOT_BE_NULL);
        }
        //判断手机号是否被使用
        User user = userService.getUserByPhone(userAo.getPhone());
        if(user!=null){
            return Result.result(ResultCode.PHONE_ALREADY_USED);
        }
        Boolean bool=userService.save(userAo);
        if (bool){
            return Result.success();
        }
        return Result.fail();

    }
    @ApiOperation(value = "编辑用户")
    @PostMapping(value = "/update")
    public Result update(@RequestBody UserAo userAo) {
        if(StringUtils.isBlank(userAo.getUserName())){
            return Result.result(ResultCode.USERNAME_NOT_BE_NULL);
        }
        if(StringUtils.isBlank(userAo.getPhone())){
            Result.result(ResultCode.PHONE_NOT_BE_NULL);
        }
        if(null==userAo.getUserGroupId()){
            return Result.result(ResultCode.USER_GROUP_NOT_BE_NULL);
        }

        Boolean bool=userService.updateById(userAo);
        if (bool){
            return Result.success();
        }
        return Result.fail();

    }
    @ApiOperation(value = "删除用户")
    @GetMapping(value = "/remove")
    public Result remove(Integer userId) {
        Boolean bool=userService.removeById(userId);
        if (bool){
            return Result.success();
        }
        return Result.fail();

    }

    @ApiOperation(value = "修改当前用户密码")
    @GetMapping(value = "/changePassword")
    public Result changePassword(String newPassword) {
        if(StringUtils.isBlank(newPassword)){
            return Result.result(ResultCode.NEWPASSWORD_NOT_BE_NULL);
        }
        Boolean bool=userService.changePassword(getSession(),newPassword);
        if(bool){
            return Result.success();
        }
        return Result.fail();
    }

    @ApiOperation(value = "修改当前用户绑定手机")
    @GetMapping(value = "/changePhone")
    public Result changePhone(String newPhone,String smsCode) {
        if (newPhone == null) {
            return Result.result(ResultCode.NEWPHONE_NOT_BE_NULL);
        }
        if (smsCode == null) {
            return Result.result(ResultCode.CODE_NOT_BE_NULL);
        }
        //校验验证码是否匹配
        String key = "UPDATE_PHONE_" + newPhone;
        String code = redisClusterService.get(key);
        if (StringUtils.isBlank(code)) {
            return Result.result(ResultCode.CODE_LOSE_EFFICACY);
        }
        if (!smsCode.equalsIgnoreCase(code)) {
            return Result.result(ResultCode.CODE_ERR);
        }
        Boolean bool=userService.changePhone(getSession(),newPhone);
        if (bool) {
            return Result.success();
        }
        return Result.fail();
    }

    @ApiOperation(value = "查询所有用户")
    @GetMapping(value = "/getAllUser")
    public Result<List<UserGroup>> getAllUser(){
        List<UserGroup> userTree=userService.getUserTree();
        return Result.success(userTree);
    }


}
