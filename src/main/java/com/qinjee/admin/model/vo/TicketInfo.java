package com.qinjee.admin.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TicketInfo implements Serializable {
    @ApiModelProperty(notes = "发票类型 0 1")
    private Integer ticketType;
    @ApiModelProperty(notes = "发票金额")
    private Double ticketPrice;
    @ApiModelProperty(notes = "开票状态")
    private Integer ticketOpenStatus;
    @ApiModelProperty(notes = "开票企业单位名称")
    private String ticketCompanyName;
    @ApiModelProperty(notes = "发票税号")
    private String ticketNumber;
    @ApiModelProperty(notes = "开户行名称")
    private String bankName;
    @ApiModelProperty(notes = "开户行银行卡号")
    private String bankAccount;
    @ApiModelProperty(notes = "联系人姓名")
    private String contactPersonName;
    @ApiModelProperty(notes = "联系人手机号")
    private String contactPhone;
    @ApiModelProperty(notes = "发票地址")
    private String ticketAddress;
    @ApiModelProperty(notes = "开票时间")
    private Date ticketOpenTime;
}
