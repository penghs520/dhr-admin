package com.qinjee.admin.controller.company;


import com.qinjee.admin.model.PageResult;
import com.qinjee.admin.model.Result;
import com.qinjee.admin.model.ao.OrderRecordPageAo;
import com.qinjee.admin.model.vo.OrderRecordVo;
import com.qinjee.admin.service.OrderRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  发票管理
 * </p>
 *
 * @author penghs
 * @since 2020-05-09
 */
@RestController
@RequestMapping("/admin/order_record")
@Api(tags = "【企业管理|购买记录】")
public class OrderController {

    @Autowired
    private OrderRecordService orderRecordService;

    @ApiOperation(value = "购买记录|发票列表")
    @PostMapping(value = "/list")
    public Result<PageResult<OrderRecordVo>> list(@RequestBody OrderRecordPageAo pageAo) {
       PageResult<OrderRecordVo> list = orderRecordService.list(pageAo);

        return Result.success(list);
    }
    @ApiOperation(value = "寄送发票")
    @GetMapping(value = "/sendTicket")
    public Result sendTicket(Integer ticketId,String expressNumber){
        return null;
    }
    @ApiOperation(value = "确认开票")
    @PostMapping(value = "/opneTicket")
    public Result opneTicket(@RequestBody Integer[] ticketId){
        return null;
    }

}
