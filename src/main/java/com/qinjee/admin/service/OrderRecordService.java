package com.qinjee.admin.service;

import com.qinjee.admin.model.PageResult;
import com.qinjee.admin.model.ao.OrderRecordPageAo;
import com.qinjee.admin.model.vo.OrderRecordVo;
import com.qinjee.admin.model.vo.TicketInfo;

public interface OrderRecordService {

    PageResult<OrderRecordVo> list(OrderRecordPageAo pageAo);

    Boolean opneTicket(Integer[] ticketIds);

    Boolean sendTicket(Integer ticketId, String expressNumber);

}
