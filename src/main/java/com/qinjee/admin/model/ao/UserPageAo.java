package com.qinjee.admin.model.ao;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserPageAo implements Serializable {

    @ApiModelProperty(notes = "当前页", example = "1")
    private Integer currentPage;

    @ApiModelProperty(notes = "页面大小", example = "10")
    private Integer pageSize;


    private Query query;

    @Data
    private class Query{
        @ApiModelProperty(notes = "所属分组id", example = "1")
        private Integer userGroupId;

        @ApiModelProperty(notes = "姓名", example = "张三")
        private String userName;

        @ApiModelProperty(notes = "手机", example = "1555551111")
        private String phone;

        @ApiModelProperty(notes = "邮箱", example = "4545@163.com")
        private String email;
    }

}
