package com.qinjee.admin.service;

import com.qinjee.admin.model.PageResult;
import com.qinjee.admin.model.ao.TalentInfoPageAo;
import com.qinjee.admin.model.vo.TalentInfoVo;

public interface ITalentInfoService {


    PageResult<TalentInfoVo> list(TalentInfoPageAo pageAo);
}
