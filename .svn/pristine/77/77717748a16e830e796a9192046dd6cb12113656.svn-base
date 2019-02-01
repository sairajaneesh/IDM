package com.hyva.idm.sass.sassentities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data
public class PosPaymentTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long paymenetId;
    private String voucherPayment;
    private String cardPayment;
    private double totalCashPayment;
    private double totalVoucherPayment;
    private double totalCardPayment;
    private double totalAmount;
    private String duplicatePrint;
    @OneToOne
    private SassOrders sassOrders;
    private int count;
    private double totalTaxAmount;
    private double roundingAdjustment;
    @OneToOne
    private SassCustomer customer;
    private String locationId;
    private String useraccount_id;
    private transient String company_no;
    private transient String registerNo;
    private transient String typePrefix;
    private transient String nextref;
    private String fileName;
    private String bankPayment;
    private double totalBankAmt;
    private double amountReturn;
    @Temporal(TemporalType.DATE)
    private Date transactionDate;

}
