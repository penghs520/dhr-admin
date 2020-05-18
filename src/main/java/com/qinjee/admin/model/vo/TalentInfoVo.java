package com.qinjee.admin.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class TalentInfoVo implements Serializable {

    private String companyName;

    private String userName;

    private String gender;

    private String phone;

    private String email;

    private String archiveId;
}
