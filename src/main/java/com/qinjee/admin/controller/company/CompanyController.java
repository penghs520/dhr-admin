package com.qinjee.admin.controller.company;


import com.qinjee.admin.controller.BaseController;
import com.qinjee.admin.model.PageResult;
import com.qinjee.admin.model.Result;
import com.qinjee.admin.model.UserSession;
import com.qinjee.admin.model.ao.CompanyFollowerAo;
import com.qinjee.admin.model.ao.CompanyPageAo;
import com.qinjee.admin.model.vo.CompanyInfoVo;
import com.qinjee.admin.model.vo.CompanyListVo;
import com.qinjee.admin.service.ICompanyInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 企业管理
 * </p>
 *
 * @author penghs
 * @since 2020-05-09
 */
@RestController
@RequestMapping("/admin/company")
@Api(tags = "【企业管理|企业信息】")
public class CompanyController extends BaseController {

    @Autowired
    private ICompanyInfoService companyInfoService;

    @ApiOperation(value = "查询企业列表")
    @PostMapping(value = "/list")
    public Result<PageResult<CompanyListVo>> list(@RequestBody  CompanyPageAo companyPageAo) {
       PageResult<CompanyListVo> list = companyInfoService.list(companyPageAo);
        return Result.success(list);
    }

    @ApiOperation(value = "认证")
    @GetMapping(value = "/authenticate")
    public Result authenticate(Integer companyId, Integer authStatus) {
        UserSession session = getSession();
        Boolean bool=companyInfoService.authenticate(companyId,authStatus,session.getUserId());
        if (bool){
            return Result.success();
        }
        return Result.fail();
    }

    @ApiOperation(value = "企业信息|认证进度")
    @GetMapping(value = "/getInfo")
    public Result getInfo(Integer companyId) {
        CompanyInfoVo companyInfo=companyInfoService.getInfo(companyId);
        return Result.success(companyInfo);
    }

    @ApiOperation(value = "添加跟进人")
    @PostMapping(value = "/addFollower")
    public Result addFollower(@RequestBody CompanyFollowerAo companyFollowerAo) {
        Boolean bool=companyInfoService.addFollower(companyFollowerAo);
        if (bool){
            return Result.success();
        }
        return Result.fail();
    }


}
