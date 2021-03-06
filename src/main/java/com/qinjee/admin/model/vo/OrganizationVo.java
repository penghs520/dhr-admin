package com.qinjee.admin.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 机构表
 *
 * @author
 */
@Data
@NoArgsConstructor
@ApiModel(description = "机构实体VO类")
@ToString
public class OrganizationVo implements Serializable {

    private boolean checkResult=true;
    private Integer lineNumber;
    private String resultMsg;
    private Short hasRole;

    private Integer hasOrg;

    /**
     * 机构ID
     */
    @ApiModelProperty("机构ID")
    private Integer orgId;

    /**
     * 机构编码
     */
    private String orgCode;
    private String managerHeadImgUrl;

    /**
     * 机构名称
     */
    @ApiModelProperty("机构名称")
    private String orgName;

    /**
     * 机构类型
     */
    @ApiModelProperty("机构类型  GROUP 集团、UNIT 单位、DEPT 部门")
    private String orgType;

    /**
     * 机构父级ID
     */
    @ApiModelProperty("机构父级ID")
    private Integer orgParentId;


    /**
     * 机构父级编码
     */
    @ApiModelProperty("机构父级编码")
    private String orgParentCode;

    /**
     * 机构父级名称
     */
    @ApiModelProperty("机构父级名称")
    private String orgParentName;

    /**
     * 机构负责人工号
     */
    @ApiModelProperty("机构负责人工号")
    private String managerEmployeeNumber;

    /**
     * 机构负责人姓名
     */
    @ApiModelProperty("机构负责人姓名")
    private String orgManagerName;
    /**
     * 机构全称
     */
    @ApiModelProperty("机构全称")
    private String orgFullName;

    /**
     * 机构负责人Id
     */
    @ApiModelProperty("机构负责人Id")
    private Integer orgManagerId;





    /**
     * 企业ID
     */
    @ApiModelProperty("企业ID")
    private Integer companyId;

    /**
     * 排序ID
     */
    @ApiModelProperty("排序ID")
    private Integer sortId;

    /**
     * 操作人ID
     */
    @ApiModelProperty("操作人ID")
    private Integer operatorId;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
    //@JSONField(format = "yyyy-MM-dd HH:mm:ss")//数据库导出页面时json格式化
    private Date createTime;

    /**
     * 是否启用
     */
    @ApiModelProperty("是否启用 0 封存、1 未封存")
    private Short isEnable;


    /**
     * 实有人数
     */

    private Integer staffNumbers;


    private String postName;

    /**
     * 计划编制人数
     */
    private Integer planNumbers;

    /**
     * 图片url
     */
    private String attachmentUrl;


    /**
     * 子机机构
     */
    private List<OrganizationVo> childList;
    private List<OrganizationVo> childOrgList;


    private static final long serialVersionUID = 1L;

    @Override
    public int hashCode() {
        return Objects.hash(orgCode);
    }
}
