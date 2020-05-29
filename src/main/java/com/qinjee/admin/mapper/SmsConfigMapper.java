package com.qinjee.admin.mapper;

import com.qinjee.admin.config.SmsConfig;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * 短信配置
 * @author 周赟
 * @date 2019/9/25
 */
@Service
public interface SmsConfigMapper {

    /**
     * 根据业务类型查询对应业务有效的短信配置信息
     * @param businessType
     * @return
     */
    SmsConfig getByBusinessType(@Param("businessType") String businessType);
}
