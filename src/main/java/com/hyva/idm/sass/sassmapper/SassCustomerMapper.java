package com.hyva.idm.sass.sassmapper;

import com.hyva.idm.sass.sassentities.SassCustomer;
import com.hyva.idm.sass.sasspojo.SassCustomerPojo;

public class SassCustomerMapper {


    public static SassCustomer mapPojoToEntity(SassCustomerPojo sassCustomerPojo) {
        SassCustomer sassCustomer = new SassCustomer();
        sassCustomer.setCustomerName(sassCustomerPojo.getCustomerName());
        sassCustomer.setCustomerNumber(sassCustomerPojo.getCustomerNumber());
        sassCustomer.setEmail(sassCustomerPojo.getEmail());
        sassCustomer.setGstCode(sassCustomerPojo.getGstCode());
        sassCustomer.setBankName(sassCustomerPojo.getBankName());
        sassCustomer.setAccountNo(sassCustomerPojo.getAccountNo());
        sassCustomer.setIFSCCode(sassCustomerPojo.getIFSCCode());
        sassCustomer.setBranchName(sassCustomerPojo.getBranchName());
        sassCustomer.setPersonIncharge(sassCustomerPojo.getPersonIncharge());
        sassCustomer.setPanNo(sassCustomerPojo.getPanNO());
        sassCustomer.setWebsite(sassCustomerPojo.getWebsite());
        sassCustomer.setAddress(sassCustomerPojo.getAddress());
        sassCustomer.setCreditedLimit(sassCustomerPojo.getCreditedLimit());
        sassCustomer.setStatus(sassCustomerPojo.getStatus());
        return sassCustomer;
    }
}
