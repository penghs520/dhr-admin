package com.qinjee.admin.model.ao;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderRecordPageAo implements Serializable {
    @ApiModelProperty(example = "0")
    private Integer currentPage;

    @ApiModelProperty(example = "10")
    private Integer pageSize;

    @ApiModelProperty(notes = "不查询已开票的",example = "true")
    private boolean operation;

    private OrderRecordQuery query;

    @Data
    class  OrderRecordQuery{

        @ApiModelProperty(value = "企业名称",example = "黄开天集团")
        private String companyName;

        @ApiModelProperty(value = "商品名称",example = "222")
        private String goodName;

        @ApiModelProperty(value = "购买时间1")
        private String buyDate_1;

        @ApiModelProperty(value = "购买时间2")
        private String buyDate_2;

        @ApiModelProperty(value = "订单号")
        private String orderNumber;

        @ApiModelProperty(value = "购买人姓名")
        private String buyUserName;//下单人，对应订单表的operator_id

        @ApiModelProperty(value = "手机号（购买人）")
        private String phone;//下单人手机号

        @ApiModelProperty(value = "支付方式,0：支付宝，1：微信，2：银行卡",example = "0")
        private String payType;//支付方式 0：支付宝，1：微信，2：银行卡

        @ApiModelProperty(value = "总金额",example = "0.1")
        private String totalPrice;


        @ApiModelProperty(value = "开票状态",example = "0")
        private String ticketOpenStatus;

        @ApiModelProperty(value = "发票寄送状态",example = "0")
        private String ticketSendStatus;

        @ApiModelProperty(value = "开票时间1")
        private String ticketOpenTime_1;

        @ApiModelProperty(value = "开票时间2")
        private String ticketOpenTime_2;

        @ApiModelProperty(value = "快递单号")
        private String expressNumber;

        @ApiModelProperty(value = "发票号码")
        private String ticketNumber;//发票号码

    }
}
