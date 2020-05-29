package com.qinjee.admin.config;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * t_sms_config
 * @author
 */
@Data
public class SmsConfig implements Serializable {
    /**
     * 短信配置ID
     */
    private Integer smsConfigId;

    /**
     * APPID
     */
    private Integer appId;

    /**
     * APPKEY
     */
    private String appKey;

    /**
     * 签名
     */
    private String smsSign;

    /**
     * 模板ID
     */
    private Integer templateId;

    /**
     * 模板内容
     */
    private String templateMsg;

    /**
     * 业务类型
     */
    private String businessType;



    /**
     * 创建时间
     */
    private Date createTime;


}
