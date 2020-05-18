package com.qinjee.admin.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderRecordVo implements Serializable {

    private String companyName;

    private Date buyDate;

    private String ticketNumber;//发票号码

    private Integer orderId;

    private String goodName;

    private String payer_name;//付款方

    private String buyUserId;//下单人，对应订单表的operator_id

    private String phone;

    private Integer payType;//支付方式 0：支付宝，1：微信，1：银行卡

    private Double totalPrice;

    private Integer ticket_status;//开票状态













}
