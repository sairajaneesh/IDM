package com.hyva.idm.sass.sasspojo;

import lombok.Data;

@Data
public class SassPackagesPojo {

    private Long packagesSASSId;
    private String packageName;
    private String redirectUrl;
    private String syncUrl;
    private String licncUrl;
    private String status;
    private String companyCreateurl;
}
