package com.qinjee.admin.mapper.company;

import com.qinjee.admin.entity.company.Company;
import com.qinjee.admin.model.ao.CompanyPageAo;
import com.qinjee.admin.model.vo.CompanyInfoVo;
import com.qinjee.admin.model.vo.CompanyListVo;
import com.qinjee.admin.model.vo.UserInfoVo;

import java.util.List;

/**
 * <p>
 * 企业信息表 Mapper 接口
 * </p>
 *
 * @author penghs
 * @since 2020-05-09
 */
public interface CompanyMapper {


    List<CompanyListVo> selectPage(CompanyPageAo pageAo);

    CompanyInfoVo selectById(Integer companyId);

    List<UserInfoVo> selectFollowers(Integer companyId);

    int updateById(Company company);
}
