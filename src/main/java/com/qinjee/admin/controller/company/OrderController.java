package com.qinjee.admin.controller.company;


import com.qinjee.admin.model.PageResult;
import com.qinjee.admin.model.Result;
import com.qinjee.admin.model.ao.CompanyPageAo;
import com.qinjee.admin.model.vo.CompanyListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation(value = "购买记录")
    @PostMapping(value = "/list")
    public Result<PageResult<CompanyListVo>> list(@RequestBody CompanyPageAo companyPageAo) {
        /*PageResult<CompanyListVo> list = companyInfoService.list(companyPageAo);

        return Result.success(list);*/
        return null;
    }
}
