package com.qinjee.admin.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class TicketSendInfo implements Serializable {
    @ApiModelProperty(notes = "收件人姓名")
    private String receiveName;
    @ApiModelProperty(notes = "收件人手机号")
    private String receivePhone;
    @ApiModelProperty(notes = "收件地址")
    private String receiveAddress;
    @ApiModelProperty(notes = "发票寄送状态")
    private Integer ticketSendStatus;
    @ApiModelProperty(notes = "寄送快递单号")
    private String expressNumber;
}
