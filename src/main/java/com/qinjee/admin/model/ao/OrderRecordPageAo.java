package com.qinjee.admin.model.ao;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderRecordPageAo implements Serializable {
    private Integer currentPage;

    private Integer pageSize;
}
