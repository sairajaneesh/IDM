package com.hyva.idm.sass.sassservice;

import com.hyva.idm.icitem.IcItem;
import com.hyva.idm.icitem.IcItemRepository;
import com.hyva.idm.icitem.IcItempojo;
import com.hyva.idm.sass.sassentities.*;
import com.hyva.idm.sass.sassmapper.*;
import com.hyva.idm.sass.sasspojo.*;
import com.hyva.idm.sass.sassrespositories.*;
import com.hyva.idm.sass.sassutil.ObjectMapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.groovy.util.StringUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;

@Component
public class SassControlPanelService {

    @Autowired
    IcItemRepository icItemRepository;
    @Autowired
    SassCountryRepository sassCountryRepository;
    @Autowired
    SassCurrencyRepository sassCurrencyRepository;
    @Autowired
    SassPaymentMethodRepository sassPaymentMethodRepository;
    @Autowired
    SassVersinControlRepository sassVersinControlRepository;
    @Autowired
    SassSubscriptionRepository sassSubscriptionRepository;
    @Autowired
    SassPackageRepository sassPackageRepository;
    @Autowired
    SassPermissionMasterRepository sassPermissionMasterRepository;
    @Autowired
    SassCustomerRepository sassCustomerRepository;
    @Autowired
    SaasStateRepository saasStateRepository;
    @Autowired
    PosPaymentTypesRepository posPaymentTypesRepository;
    @Autowired
    SassAddOnRepository sassAddOnRepository;
    @Autowired
    PractitionerRegistrationRepository PractitionerRegistrationRepository;
    @Autowired
    PractitionerordersRepository PractitionerordersRepository;
    @Autowired
    CartRegistrationRepository cartRegistrationRepository;
    @Autowired
    CartCustomerRepository cartCustomerRepository;
    @Autowired
    SassCustomerNotificationsRepository SassCustomerNotificationsRepository;
    @Autowired
    SaasTranscationsDataRepository SaasTranscationsDataRepository;
    @Autowired
    DestinationTypeRepository DestinationTypeRepository;
    @Autowired
    DestinationMapRepository DestinationMapRepository;
    @Autowired
    RTRSyncSettingsRepository RTRSyncSettingsRepository;

    public List<SassCurrencyPojo> CurrencyList() {
        List<SassCurrency> currency = (List<SassCurrency>) sassCurrencyRepository.findAll();
        List<SassCurrencyPojo> sassCurrencyPojos = ObjectMapperUtils.mapAll(currency, SassCurrencyPojo.class);
        return sassCurrencyPojos;
    }

    public List<SassPaymentMethodPojo> PaymentMethodList() {
        List<SassPaymentMethod> paymentMethod = (List<SassPaymentMethod>) sassPaymentMethodRepository.findAll();
        List<SassPaymentMethodPojo> sassPaymentMethodPojoList = ObjectMapperUtils.mapAll(paymentMethod, SassPaymentMethodPojo.class);
        return sassPaymentMethodPojoList;
    }

    public List<IcItempojo> IcItemMethodList() {
        List<IcItem> icItem = (List<IcItem>) icItemRepository.findAll();
        List<IcItempojo> icItempojosList = ObjectMapperUtils.mapAll(icItem, IcItempojo.class);
        return icItempojosList;
    }

    public List<SassCustomerPojo> CustomerList(String search) {
        List<SassCustomer> customer = new ArrayList<>();
        if (StringUtils.isBlank(search)) {
            customer = (List<SassCustomer>) sassCustomerRepository.findAll();
        } else {
            customer = sassCustomerRepository.findAllByEmailStartsWith(search);
        }
        List<SassCustomerPojo> sassCustomerPojos1 = new ArrayList<>();
        for (SassCustomer sassCustomer : customer) {
            SassCustomerPojo sassCustomerPojo = new SassCustomerPojo();
            sassCustomerPojo.setCustomerId(sassCustomer.getCustomerId());
            if (sassCustomer.getStateId() != null) {
                sassCustomerPojo.setStateId(sassCustomer.getStateId().getStateName());
            }
            sassCustomerPojo.setCustomerName(sassCustomer.getCustomerName());
            sassCustomerPojo.setGstCode(sassCustomer.getGstCode());
            sassCustomerPojo.setEmail(sassCustomer.getEmail());
            sassCustomerPojo.setCustomerNumber(sassCustomer.getCustomerNumber());
            sassCustomerPojos1.add(sassCustomerPojo);
        }
        return sassCustomerPojos1;
    }

    public SassCurrency SaveCurrency(SassCurrencyPojo saveNewCurrencyDetails) {
        SassCurrency sassCurrency = SaasCurrencyMapper.mapPojoToEntity(saveNewCurrencyDetails);
        sassCurrency.setSassCountryId(new SassCountry(Long.parseLong(saveNewCurrencyDetails.getSassCountryPojoId())));
        sassCurrencyRepository.save(sassCurrency);
        return sassCurrency;
    }

    public SassCustomer SaveCustomer(SassCustomerPojo saveCustomerDetails) {
        SassCustomer sassCustomer = null;
        sassCustomer = sassCustomerRepository.findByEmail(saveCustomerDetails.getEmail());
        if (sassCustomer != null) {
            sassCustomer = null;
        } else {
            sassCustomer = SassCustomerMapper.mapPojoToEntity(saveCustomerDetails);
            if (saveCustomerDetails.getCountryId() != null) {
                sassCustomer.setCountryId(new SassCountry(Long.parseLong(saveCustomerDetails.getCountryId())));
            }
            if (saveCustomerDetails.getCurrencyId() != null) {
                sassCustomer.setCurrencyId(new SassCurrency(Long.parseLong(saveCustomerDetails.getCurrencyId())));
            }
            if (saveCustomerDetails.getStateId() != null) {
                sassCustomer.setStateId(new SaasState(Long.parseLong(saveCustomerDetails.getStateId())));
            }
            sassCustomerRepository.save(sassCustomer);
        }
        return sassCustomer;
    }

    public SassCurrencyPojo getCurrencyPrerequisite() {
        SassCurrencyPojo sassCurrencyPojo = new SassCurrencyPojo();
        List<SassCountry> sassCountryList = (List<SassCountry>) sassCountryRepository.findAll();
        List<SassCountryPojo> sassCountryPojoList = ObjectMapperUtils.mapAll(sassCountryList, SassCountryPojo.class);
        sassCurrencyPojo.setSassCountryPojoList(sassCountryPojoList);
        return sassCurrencyPojo;
    }

    public SassPaymentMethod savePaymentMethodList(SassPaymentMethodPojo saveNewPaymentMethodDetails) {
        SassPaymentMethod sassPaymentMethod = SassPayMethodMapper.mapPojoToEntity(saveNewPaymentMethodDetails);
        sassPaymentMethodRepository.save(sassPaymentMethod);
        return sassPaymentMethod;
    }

    public SassCountry SaveCountryDetails(SassCountryPojo sassCountryPojo) {
        SassCountry sassCountry = SaasCountryMapper.mapPojoToEntity(sassCountryPojo);
        sassCountryRepository.save(sassCountry);
        return sassCountry;
    }

    public SassProjectModule saveVersionControlList(SassProjectModulePojo saveVersionDetails) {
        SassProjectModule sassProjectModule = SassVersionMapper.mapPojoToEntity(saveVersionDetails);
        sassVersinControlRepository.save(sassProjectModule);
        return sassProjectModule;
    }

    public List<SassProjectModulePojo> VersionControlList() {
        List<SassProjectModule> projectModules = (List<SassProjectModule>) sassVersinControlRepository.findAll();
        List<SassProjectModulePojo> sassProjectModulePojos = ObjectMapperUtils.mapAll(projectModules, SassProjectModulePojo.class);
        return sassProjectModulePojos;
    }

    public SassSubscriptions saveSubscriptionList(SassSubscriptionsPojo saveSubscriptionDetails) {
        SassSubscriptions sassSubscriptions = new SassSubscriptions();
        sassSubscriptions = SassSubscriptionMapper.mapPojoToEntity(saveSubscriptionDetails);
        if (saveSubscriptionDetails.getSubscriptionId() != null && saveSubscriptionDetails.getSubscriptionFor().equalsIgnoreCase("HiConnect")) {
            sassSubscriptions.setSubscriptionFor("HiConnect");
        } else if (saveSubscriptionDetails.getSubscriptionId() != null && saveSubscriptionDetails.getSubscriptionFor().equalsIgnoreCase("HiSync")) {
            sassSubscriptions.setHiSync("HiSync");

        } else if (saveSubscriptionDetails.getSubscriptionId() != null && saveSubscriptionDetails.getSubscriptionFor().equalsIgnoreCase("HiAccount")) {
            sassSubscriptions.setHiAccount("HiAccount");
        }
        if (sassSubscriptions.getSubscriptionId() == null || sassSubscriptions.getSubscriptionId().equals("")) {
            sassSubscriptions.setSubscriptionId(saveSubscriptionDetails.getSubscriptionId());
        }

        sassSubscriptionRepository.save(sassSubscriptions);
        return sassSubscriptions;
    }

    //    public List<SassSubscriptionsPojo>  SubscriptionByName(String  subName) {
//        List<SassSubscriptions> subscriptions = (List<SassSubscriptions>) sassSubscriptionRepository.findBySubscriptionName(subName);
//        List<SassSubscriptionsPojo> sassSubscriptionsPojoList = ObjectMapperUtils.mapAll(subscriptions, SassSubscriptionsPojo.class);
//        return sassSubscriptionsPojoList;
//    }
    public List<SassSubscriptionsPojo> SubscriptionList() {
        List<SassSubscriptions> subscriptions = (List<SassSubscriptions>) sassSubscriptionRepository.findAll();
        List<SassSubscriptionsPojo> sassSubscriptionsPojoList = ObjectMapperUtils.mapAll(subscriptions, SassSubscriptionsPojo.class);
        return sassSubscriptionsPojoList;
    }

    public List<PermissionMaster> PermissionMasterList() {
        Iterable<PermissionMaster> permissionMaster = sassPermissionMasterRepository.findAll();
        List<PermissionMaster> permasterPojos = new ArrayList<>();
        permissionMaster.forEach(perMasterEntity -> {
            permasterPojos.add(perMasterEntity);

        });
        return permasterPojos;
    }

    public HashMap<Object, Object> retriveCompanyPermissions() {
        HashMap<Object, Object> finalResult = new HashMap<>();
        List<PermissionMaster> queryResult = sassPermissionMasterRepository.findAllBySaasStatusAndAndParentPM("Active", null);
        for (PermissionMaster permission : queryResult) {
            HashMap node1 = new HashMap();
            node1.put("id", permission.getPmId());
            node1.put("permissionString", permission.getPermissionMasterkey());
            node1.put("status", "Active");
            node1.put("children", new HashSet());
            List<PermissionMaster> queryResult1 = sassPermissionMasterRepository.findAllBySaasStatusAndAndParentPM("Active", permission);
            Map level1End = new HashMap<>();
            for (PermissionMaster permission1 : queryResult1) {
                HashMap node2 = new HashMap();
                node2.put("id", permission1.getPmId());
                node2.put("permissionString", permission1.getPermissionMasterkey());
                node2.put("status", "Active");
                node2.put("children", new HashSet());
                List<PermissionMaster> queryResult2 = sassPermissionMasterRepository.findAllBySaasStatusAndAndParentPM("Active", permission1);
                Map level2End = new HashMap<>();
                for (PermissionMaster permission2 : queryResult2) {
                    HashMap node3 = new HashMap();
                    node3.put("id", permission2.getPmId());
                    node3.put("permissionString", permission2.getPermissionMasterkey());
                    node3.put("status", "Active");
                    node3.put("children", new HashSet());

                    List<PermissionMaster> queryResult3 = sassPermissionMasterRepository.findAllBySaasStatusAndAndParentPM("Active", permission2);
                    Map level3End = new HashMap<>();
                    for (PermissionMaster permission3 : queryResult3) {
                        HashMap node4 = new HashMap();
                        node4.put("id", permission3.getPmId());
                        node4.put("permissionString", permission3.getPermissionMasterkey());
                        node4.put("status", "Active");
                        node4.put("children", new HashSet());

                        List<PermissionMaster> queryResult4 = sassPermissionMasterRepository.findAllBySaasStatusAndAndParentPM("Active", permission3);
                        Map level4End = new HashMap();
                        for (PermissionMaster permission4 : queryResult4) {
                            HashMap node5 = new HashMap();
                            node5.put("id", permission4.getPmId());
                            node5.put("permissionString", permission4.getPermissionMasterkey());
                            node5.put("status", "Active");
                            node5.put("children", new HashSet());

                            List<PermissionMaster> queryResult5 = sassPermissionMasterRepository.findAllBySaasStatusAndAndParentPM("Active", permission4);
                            Map level5End = new HashMap();
                            for (PermissionMaster permission5 : queryResult5) {
                                HashMap node6 = new HashMap();
                                node6.put("id", permission5.getPmId());
                                node6.put("permissionString", permission5.getPermissionMasterkey());
                                node6.put("status", "Active");
                                node6.put("children", new HashSet());

                                level5End.put(permission5.getDescription(), node6);
                                node5.put("children", level5End);
                            }
                            level4End.put(permission4.getDescription(), node5);
                            node4.put("children", level4End);
                        }
                        level3End.put(permission3.getDescription(), node4);
                        node3.put("children", level3End);
                    }
                    level2End.put(permission2.getDescription(), node3);
                    node2.put("children", level2End);
                }
                level1End.put(permission1.getDescription(), node2);
                node1.put("children", level1End);
            }//end level1
            finalResult.put(permission.getDescription(), node1);
        }
        return finalResult;
    }

    public SassPackages savePackageList(SassPackagesPojo savePackageDetails) {
        SassPackages sassPackages = SassPackagesMapper.mapPojoToEntity(savePackageDetails);
        sassPackageRepository.save(sassPackages);
        return sassPackages;
    }

    public List<SassPackagesPojo> PackagesList() {
        List<SassPackages> packages = (List<SassPackages>) sassPackageRepository.findAll();
        List<SassPackagesPojo> sassPackagesPojos = ObjectMapperUtils.mapAll(packages, SassPackagesPojo.class);
        return sassPackagesPojos;
    }

    public List<SassCountryPojo> getCountryList() {
        List<SassCountry> country = (List<SassCountry>) sassCountryRepository.findAll();
        List<SassCountryPojo> sassCountryPojos = ObjectMapperUtils.mapAll(country, SassCountryPojo.class);
        return sassCountryPojos;
    }

    public List<SaasStatePojo> stateList() {
        List<SaasState> state = (List<SaasState>) saasStateRepository.findAll();
        List<SaasStatePojo> PojoList = ObjectMapperUtils.mapAll(state, SaasStatePojo.class);
        return PojoList;
    }

    public SaasState SavestateDetails(SaasStatePojo details) {
        SaasState SaasState = SaasStateMapper.mapPojoToEntity(details);
        SaasState.setCountryId(new SassCountry(Long.parseLong(details.getCountryId())));
        saasStateRepository.save(SaasState);
        return SaasState;

    }

    public PosPaymentTypes Savespayments(PaymentTypePojo details) {
        PosPaymentTypes PosPaymentTypes = SaasPaymentTypeMapper.mapPojoToEntity(details);
        PosPaymentTypes.setCustomer(new SassCustomer(Long.parseLong(details.getCustomerId())));
        posPaymentTypesRepository.save(PosPaymentTypes);
        return PosPaymentTypes;

    }

    public List<PaymentTypePojo> paymenTtypeList() {
        List<PosPaymentTypes> payments = new ArrayList<>();
        payments = (List<PosPaymentTypes>) posPaymentTypesRepository.findAll();
        List<PaymentTypePojo> paymentTypePojos = new ArrayList<>();
        for (PosPaymentTypes posPaymentTypes : payments) {
            PaymentTypePojo paymentTypePojo = new PaymentTypePojo();
            paymentTypePojo.setPaymenetId(posPaymentTypes.getPaymenetId());
            if (posPaymentTypes.getCustomer() != null) {
                paymentTypePojo.setCustomerId(posPaymentTypes.getCustomer().getCustomerName());
            }
            paymentTypePojo.setTotalAmount(posPaymentTypes.getTotalAmount());
            paymentTypePojo.setTransactionDate(posPaymentTypes.getTransactionDate());
            paymentTypePojos.add(paymentTypePojo);
        }
        return paymentTypePojos;
    }

    public List<CustomerNotificationsPojo> customernotify() {
        List<CustomerNotifications> custnotify = (List<CustomerNotifications>) SassCustomerNotificationsRepository.findAll();
        List<CustomerNotificationsPojo> cust = ObjectMapperUtils.mapAll(custnotify, CustomerNotificationsPojo.class);
        return cust;
    }

    public List<TransactionsDataPojo> transactiondataList() {
        List<TransactionsData> custnotify = (List<TransactionsData>) SaasTranscationsDataRepository.findAll();
        List<TransactionsDataPojo> cust = ObjectMapperUtils.mapAll(custnotify, TransactionsDataPojo.class);
        return cust;
    }

    public AddOnn SaveAddOn(AddOnnPojo addOnnPojo) {
        AddOnn addOnn = SassAddOnMapper.mapPojoToEntity(addOnnPojo);
        addOnn.setSubId(new SassSubscriptions(Long.parseLong(addOnnPojo.getSubscriptionId())));
        sassAddOnRepository.save(addOnn);
        return addOnn;

    }

    public CartCustomerLink SaveCartCustomer(CartCustomerLinkPojo cartCustomerLinkPojo) {
        CartCustomerLink cartCustomerLink = CartCustomerMapper.mapPojoToEntity(cartCustomerLinkPojo);
        if (cartCustomerLinkPojo.getCartCustId() != null) {
            cartCustomerLinkPojo.setCartCustId(cartCustomerLinkPojo.getCartCustId());
        }
        cartCustomerLink.setCustomerId(new SassCustomer(Long.parseLong(cartCustomerLinkPojo.getCustomerId())));
        cartCustomerLink.setCartId(new Cartregistration(Long.parseLong(cartCustomerLinkPojo.getCartId())));
        cartCustomerRepository.save(cartCustomerLink);
        return cartCustomerLink;

    }

    public List<AddOnnPojo> AddOnList() {
        List<AddOnn> addOnns = (List<AddOnn>) sassAddOnRepository.findAll();
        List<AddOnnPojo> addOnnPojoList = ObjectMapperUtils.mapAll(addOnns, AddOnnPojo.class);
        return addOnnPojoList;
    }

    public List<CartCustomerLinkPojo> CartCustomerList(String SearchText) {
        List<CartCustomerLink> link = new ArrayList<>();
        if (StringUtils.isBlank(SearchText)) {
            link = (List<CartCustomerLink>) cartCustomerRepository.findAll();
        }
//      else if(StringUtils.isBlank(SearchText)) {
//            link=sassCustomerRepository.(SearchText);}
//            else{
//            link=cartCustomerRepository.(SearchText);
//            }
        List<CartCustomerLinkPojo> cartCustPojoList = ObjectMapperUtils.mapAll(link, CartCustomerLinkPojo.class);
        return cartCustPojoList;
    }

    public Cartregistration saveCartRegistration(CartRegistrationPojo cartRegistrationPojo) {
        Cartregistration cartregistration = CartRegistrationMapper.mapPojoToEntity(cartRegistrationPojo);
        if (cartRegistrationPojo.getCartId() != null) {
            cartRegistrationPojo.setCartId(cartRegistrationPojo.getCartId());
        }
        cartRegistrationRepository.save(cartregistration);

        return cartregistration;


    }

    public IcItem SaveItem(IcItempojo icItempojo) {
        IcItem icItem = IcItemMapper.mapPojoToEntity(icItempojo);
        icItemRepository.save(icItem);
        return icItem;

    }

    public List<CartRegistrationPojo> cartRegistrationPojoList() {
        List<Cartregistration> cartregistrations = (List<Cartregistration>) cartRegistrationRepository.findAll();
        List<CartRegistrationPojo> cartRegistrationPojoList = ObjectMapperUtils.mapAll(cartregistrations, CartRegistrationPojo.class);
        return cartRegistrationPojoList;
    }

    public PractitionerRegistration SavepracReg(PractitionerRegistrPojo Practitioner) {
        PractitionerRegistration register = PractitionerRegMapper.mapPojoToEntity(Practitioner);
        register.setSubscriptionId(new SassSubscriptions(Long.parseLong(Practitioner.getSubscriptionId())));
        PractitionerRegistrationRepository.save(register);
        return register;

    }

    public List<PractitionerRegistrPojo> practitionerReg(String search) {
        List<PractitionerRegistration> pregister = new ArrayList<>();
        if (StringUtils.isBlank(search)) {
            pregister = (List<PractitionerRegistration>) PractitionerRegistrationRepository.findAll();
        } else {
            pregister = PractitionerRegistrationRepository.findAllByEmail(search);
        }
        List<PractitionerRegistrPojo> register = ObjectMapperUtils.mapAll(pregister, PractitionerRegistrPojo.class);
        return register;
    }


    public Practitionerorders Savepracorders(PractitionerordersPojo details) {
        Practitionerorders orders = PractordersMapper.mapPojoToEntity(details);
        orders.setPractitionerId(new PractitionerRegistration(Long.parseLong(details.getPractitionerId())));
        orders.setOrderId(new SassOrders(Long.parseLong(details.getOrderId())));
        PractitionerordersRepository.save(orders);
        return orders;

    }

    public List<PractitionerordersPojo> practorderList() {
        List<Practitionerorders> state = (List<Practitionerorders>) PractitionerordersRepository.findAll();
        List<PractitionerordersPojo> PojoList = ObjectMapperUtils.mapAll(state, PractitionerordersPojo.class);
        return PojoList;
    }

    public DestinationType SaveDestination(DestinationTypePojo destdetails) {
        DestinationType dsettype = DestinationTypeMapper.mapPojoToEntity(destdetails);

        DestinationTypeRepository.save(dsettype);
        return dsettype;

    }

    public List<DestinationTypePojo> destinationList(String sourceType) {
        List<DestinationType> destinationType = null;

        if (!sourceType.equals("All")) {
            destinationType = DestinationTypeRepository.findByDocType(sourceType);
        } else {
            destinationType = (List<DestinationType>) DestinationTypeRepository.findAll();
        }

    List<DestinationTypePojo> destinationTypePojos = ObjectMapperUtils.mapAll(destinationType, DestinationTypePojo.class);

        return destinationTypePojos;
}
    public List<DestinationTypePojo> destinationtype() {
        List<DestinationType> desttype = (List<DestinationType>) DestinationTypeRepository.findAll();
        List<DestinationTypePojo> PojoList = ObjectMapperUtils.mapAll(desttype, DestinationTypePojo.class);
        return PojoList;
    }

    public DestinationMap SaveDestinationMap(DestinationMapPojo destdetails) {
//        DestinationMap map=null;
        DestinationMap dsettype= DestinationMapMapper.mapPojoToEntity(destdetails);
      if(destdetails.getId()!=null){
          destdetails.setId(destdetails.getId());

     }
        DestinationMapRepository.save(dsettype);
        return dsettype;

    }
    public List<DestinationMapPojo> destinationMapList() {
        List<DestinationMap> map = (List<DestinationMap>) DestinationMapRepository.findAll();
        List<DestinationMapPojo> List = ObjectMapperUtils.mapAll(map, DestinationMapPojo.class);
        return List;
    }
    public DestinationMap getMapObject(String description){
        DestinationMap destinationMap=DestinationMapRepository.findBysourceName(description);
        return  destinationMap;
    }
    public RTRSyncSettings saveRTRSyncsettingDetails(RTRSyncSettingsPojo rtrsync){
        RTRSyncSettings rtr=RTRSyncSettingsMapper.mapPojoToEntity(rtrsync);
        if(rtrsync.getRtrSyncID()!=null){
            rtrsync.setRtrSyncID(rtrsync.getRtrSyncID());
        }
        RTRSyncSettingsRepository.save(rtr);
        return rtr;
    }
    public List<RTRSyncSettingsPojo>getRTRSyncsettingList(String searchtext){
        List<RTRSyncSettings>rtr=new ArrayList<>();
        if(StringUtils.isBlank(searchtext)){
            rtr=(List<RTRSyncSettings>)RTRSyncSettingsRepository.findAll();
        }else{
         rtr=RTRSyncSettingsRepository.findByCompanyKeyWordStartingWith(searchtext);}
        List<RTRSyncSettingsPojo>list=ObjectMapperUtils.mapAll(rtr,RTRSyncSettingsPojo.class);
        return list;
    }
}
