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

    private CompanyPageQuery query;

    @Data
    class CompanyPageQuery{
        @ApiModelProperty(notes = "企业名称")
        private String companyName;


        @ApiModelProperty(notes = "创建时间1")
        private String createTime_1;

        @ApiModelProperty(notes = "创建时间2")
        private String createTime_2;

        @ApiModelProperty(notes = "企业类型",example = "A")
        private String companyType;

        @ApiModelProperty(notes = "创建人姓名",example = "phs")
        private String registUserName;

        @ApiModelProperty(notes = "人员规模",example = "499")
        private String userNumber;

        @ApiModelProperty(notes = "员工人数",example = "50")
        private String staffCount_1;

        private String staffCount_2;

        @ApiModelProperty(notes = "管理员账号数",example = "10")
        private String accountCount_1;

        private String accountCount_2;
        @ApiModelProperty("注册地址")
        private String registAddress;

        @ApiModelProperty(notes = "认证状态 0 未认证 1  已认证 2 认证失败",example = "1")
        private Integer authStatus;
    }


}
