package com.qinjee.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qinjee.admin.mapper.OrderRecordMapper;
import com.qinjee.admin.model.PageResult;
import com.qinjee.admin.model.ao.OrderRecordPageAo;
import com.qinjee.admin.model.vo.OrderRecordVo;
import com.qinjee.admin.service.OrderRecordService;
import org.springframework.stereotype.Service;

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
}
