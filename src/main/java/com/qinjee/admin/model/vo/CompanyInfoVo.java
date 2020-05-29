package com.qinjee.admin.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CompanyInfoVo implements Serializable {

    private String companyName;

    private String registUserName;

    private String authenticator;

    private Integer authenticatorId;

    private String registUserPhone;

    private Integer registUserId;

    private String version;

    private String createTime;

    private String companyType;

    private Integer userScale;

    /**
     * 跟进人
     */
    @ApiModelProperty("跟进人")
    private List<UserInfoVo> followers;

    /**
     * 管理员账号数
     */
    @ApiModelProperty("管理员账号数")
    private Integer accountCount;

    /**
     * 员工总数
     */
    @ApiModelProperty("员工总数")
    private Integer staffCount;

    /**
     * 注册地址
     */
    @ApiModelProperty("注册地址")
    private String registAddress;

    /**
     * 认证状态 0 未认证 1 认证中 2 已认证 3 认证失败
     */
    @ApiModelProperty("认证状态，0 未认证  1 已认证 2 认证失败")
    private Integer authStatus;

    /**
     * 营业执照ID
     */
    private String businessLicenseUrl;

}
