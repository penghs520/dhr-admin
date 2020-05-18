package com.qinjee.admin.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CompanyListVo implements Serializable {

    @ApiModelProperty("企业ID")
    private Integer companyId;

    @ApiModelProperty("企业名称")
    private String companyName;

    /**
     * 版本：trial/official
     */
    @ApiModelProperty("版本：trial（试用版）/official（正式版）")
    private String version;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    /**
     * 企业注册人id
     */
    @ApiModelProperty("企业注册人id")
    private String registUserId;
    /**
     * 企业注册人姓名
     */
    @ApiModelProperty("企业注册人姓名")
    private String registUserName;



    @ApiModelProperty("企业注册人手机号")
    private String registUserPhone;

    /**
     * 行业
     */
    @ApiModelProperty("行业类型")
    private String companyType;

    /**
     * 人员规模
     */
    @ApiModelProperty("人员规模")
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
     * 管理员已使用账号数
     */
    @ApiModelProperty("已使用管理员账号数")
    private Integer accountUsed;

    /**
     * 注册地址
     */
    @ApiModelProperty("注册地址")
    private String registAddress;

    /**
     * 认证状态 0 未认证 1 认证中 2 已认证 3 认证失败
     */
    @ApiModelProperty("认证状态，0 未认证 1 认证中 2 已认证 3 认证失败")
    private Integer authStatus;


}
