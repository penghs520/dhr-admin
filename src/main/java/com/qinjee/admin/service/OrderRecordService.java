package com.qinjee.admin.service;

import com.qinjee.admin.model.PageResult;
import com.qinjee.admin.model.ao.OrderRecordPageAo;
import com.qinjee.admin.model.vo.OrderRecordVo;

public interface OrderRecordService {

    PageResult<OrderRecordVo> list(OrderRecordPageAo pageAo);
}
