package com.qinjee.admin.mapper;

import com.qinjee.admin.model.ao.OrderRecordPageAo;
import com.qinjee.admin.model.vo.OrderRecordVo;
import com.qinjee.admin.model.vo.TicketInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderRecordMapper {

    List<OrderRecordVo> selectPage(OrderRecordPageAo pageAo);

    int opneTicket(@Param("ticketIds") Integer[] ticketIds);

    int sendTicket(@Param("ticketId") Integer ticketId, @Param("expressNumber") String expressNumber);
}
