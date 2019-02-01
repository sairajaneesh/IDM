package com.hyva.idm.sass.sassentities;

import com.hyva.idm.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@ RequiredArgsConstructor

public class SassOrders extends BaseEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long SassOrdersId;
    private String companyName;
    private String companyAddress;
    private String companyEmail;
    private String companyLoginId;
    private String companyLoginPassword;
    private String customerName;
    private String customerEmail;
    private String companyPhone;
    private String companyRegistrationNo;
    private String companyGSTno;
    private String  deliveryType;//desktop r cloud
    private String  packagesSASSId;
    private String  companySubscriptionId;
    private String username;
    private String password;
    private String companyNo;
    private String language;
    private String pan;
    private Date incorpDate;
    private Long faxno;
    private String website;
//    private String state;
//    private String currency;
    private String gstRegistered;
    private Date gstRegisteredDate;

    @Column(unique = true)
    private String licenceKey;
    private String  newsletter;
    private String postingStatus;
    @OneToOne
    private SassSubscriptions sassSubscriptionsId;
    @OneToOne
    private SassUser sassUserId;
    private String loginUniqueId;
    private Date loginUniqueIdExpiry;

    @OneToOne
    private SassCountry countryId;
    @OneToOne
    private SassCurrency currencyId;
    @OneToOne
    private SaasState stateId;


    public SassOrders(Long SassOrdersId) {
        this.SassOrdersId=SassOrdersId;
    }
}
