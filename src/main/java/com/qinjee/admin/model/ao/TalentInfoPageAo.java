package com.qinjee.admin.model.ao;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class TalentInfoPageAo implements Serializable {

    @ApiModelProperty(notes = "当前页",example = "0")
    private Integer currentPage;

    @ApiModelProperty(notes = "页大小",example = "10")
    private Integer pageSize;

    @ApiModelProperty(notes = "查询条件对象")
    private Query2 query;

    @Data
    private class Query2{//TODO 内部类名字不能一样，我醉了
        @ApiModelProperty(notes = "企业名称")
        private String companyName;

        @ApiModelProperty(notes = "姓名")
        private String userName;

        @ApiModelProperty(notes = "性别")
        private String gender;

        @ApiModelProperty(notes = "手机")
        private String phone;

        @ApiModelProperty(notes = "邮箱")
        private String email;

        @ApiModelProperty(notes = "人才ID")
        private String archiveId;//TODO 数据库人才ID，待定
    }



}
