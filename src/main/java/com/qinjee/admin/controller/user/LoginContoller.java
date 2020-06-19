package com.qinjee.admin.controller.user;

import com.alibaba.fastjson.JSON;
import com.qinjee.admin.config.redis.RedisClusterService;
import com.qinjee.admin.entity.User;
import com.qinjee.admin.model.Result;
import com.qinjee.admin.model.ResultCode;
import com.qinjee.admin.model.SystemConstants;
import com.qinjee.admin.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/admin/userlogin")
@RestController
@Api(tags = "【登录服务】")
public class LoginContoller {

    @Autowired
    private IUserService userService;
    @Autowired
    private RedisClusterService redisClusterService;

    @Autowired
    protected HttpServletRequest request;

    @ApiOperation(value = "短信验证码登录")
    @GetMapping(value = "/loginBySmscode")
    public Result loginBySmscode(HttpServletResponse response, String phone, String smsCode) {
        if (phone == null) {
            return Result.result(ResultCode.PHONE_NOT_BE_NULL);
        }
        if (smsCode == null) {
            return Result.result(ResultCode.CODE_NOT_BE_NULL);
        }
        //查询账号是否存在
        User user = userService.getUserByPhone(phone);
        if (user == null) {
            return Result.result(ResultCode.ACCOUNT_NOT_EXSIT);
        }

        //校验验证码是否匹配
        String key = "LOGIN_" + phone;
        String code = redisClusterService.get(key);
        if (StringUtils.isBlank(code)) {
            return Result.result(ResultCode.CODE_LOSE_EFFICACY);
        }
        if (!smsCode.equalsIgnoreCase(code)) {
            return Result.result(ResultCode.CODE_ERR);
        }
        //登录成功后将验证码置为失效
        redisClusterService.del(key);
        //将登录信息存入session
        saveSession(response, user);
        return Result.success();
    }

    private void saveSession(HttpServletResponse response, User user) {
        StringBuffer loginKey = new StringBuffer();
        loginKey.append("DHR_ADMIN_SESSION_KEY").append("_").append(user.getUserId());
        //设置redis登录缓存时间，120分钟过期，与前端保持一致
        redisClusterService.setex(loginKey.toString(), SystemConstants.SESSION_TIME_OUT, JSON.toJSONString(user));
        Cookie cookie = new Cookie("DHR_ADMIN_SESSION_KEY", loginKey.toString());
        cookie.setMaxAge(SystemConstants.SESSION_TIME_OUT);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @ApiOperation(value = "账号密码登录")
    @GetMapping(value = "/loginByPassword")
    public Result loginByPassword(HttpServletResponse response, String phone, String password) {

        if (phone == null) {
            return Result.result(ResultCode.PHONE_NOT_BE_NULL);
        }
        if (password == null) {
            return Result.result(ResultCode.PASSWORD_NOT_BE_NULL);
        }
        //查询账号是否存在
        User user = userService.getUserByPhone(phone);
        if (user == null) {
            return Result.result(ResultCode.ACCOUNT_NOT_EXSIT);
        }

        //TODO 将密码加密后进行比较
        User user2 = userService.getUserByPhoneAndPassword(phone,password);

        if(user2 ==null){
            return Result.result(ResultCode.PASSWORD_ERR);
        }
        //将登录信息存入session
        saveSession(response, user);

        return Result.success();
    }


    @ApiOperation(value = "安全退出")//清除session
    @GetMapping(value = "/logout")
    public Result loginOut(HttpServletResponse response) {
        String loginKey = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if ("DHR_ADMIN_SESSION_KEY".equals(cookies[i].getName())) {
                    loginKey = cookies[i].getValue();
                    if (loginKey == null) {
                        return Result.fail("登出失败");
                    }
                    if (redisClusterService.exists(loginKey)) {
                        redisClusterService.del(loginKey);
                    }
                    cookies[i].setValue(null);
                    // 立即销毁cookie
                    cookies[i].setMaxAge(0);
                    cookies[i].setPath("/");
                    response.addCookie(cookies[i]);
                }
            }
        }
        return Result.success();
    }
}

