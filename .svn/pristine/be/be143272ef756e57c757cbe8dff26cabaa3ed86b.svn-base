package com.hyva.idm.sass.sassentities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
public class PermissionMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long pmId;
    private String description; //Sales (Pages)
    private String saasStatus;
    private String permissionMasterkey;
    @OneToOne
    private PermissionGroup permissionGroupId;//Sales
    @OneToOne
    private PermissionMaster parentPM;

}
