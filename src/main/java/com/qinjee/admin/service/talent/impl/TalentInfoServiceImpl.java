package com.qinjee.admin.service.talent.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qinjee.admin.mapper.talent.TalentInfoMapper;
import com.qinjee.admin.model.PageResult;
import com.qinjee.admin.model.ao.TalentInfoPageAo;
import com.qinjee.admin.model.vo.TalentInfoVo;
import com.qinjee.admin.service.talent.ITalentInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TalentInfoServiceImpl implements ITalentInfoService {

    @Resource
    private TalentInfoMapper talentInfoMapper;


    @Override
    public PageResult<TalentInfoVo> list(TalentInfoPageAo pageAo) {
        if (pageAo.getCurrentPage() != null && pageAo.getPageSize() != null) {
            PageHelper.startPage(pageAo.getCurrentPage(), pageAo.getPageSize());
        }
        List<TalentInfoVo> userList= talentInfoMapper.selectPage(pageAo);
        PageInfo<TalentInfoVo> pageInfo = new PageInfo<>(userList);
        PageResult<TalentInfoVo> pageResult = new PageResult<>();
        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setList(userList);
        return pageResult;
    }
}
