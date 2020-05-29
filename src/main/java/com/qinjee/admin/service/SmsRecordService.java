/**
 * 文件名：SmsRecordService
 * 工程名称：eTalent
 * <p>
 * qinjee
 * 创建日期：2019/9/16
 * <p>
 * Copyright(C) 2019, by zhouyun
 * 原始作者：周赟
 */
package com.qinjee.admin.service;


public interface SmsRecordService {


    void sendUpdatePhoneCode(String phone);

    void sendLoginCode(String phone);

    void sendUpdatePwdCode(String phone);




}
