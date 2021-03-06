package com.qinjee.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qinjee.admin.entity.Company;
import com.qinjee.admin.entity.User;
import com.qinjee.admin.mapper.CompanyMapper;
import com.qinjee.admin.mapper.OrganizationMapper;
import com.qinjee.admin.mapper.UserMapper;
import com.qinjee.admin.model.PageResult;
import com.qinjee.admin.model.ao.CompanyFollowerAo;
import com.qinjee.admin.model.ao.CompanyPageAo;
import com.qinjee.admin.model.vo.CompanyInfoVo;
import com.qinjee.admin.model.vo.CompanyListVo;
import com.qinjee.admin.model.vo.OrganizationVo;
import com.qinjee.admin.model.vo.UserInfoVo;
import com.qinjee.admin.service.ICompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public PageResult<CompanyListVo> list(CompanyPageAo pageAo) {
        if (pageAo.getCurrentPage() != null && pageAo.getPageSize() != null) {
            PageHelper.startPage(pageAo.getCurrentPage(), pageAo.getPageSize());
        }
        List<CompanyListVo> companyListVos = companyMapper.selectPage(pageAo);
        PageInfo<CompanyListVo> pageInfo = new PageInfo<>(companyListVos);
        PageResult<CompanyListVo> pageResult = new PageResult<>();
        pageResult.setTotal(pageInfo.getTotal());
        //TODO 暂时用笨方式
        for (CompanyListVo vo : companyListVos) {
            int count = companyMapper.getArchiveCount(vo.getCompanyId());
            vo.setStaffCount(count);
        }

        pageResult.setList(companyListVos);
        return pageResult;
    }

    @Override
    public CompanyInfoVo getInfo(Integer companyId) {
        CompanyInfoVo companyInfoVo = companyMapper.selectById(companyId);
        //查询企业跟进者
        List<UserInfoVo> followers = companyMapper.selectFollowers(companyId);
        companyInfoVo.setFollowers(followers);

        //查询认证者
        if (companyInfoVo.getAuthenticatorId() != null) {
            User user = new User();
            user.setUserId(companyInfoVo.getAuthenticatorId());
            User user1 = userMapper.selectByUser(user);
            if (user1 != null) {
                companyInfoVo.setAuthenticator(user1.getUserName());
            }
        }
        return companyInfoVo;
    }

    @Override
    public CompanyInfoVo getById(Integer companyId) {
        return companyMapper.selectById(companyId);
    }

    @Override
    @Transactional
    public Boolean authenticate(Integer companyId, Integer authStatus, Integer operatorId) {
        CompanyInfoVo companyInfoVo = companyMapper.selectById(companyId);
        Company company = new Company();

        company.setAuthenticatorId(operatorId);
        company.setAuthStatus(authStatus);
        company.setCompanyId(companyId);
        //当通过认证时，同步设置机构名称和企业名称
        if(authStatus==2){

            company.setCompanyName(companyInfoVo.getRegistCompanyName());

            OrganizationVo topOrganization = organizationMapper.getTop(companyId);
            topOrganization.setOrgName(companyInfoVo.getRegistCompanyName());
            organizationMapper.update(topOrganization);
        }


        int i = companyMapper.updateById(company);



        return i > 0 ? true : false;
    }


    @Override
    @Transactional
    public Boolean addFollower(CompanyFollowerAo companyFollowerAo) {
        int i = companyMapper.deleteFollower(companyFollowerAo.getCompanyIds());
        List<Integer> companyIds = companyFollowerAo.getCompanyIds();
        List<Integer> followerIds = companyFollowerAo.getFollowerIds();
        //TODO 更换为批量插入
        for (Integer companyId : companyIds) {
            for (Integer followerId : followerIds) {
                int j = companyMapper.addFollower(companyId, followerId);
            }
        }
        return true;
    }

}
