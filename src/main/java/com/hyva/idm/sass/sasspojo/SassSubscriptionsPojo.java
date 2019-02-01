package com.hyva.idm.sass.sasspojo;

import lombok.Data;

import java.util.List;
@Data
public class SassSubscriptionsPojo {

    private String subscriptionName;
    private String validity;
    private String status;
    private String validityType;
    private String subscriptionFor;
    private String originalCharge;
    private String version;
    private String permission;
    private String addonPermission;
    private Long subscriptionId;
    private String key;
    public List permissionList;
    private Long companies;
    private Long activeUsers;
    private String hiConnect;
    private String hiSync;
    private String users;
    private String hiAccount;
}
