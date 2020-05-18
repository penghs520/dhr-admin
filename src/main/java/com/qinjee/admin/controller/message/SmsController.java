package com.qinjee.admin.controller.message;

import com.qinjee.admin.config.redis.RedisClusterService;
import com.qinjee.admin.model.Result;
import com.qinjee.admin.model.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/sms")
@Api(tags = "【短信服务】")
public class SmsController {

    @Autowired
    private RedisClusterService redisClusterService;

    @ApiOperation(value = "发送登录验证码")
    @GetMapping(value = "/sendLoginCode")
    public Result sendLoginCode(String phone) {
        String smsCode = "1234";
        String smsMsg = "您的勤杰账号正在执行登录操作，短信验证码为：" + smsCode + "，一分钟之内有效。[勤杰软件]";
        redisClusterService.setex("LOGIN_" + phone, 60, smsCode);
        return Result.success(smsMsg);
    }

    @ApiOperation(value = "发送修改密码验证码")
    @GetMapping(value = "/sendUpdatePwdCode")
    public Result sendUpdatePwdCode(String phone) {
        String smsCode = "1234";
        String smsMsg = "您的勤杰账号正在执行修改密码操作，短信验证码为：" + smsCode + "，一分钟之内有效。[勤杰软件]";
        redisClusterService.setex("UPDATE_PWD_" + phone, 60, smsCode);
        return Result.success(smsMsg);
    }

    @ApiOperation(value = "发送修改绑定手机验证码")
    @GetMapping(value = "/sendUpdatePhoneCode")
    public Result sendUpdatePhoneCode(String phone) {
        String smsCode = "1234";
        String smsMsg = "您的勤杰账号正在执行修改绑定手机操作，短信验证码为：" + smsCode + "，一分钟之内有效。[勤杰软件]";
        redisClusterService.setex("UPDATE_PHONE_" + phone, 60, smsCode);
        return Result.success(smsMsg);
    }

    @ApiOperation(value = "【校验】修改绑定手机验证码")
    @GetMapping(value = "/checkUpdatePhoneCode")
    public Result checkUpdatePhoneCode(String phone, String smsCode) {
        if (phone == null) {
            return Result.result(ResultCode.PHONE_NOT_BE_NULL);
        }
        if (smsCode == null) {
            return Result.result(ResultCode.CODE_NOT_BE_NULL);
        }

        String key = "UPDATE_PHONE_" + phone;
        String redisSmsCode = redisClusterService.get(key);
        if (StringUtils.isBlank(redisSmsCode)) {
            return Result.result(ResultCode.CODE_LOSE_EFFICACY);
        }
        if (!smsCode.equalsIgnoreCase(redisSmsCode)) {
            return Result.result(ResultCode.CODE_ERR);
        }
        redisClusterService.del(key);
        return Result.success();
    }
    @ApiOperation(value = "【校验】修改密码验证码")
    @GetMapping(value = "/checkUpdatePwdCode")
    public Result checkUpdatePwdCode(String phone, String smsCode) {
        if (phone == null) {
            return Result.result(ResultCode.PHONE_NOT_BE_NULL);
        }
        if (smsCode == null) {
            return Result.result(ResultCode.CODE_NOT_BE_NULL);
        }

        String key = "UPDATE_PWD_" + phone;
        String redisSmsCode = redisClusterService.get(key);
        if (StringUtils.isBlank(redisSmsCode)) {
            return Result.result(ResultCode.CODE_LOSE_EFFICACY);
        }
        if (!smsCode.equalsIgnoreCase(redisSmsCode)) {
            return Result.result(ResultCode.CODE_ERR);
        }
        redisClusterService.del(key);
        return Result.success();
    }

}
