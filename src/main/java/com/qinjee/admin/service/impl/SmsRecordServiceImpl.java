package com.qinjee.admin.service.impl;

import com.qinjee.admin.config.SmsConfig;
import com.qinjee.admin.config.redis.RedisClusterService;
import com.qinjee.admin.mapper.SmsConfigMapper;
import com.qinjee.admin.service.SmsRecordService;
import com.qinjee.admin.utils.KeyUtils;
import com.qinjee.admin.utils.SendMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author penghs
 * @since 2020-05-09
 */
@Service
public class SmsRecordServiceImpl implements SmsRecordService {
    //短信模板
    private static final String BUSINESS_TYPE_LOGIN_CODE = "LOGIN_CODE";
    private static final String BUSINESS_TYPE_REGIST_CODE = "REGIST_CODE";
    private static final String BUSINESS_TYPE_WECHAT_BIND_CODE = "WECHAT_BIND_CODE";
    private static final String BUSINESS_TYPE_FORGET_PASSWORD_CODE = "FORGET_PASSWORD_CODE";
    private static final String BUSINESS_TYPE_ENTRY_REGISTRATION = "ENTRY_REGISTRATION";
    private static final String BUSINESS_TYPE_PRE_LOGIN_CODE = "PRE_LOGIN_CODE";

    private static final int SMS_CODE_VALID_MINUTE = 5;

    @Autowired
    private SmsConfigMapper smsConfigMapper;

    @Autowired
    private RedisClusterService redisClusterService;
    @Override
    public void sendUpdatePhoneCode(String phone) {
        SmsConfig smsConfig = smsConfigMapper.getByBusinessType(BUSINESS_TYPE_FORGET_PASSWORD_CODE);
        /**
         * 生成6位随机数字验证码
         */
        String smsCode = KeyUtils.getNonceCodeNumber(6);
        String[] params={smsCode,String.valueOf(SMS_CODE_VALID_MINUTE)};
        redisClusterService.setex("UPDATE_PHONE_" + phone,SMS_CODE_VALID_MINUTE * 60, smsCode);

        SendMessageUtil.sendSingleMessage(smsConfig.getAppId(),smsConfig.getAppKey(),smsConfig.getTemplateId(),smsConfig.getSmsSign(),phone,params);
    }

    @Override
    public void sendLoginCode(String phone) {

        SmsConfig smsConfig = smsConfigMapper.getByBusinessType(BUSINESS_TYPE_LOGIN_CODE);
        /**
         * 生成6位随机数字验证码
         */
        String smsCode = KeyUtils.getNonceCodeNumber(6);
        String[] params={smsCode,String.valueOf(SMS_CODE_VALID_MINUTE)};
        redisClusterService.setex("LOGIN_" + phone,SMS_CODE_VALID_MINUTE * 60, smsCode);

        SendMessageUtil.sendSingleMessage(smsConfig.getAppId(),smsConfig.getAppKey(),smsConfig.getTemplateId(),smsConfig.getSmsSign(),phone,params);

        //添加短信记录
        //insertSmsRecord(smsConfig,phone,params);


    }

    @Override
    public void sendUpdatePwdCode(String phone) {

        SmsConfig smsConfig = smsConfigMapper.getByBusinessType(BUSINESS_TYPE_FORGET_PASSWORD_CODE);
        /**
         * 生成6位随机数字验证码
         */
        String smsCode = KeyUtils.getNonceCodeNumber(6);
        String[] params={smsCode,String.valueOf(SMS_CODE_VALID_MINUTE)};
        redisClusterService.setex("UPDATE_PWD_" + phone,SMS_CODE_VALID_MINUTE * 60, smsCode);

        SendMessageUtil.sendSingleMessage(smsConfig.getAppId(),smsConfig.getAppKey(),smsConfig.getTemplateId(),smsConfig.getSmsSign(),phone,params);

    }



}
