package com.qinjee.admin.service;

import com.qinjee.admin.model.PageResult;
import com.qinjee.admin.model.ao.CompanyFollowerAo;
import com.qinjee.admin.model.ao.CompanyPageAo;
import com.qinjee.admin.model.vo.CompanyInfoVo;
import com.qinjee.admin.model.vo.CompanyListVo;

import java.util.List;

/**
 * <p>
 * 企业信息表 服务类
 * </p>
 *
 * @author penghs
 * @since 2020-05-09
 */
public interface ICompanyInfoService {

   PageResult<CompanyListVo> list(CompanyPageAo companyPageAo);

    CompanyInfoVo getInfo(Integer companyId);

    CompanyInfoVo getById(Integer companyId);

    Boolean authenticate(Integer companyId, Integer authStatus,Integer operatorId);

    Boolean addFollower(CompanyFollowerAo companyFollowerAo);
}
