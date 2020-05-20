package com.qinjee.admin.mapper;

import com.qinjee.admin.model.ao.TalentInfoPageAo;
import com.qinjee.admin.model.vo.TalentInfoVo;

import java.util.List;

public interface TalentInfoMapper {

    //TODO 后面要支持多数据源
    List<TalentInfoVo> selectPage(TalentInfoPageAo pageAo);
}
