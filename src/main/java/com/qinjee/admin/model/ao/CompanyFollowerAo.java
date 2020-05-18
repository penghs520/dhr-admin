package com.qinjee.admin.model.ao;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CompanyFollowerAo implements Serializable {

    private List<Integer> companyIds;

    private List<Integer> followerIds;
}
