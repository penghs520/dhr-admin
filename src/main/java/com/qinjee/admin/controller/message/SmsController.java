package com.qinjee.admin.controller.message;

import com.qinjee.admin.config.redis.RedisClusterService;
import com.qinjee.admin.model.Result;
import com.qinjee.admin.model.ResultCode;
import com.qinjee.admin.service.SmsRecordService;
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

    @Autowired
    private SmsRecordService smsRecordService;

    @ApiOperation(value = "发送登录验证码")
    @GetMapping(value = "/sendLoginCode")
    public Result sendLoginCode(String phone) {
        //TODO 异步发送短信 后面采用线程池代替
        smsRecordService.sendLoginCode(phone);
        return Result.result(200, "已发送");
    }

    @ApiOperation(value = "发送修改密码验证码")
    @GetMapping(value = "/sendUpdatePwdCode")
    public Result sendUpdatePwdCode(String phone) {
        smsRecordService.sendUpdatePwdCode(phone);
        return Result.result(200, "已发送");
    }

    @ApiOperation(value = "发送修改绑定手机验证码")
    @GetMapping(value = "/sendUpdatePhoneCode")
    public Result sendUpdatePhoneCode(String phone) {
        smsRecordService.sendUpdatePhoneCode(phone);
        return Result.result(200, "已发送");
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
