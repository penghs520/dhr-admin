package com.qinjee.admin.controller.talent;


import com.qinjee.admin.model.PageResult;
import com.qinjee.admin.model.Result;
import com.qinjee.admin.model.ao.TalentInfoPageAo;
import com.qinjee.admin.model.vo.TalentInfoVo;
import com.qinjee.admin.service.ITalentInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  人才管理
 * </p>
 *
 * @author penghs
 * @since 2020-05-09
 */
@RestController
@RequestMapping("/admin/talents_info")
@Api(tags = "【人才管理-人才信息】")
public class TalentsInfoController {

    @Autowired
    private ITalentInfoService talentInfoService;

    @ApiOperation(value = "人才信息列表")
    @PostMapping(value = "/list")
    public Result<PageResult<TalentInfoVo>> list(@RequestBody TalentInfoPageAo talentInfoPageAo){
        PageResult<TalentInfoVo> iPage = talentInfoService.list(talentInfoPageAo);
        return Result.success(iPage);
    }

}
