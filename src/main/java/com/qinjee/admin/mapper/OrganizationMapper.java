package com.qinjee.admin.mapper;



import com.qinjee.admin.model.vo.OrganizationVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationMapper {

    OrganizationVo getTop(@Param("companyId") Integer companyId);

    void update(OrganizationVo topOrganization);
}
