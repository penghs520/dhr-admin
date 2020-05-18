package com.qinjee.admin.model.ao;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class CompanyPageAo implements Serializable {
    @ApiModelProperty(notes = "当前页",example = "1")
    private Integer currentPage;

    @ApiModelProperty(notes = "页面大小",example = "10")
    private Integer pageSize;

    private Query3 query;

    @Data
    class Query3{
        @ApiModelProperty(notes = "企业名称")
        private String companyName;

        @ApiModelProperty(notes = "版本：待定",example = "待定")
        private String version;

        @ApiModelProperty(notes = "创建时间1")
        private Date createTime_1;

        @ApiModelProperty(notes = "创建时间2")
        private Date createTime_2;

        @ApiModelProperty(notes = "企业类型",example = "A")
        private String companyType;

        @ApiModelProperty(notes = "创建人姓名",example = "1")
        private String registUserName;

        @ApiModelProperty(notes = "人员规模",example = "499")
        private Integer userNumber;

        @ApiModelProperty(notes = "员工人数",example = "50")
        private Integer staffCount;

        @ApiModelProperty(notes = "管理员账号数",example = "10")
        private Integer accountCount;
        @ApiModelProperty("注册地址")
        private String registAddress;

        @ApiModelProperty(notes = "认证状态 0 未认证 1 认证中 2 已认证 3 认证失败",example = "1")
        private Integer authStatus;
    }


}
