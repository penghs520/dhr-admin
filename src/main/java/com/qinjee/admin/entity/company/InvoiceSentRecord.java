package com.qinjee.admin.entity.company;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author penghs
 * @since 2020-05-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class InvoiceSentRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String receiveName;

    private String receivePhone;

    private String receiveAddress;

    private Integer status;

    /**
     * 快递单号
     */
    private String expressNo;


}
