package com.qinjee.admin.entity.company;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 企业信息表
 * </p>
 *
 * @author penghs
 * @since 2020-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 企业ID
     */
    private Integer companyId;

    /**
     * 企业名称
     */
    private String companyName;

    /**
     * 企业类型
     */
    private String companyType;

    /**
     * 注册人userID
     */
    private Integer registUserId;

    /**
     * 营业执照ID
     */
    private String business_license_url;

    /*
     * 营业执照上传者
     */
    private Integer businessLicenseUploader;

    /*
     * 营业执照上传时间
     */
    private LocalDateTime businessLicenseUploadTime;


    /**
     * 注册地址
     */
    private String registAddress;

    /**
     * 办公地址
     */
    private String officeAddress;

    /**
     * 欢迎页标题
     */
    private String welcomeTitle;

    /**
     * 欢迎语
     */
    private String welcomeContent;

    /**
     * logo图路径
     */
    private String logoImgUrl;

    /**
     * 背景图路径
     */
    private String backgroundImgUrl;

    /**
     * 企业规模
     */
    private Integer userNumber;

    /**
     * 员工人数
     */
    private Integer staffCount;

    /**
     * 管理员账号数
     */
    private Integer accountCount;

    /**
     * 管理员已使用账号数
     */
    private Integer accountUsed;

    /**
     * 版本：trial/official
     */
    private String version;

    /**
     * 有效截止日期
     */
    private LocalDate validEndDate;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 认证状态
     */
    private Integer authStatus;
    /**
     * 认证者
     */
    private Integer authenticatorId;

    /**
     * 是否SaaS
     */
    private Integer isSaas;

    /**
     * 是否启用
     */
    private Integer isEnable;


}
