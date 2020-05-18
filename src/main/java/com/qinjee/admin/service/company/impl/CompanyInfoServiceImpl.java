package com.qinjee.admin.service.company.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qinjee.admin.entity.company.Company;
import com.qinjee.admin.entity.user.User;
import com.qinjee.admin.mapper.company.CompanyMapper;
import com.qinjee.admin.mapper.user.UserMapper;
import com.qinjee.admin.model.PageResult;
import com.qinjee.admin.model.ao.CompanyPageAo;
import com.qinjee.admin.model.vo.CompanyInfoVo;
import com.qinjee.admin.model.vo.CompanyListVo;
import com.qinjee.admin.model.vo.UserInfoVo;
import com.qinjee.admin.service.company.ICompanyInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 企业信息表 服务实现类
 * </p>
 *
 * @author penghs
 * @since 2020-05-09
 */
@Service
public class CompanyInfoServiceImpl implements ICompanyInfoService {

    @Resource
    private CompanyMapper companyMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public PageResult<CompanyListVo> list(CompanyPageAo pageAo) {
        if (pageAo.getCurrentPage() != null && pageAo.getPageSize() != null) {
            PageHelper.startPage(pageAo.getCurrentPage(), pageAo.getPageSize());
        }
        List<CompanyListVo> companyListVos = companyMapper.selectPage(pageAo);
        PageInfo<CompanyListVo> pageInfo = new PageInfo<>(companyListVos);
        PageResult<CompanyListVo> pageResult = new PageResult<>();
        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setList(companyListVos);
        return pageResult;
    }

    @Override
    public CompanyInfoVo getInfo(Integer companyId) {
        CompanyInfoVo companyInfoVo = companyMapper.selectById(companyId);
        //查询企业跟进者
        List<UserInfoVo> followers=companyMapper.selectFollowers(companyId);
        companyInfoVo.setFollowers(followers);

        //查询认证者
        if(companyInfoVo.getAuthenticatorId()!=null){
            User user = new User();
            user.setUserId(companyInfoVo.getAuthenticatorId());
            User user1 = userMapper.selectByUser(user);
            if(user1!=null){
                companyInfoVo.setAuthenticator(user1.getUserName());
            }
        }
        return companyInfoVo;
    }

    @Override
    public Boolean authenticate(Integer companyId, Integer authStatus,Integer operatorId) {
        Company company = new Company();
        company.setAuthenticatorId(operatorId);
        company.setAuthStatus(authStatus);
        int i=companyMapper.updateById(company);
        if (i>0){
            return true;
        }
        return false;
    }

}
