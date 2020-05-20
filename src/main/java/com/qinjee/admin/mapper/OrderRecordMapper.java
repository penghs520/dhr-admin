package com.qinjee.admin.mapper;

import com.qinjee.admin.model.ao.OrderRecordPageAo;
import com.qinjee.admin.model.vo.OrderRecordVo;

import java.util.List;

public interface OrderRecordMapper {

    List<OrderRecordVo> selectPage(OrderRecordPageAo pageAo);
}
