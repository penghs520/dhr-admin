package com.qinjee.admin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class SmsRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String phone;

    private String sendMsg;

    private LocalDateTime sendTime;

    private Integer status;

    private String errMsg;


}
