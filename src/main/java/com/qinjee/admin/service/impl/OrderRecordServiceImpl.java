package com.qinjee.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qinjee.admin.mapper.OrderRecordMapper;
import com.qinjee.admin.model.PageResult;
import com.qinjee.admin.model.ao.OrderRecordPageAo;
import com.qinjee.admin.model.vo.OrderRecordVo;
import com.qinjee.admin.model.vo.TicketInfo;
import com.qinjee.admin.service.OrderRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderRecordServiceImpl implements OrderRecordService {

    @Resource
    private OrderRecordMapper orderRecordMapper;
    @Override
    public PageResult<OrderRecordVo> list(OrderRecordPageAo pageAo) {
        if (pageAo.getCurrentPage() != null && pageAo.getPageSize() != null) {
            PageHelper.startPage(pageAo.getCurrentPage(), pageAo.getPageSize());
        }
        List<OrderRecordVo> orderRecordVos = orderRecordMapper.selectPage(pageAo);
        PageInfo<OrderRecordVo> pageInfo = new PageInfo<>(orderRecordVos);
        PageResult<OrderRecordVo> pageResult = new PageResult<>();
        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setList(orderRecordVos);
        return pageResult;
    }

    @Override
    public Boolean opneTicket(Integer[] ticketIds) {
        int i=orderRecordMapper.opneTicket(ticketIds);
        if(i==ticketIds.length){
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean sendTicket(Integer ticketId, String expressNumber) {
        int i=orderRecordMapper.sendTicket(ticketId,expressNumber);
        if(i>0){
            //TODO 公众号通知
            return true;
        }
        return false;
    }


}
