package com.qinjee.admin.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderRecordVo implements Serializable {
    @ApiModelProperty(notes = "企业名称")
    private String companyName;
    @ApiModelProperty(notes = "购买时间")
    private Date buyDate;
    @ApiModelProperty(notes = "发票税号")
    private String ticketNumber;//发票号码
    @ApiModelProperty(notes = "发票id")
    private String ticketId;//发票号码
    @ApiModelProperty(notes = "开票时间")
    private Date ticketOpenTime;
    @ApiModelProperty(notes = "开票状态 0未开票 1已开票")
    private Integer ticketOpenStatus;
    @ApiModelProperty(notes = "发票寄送状态 0未寄送 1已寄送")
    private Integer ticketSendStatus;
    @ApiModelProperty(notes = "寄送快递单号")
    private String expressNumber;
    @ApiModelProperty(notes = "订单号")
    private String orderNumber;
    @ApiModelProperty(notes = "商品名称")
    private String goodName;

    @ApiModelProperty(notes = "下单人id")
    private String buyUserId;//下单人，对应订单表的operator_id
    @ApiModelProperty(notes = "下单人姓名")
    private String buyUserName;//下单人，对应订单表的operator_id
    @ApiModelProperty(notes = "下单人手机号")
    private String phone;
    @ApiModelProperty(notes = "支付方式 0：支付宝，1：微信，1：银行卡")
    private Integer payType;//支付方式 0：支付宝，1：微信，1：银行卡
    @ApiModelProperty(notes = "订单金额")
    private Double totalPrice;

    @ApiModelProperty(notes = "发票信息对象")
    private TicketInfo ticketInfo;//发票信息
    @ApiModelProperty(notes = "发票寄送信息对象")
    private TicketSendInfo ticketSendInfo;//发票信息


}
