package com.hyva.idm.sass.sassentities;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class SassCustomer  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long customerId;
    private String customerName;
    private String customerNumber;
    private String customerCode;
    private String companyNumber;
    private String companyCode;
    private String address;
    private String attention;
    @Email
    @Column(unique = true)
    private String email;
    private String phoneNumber1;
    private String phoneNumber2;
    private String faxTelex;
    private String contactPerson;
    private String generalNote;
    private String website;
    private String gstCode;
    private boolean creditedLimitAlert;
    private String creditedLimit;
    @OneToOne
    private SassCountry countryId;
    @OneToOne
    private SassOrders orderId;
    private String periodOfVerification;
    private String ArAccount;
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Temporal(TemporalType.DATE)
    private Date verificationDate;
    private String currencyWord;
    private String currencySymbol;
    private String shipToAddress;
    @OneToOne
    private SassCurrency currencyId;
    private String locationId;
    private String useraccount_id;
    private String hiConnectStatus;
    private String panNo;
    @OneToOne
    private SaasState stateId;
    private String bankName;
    private String accountNo;
    private String iFSCCode;
    private String branchName;
    private String personIncharge;
    private double loyaltyPoints;
    private String status;
    private String loginName;
    private String loginPassword;
    private String licence;
    private String loginUniqueId;
    private Date loginUniqueIdExpiry;


    public SassCustomer(Long customerId) {
        this.customerId=customerId;
    }

    public SassCustomer() {

    }
}
