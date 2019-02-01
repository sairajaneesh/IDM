package com.hyva.idm.sass.sasspojo;

import lombok.Data;

@Data
public class SassUserPojo {
    private int useraccount_id;
    private String userName;
    private String phone;
    private String passwordUser;
    private String full_name;
    private String email;
    private String securityQuestion;
    private String securityAnswer;
    private String status;
}
