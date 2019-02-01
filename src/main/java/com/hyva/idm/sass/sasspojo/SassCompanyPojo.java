package com.hyva.idm.sass.sasspojo;

import lombok.Data;

@Data
public class SassCompanyPojo {

    private Long compnayId;
    private String companyName;
    private String email;
    private String userName;
    private String password;
    private String loginUniqueId;
    private String status;
    private String companyType;
    private String createdDate;
    private String userId;
}
