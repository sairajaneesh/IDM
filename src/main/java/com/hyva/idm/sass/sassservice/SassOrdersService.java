package com.hyva.idm.sass.sassservice;

import com.hyva.idm.icitem.IcItem;
import com.hyva.idm.icitem.IcItemRepository;
import com.hyva.idm.icitem.IcItempojo;
import com.hyva.idm.sass.sassentities.*;
import com.hyva.idm.sass.sassmapper.CustomerNotificationMapper;
import com.hyva.idm.sass.sassmapper.SassOrdersMapper;
import com.hyva.idm.sass.sasspojo.CustomerNotificationsPojo;
import com.hyva.idm.sass.sasspojo.SassOrdersPojo;
import com.hyva.idm.sass.sassrespositories.*;
import com.hyva.idm.sass.sassutil.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.hyva.idm.sass.sassutil.AccessTokenGenerator.randomAlphaNumeric;


@Component
public class SassOrdersService {
    @Autowired
    SassOrdersRepository sassOrdersRepository;
    @Autowired
    SassSubscriptionRepository sassSubscriptionRepository;
    @Autowired
    SassCustomerRepository sassCustomerRepository;
    @Autowired
    PosPaymentTypesRepository posPaymentTypesRepository;
    @Autowired
    SassTransactionRepository sassTransactionRepository;
    @Autowired
    SassCountryRepository SassCountryRepository;
    @Autowired
    SassCurrencyRepository SassCurrencyRepository;
    @Autowired
    SaasStateRepository SaasStateRepository;
    @Autowired
    SassUserRepository sassUserRepository;

    @Autowired
    IcItemRepository icItemRepository;
    @Autowired
    SassCompanyRepository sassCompanyRepository;



    public SassCustomer getCustomerObj(SassCustomer credentials)
    {
        SassCustomer sassCustomer=sassCustomerRepository.findByEmailAndLoginNameAndLoginPassword(credentials.getEmail(),credentials.getLoginName(),credentials.getLoginPassword());
        return sassCustomer;
    }


    @Transactional
    public SassOrders createbuynowOrderSave(SassOrdersPojo sassOrdersPojo) {
//    boolean status=sassCustomerRepository.existsByEmail(sassOrdersPojo.getCustomerEmail());

        SassOrders sassOrders = null;
        List<SassOrders> list = sassOrdersRepository.findByCompanyEmail(sassOrdersPojo.getCompanyEmail());
        if (list.size() > 0) {
            sassOrders = null;

        } else {

            sassOrders = SassOrdersMapper.mapPojoToEntity(sassOrdersPojo);
//        if(!status){
//            return  sassOrders;
//        }

            SassCountry country=SassCountryRepository.findByCountryName(sassOrdersPojo.getCountryId());
            if(country !=null)
                sassOrders.setCountryId(country);
            SassCurrency currency=SassCurrencyRepository.findByCurrencyName(sassOrdersPojo.getCurrencyId());
            if(currency!=null)
                sassOrders.setCurrencyId(currency);
            SaasState state=SaasStateRepository.findByStateName(sassOrdersPojo.getStateId());
            if(state!=null)
                sassOrders.setStateId(state);
            SassSubscriptions orders = sassSubscriptionRepository.findBySubscriptionName(sassOrdersPojo.getSubscriptionId());
            if (orders != null)
                sassOrders.setSassSubscriptionsId(orders);
            String licenceKey = getLicenceKey(sassOrdersPojo);
            sassOrders.setLicenceKey(licenceKey);
//            SassUser sassUser = sassUserRepository.findByUseraccountid(Integer.parseInt(sassOrdersPojo.getUserId()));
//            if(sassUser != null)
//            sassOrders.setSassUserId(sassUser);
            sassOrders = sassOrdersRepository.save(sassOrders);
            SassCustomer sassCustomer = new SassCustomer();
            sassCustomer.setCustomerName(sassOrdersPojo.getCustomerName());
            sassCustomer.setEmail(sassOrdersPojo.getCustomerEmail());
            sassCustomer.setOrderId(sassOrders);
            sassCustomer = sassCustomerRepository.save(sassCustomer);
            SassCompany sassCompany = new SassCompany();
//            sassOrders = SassCompanyMapper.mapPojoToEntity(sassOrdersPojo);
            if(sassCustomer != null) {
                sassCompany.setSassCustomerId(sassCustomer);
            }
            sassCompany.setCustomerName(sassOrdersPojo.getCustomerName());
            sassCompany.setCompanyName(sassOrdersPojo.getCompanyName());
            sassCompany.setCompanyAddress(sassOrdersPojo.getCompanyAddress());
            sassCompany.setDeliveryType(sassOrdersPojo.getDeliveryType());
            sassCompany.setCompanyEmail(sassOrdersPojo.getCompanyEmail());
            sassCompany.setNewsletter(sassOrdersPojo.getNewsletter());
            sassCompany.setCompanyPhone(sassOrdersPojo.getCompanyPhone());
            sassCompany.setUsername(sassOrdersPojo.getUsername());
            sassCompany.setPassword(sassOrdersPojo.getPassword());
            sassCompany.setPackagesSASSId(sassOrdersPojo.getPackagesSASSId());
            sassCompany.setCompanyNo(sassOrdersPojo.getCompanyNo());
            sassCompany.setFaxno(sassOrdersPojo.getFaxno());
            sassCompany.setLanguage(sassOrdersPojo.getLanguage());
            sassCompany.setGstRegistered(sassOrdersPojo.getGstRegistered());
            sassCompany.setGstRegisteredDate(sassOrdersPojo.getGstRegisteredDate());
            sassCompany.setIncorpDate(sassOrdersPojo.getIncorpDate());
            sassCompany.setPan(sassOrdersPojo.getPan());
            sassCompany.setWebsite(sassOrdersPojo.getWebsite());
            sassCompany.setCountryId(country);
            sassCompany.setStateId(state);
            sassCompany.setCurrencyId(currency);
            sassCompany.setLicenceKey(licenceKey);
            sassCompany.setSassSubscriptionsId(orders);
            sassCompany.setSassOrdersId(sassOrders);
//            sassCompany.setSassUserId(sassUser);
            sassCompany = sassCompanyRepository.save(sassCompany);
            PosPaymentTypes posPaymentTypes = new PosPaymentTypes();
            posPaymentTypes.setTotalAmount(60);
            posPaymentTypes.setCustomer(sassCustomer);
            posPaymentTypes.setSassOrders(sassOrders);
            posPaymentTypesRepository.save(posPaymentTypes);
        } return sassOrders;
    }


    public List<SassCustomer>  getCompanyNamesListFromCustomers(String CompanyName){
        List<SassCustomer> order =sassCustomerRepository.findByCustomerNameIsStartingWith(CompanyName);
        return order;
    }

    public List<SassOrders>  searchCompanyNames(String CompanyName){
        List<SassOrders> order =sassOrdersRepository.findByCompanyNameIsStartingWith(CompanyName);
        return order;
    }
    public List<SassOrders>  getOrderListByEmail(String email){
        List<SassOrders> order =sassOrdersRepository.findByCompanyEmail(email);
        return order;
    }
    public List<SassOrdersPojo> OrderList() {
//        SassUser sassUser = sassUserRepository.findByUseraccountid(Integer.parseInt(userId));
        List<SassOrders> sassOrders = (List<SassOrders>) sassOrdersRepository.findAll();
        List<SassOrdersPojo> sassOrdersPojos = ObjectMapperUtils.mapAll(sassOrders, SassOrdersPojo.class);
        return sassOrdersPojos;
    }
   public List<PosPaymentTypes> getPaymentsList(){
    Iterable<PosPaymentTypes> posPaymentTypes=posPaymentTypesRepository.findAll();
    List<PosPaymentTypes> posPaymentTypes1 = new ArrayList<>();
    posPaymentTypes.forEach(PosPaymentTypes -> {
        posPaymentTypes1.add(PosPaymentTypes);

    });
    return posPaymentTypes1;

    }

    public List<SassCompany> getSaasCustomerRegNo(String fromRegno){
        List<SassCompany> sassCompanyList =sassCompanyRepository.findByHiConnectCompnyRegNo(fromRegno);
        return sassCompanyList;
    }
    public List<SassCompany> getfindByCompanyNameIsLike(String customerName){
        List<SassCompany> sassCompanyList =sassCompanyRepository.findByCompanyNameStartingWith(customerName);
        return sassCompanyList;
    }


    public SassCompany getSaasCustomerRegNoObj(String fromRegno){
        SassCompany sassCompanyObj =sassCompanyRepository.getByHiConnectCompnyRegNo(fromRegno);
        return sassCompanyObj;
    }

    public List<TransactionsData> getTransactionList(){
        List<TransactionsData> transactionsDataList =sassTransactionRepository.findAllByStatus("pending");
        return transactionsDataList;
    }

    public TransactionsData getTransactionObject(Long transactionId){
        TransactionsData transactionsData =sassTransactionRepository.findAllByTransactionId(transactionId);
        return transactionsData;
    }

    public String UpdatePermissionGpandPm(){

//        List<PermissionGroup> masterPermissionGroupList = multiCompanyDao.listMasterPermissionGroup();
//        if (masterPermissionGroupList.isEmpty()) {
//            HSSFSheet sheet = readingXlsFile.getXlsSheet("accounts/Permission_Group.xls");
//            HSSFRow hssfRow;
//            Iterator iterable = sheet.rowIterator();
//            while (iterable.hasNext()) {
//                PermissionGroup groupObj = new PermissionGroup();
//                hssfRow = (HSSFRow) iterable.next();
//                String description = hssfRow.getCell(0).toString();
//                String featureName = hssfRow.getCell(1).toString();
//                String pgKey = hssfRow.getCell(2).toString();
//                String saasStatus = hssfRow.getCell(3).toString();
//                PermissionGroup permissionGroup = new PermissionGroup();
//                permissionGroup.setDescription(description);
//                permissionGroup.setFeatureName(featureName);
//                permissionGroup.setPgkey(pgKey);
//                permissionGroup.setSaasStatus(saasStatus);
//                multiCompanyDao.save(permissionGroup);
//            }
//        }42
//        List<PermissionMaster> permissionMasterList = multiCompanyDao.listMasterPermissionMaster();
//        if(permissionMasterList.isEmpty()) {
//            HSSFSheet sheet = readingXlsFile.getXlsSheet("accounts/Invetorylevel3.xls");
//            HSSFRow hssfRow;
//            Iterator iterable = sheet.rowIterator();
//            while (iterable.hasNext()) {
//                addNewItemDTO itemDTO = null;
//                hssfRow = (HSSFRow) iterable.next();
//                Cell accountgroup = hssfRow.getCell(0);
//                Cell level1 = hssfRow.getCell(1);
//                Cell level2 = hssfRow.getCell(2);
//                Cell description = hssfRow.getCell(3);
//                Cell status = hssfRow.getCell(4);
//                Cell permissionkey = hssfRow.getCell(5);
//                PermissionMasterDTO permissionMasterDTO = new PermissionMasterDTO();
//                if (accountgroup != null) {
//                    com.hyva.master.entities.PermissionGroup permissionGroup = hiposService.getpermissionMasterGroupMaster(accountgroup.toString());
//                    if (permissionGroup != null) {
//                        permissionMasterDTO.setPgId(permissionGroup.getPgId());
//                    }
//                    com.hyva.master.entities.PermissionMaster permissionMaster = null;
//                    if (level1 != null) {
//                        permissionMaster = hiposService.getpermissionMasterMaster(level1.toString());
//                        if (permissionMaster != null) {
//                            permissionMasterDTO.setPmId(Long.parseLong(permissionMaster.getPmId().toString()));
//                        } else {
//                            permissionMasterDTO.setPmId(null);
//                        }
//                    } else {
//                        permissionMasterDTO.setPmId(null);
//                    }
//                    if (level2 != null) {
//                        permissionMaster = hiposService.getpermissionMasterMaster(level2.toString());
//                        if (permissionMaster != null) {
//                            permissionMasterDTO.setPmId(Long.parseLong(permissionMaster.getPmId().toString()));
//                        } else {
//                            permissionMasterDTO.setPmId(null);
//                        }
//                    } else {
//                        permissionMasterDTO.setPmId(null);
//                    }
//                    permissionMasterDTO.setDescription(description.toString());
//                    permissionMasterDTO.setSaasStatus(status.toString());
//                    permissionMasterDTO.setPermissionMasterkey(permissionkey.toString());
//                    hiposService.NewpermissionMaster(permissionMasterDTO);
//
//                }}}
        return null;

    }

    public String getLicenceKey(SassOrdersPojo sassOrdersPojo) {
        String requestUrl = "http://cloud.hiaccounts.com:8890/licenser/getlicense";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> uriParams= new LinkedMultiValueMap<String, String>();
        uriParams.add("companyName", sassOrdersPojo.getCompanyName());
        uriParams.add("emailId", sassOrdersPojo.getCompanyEmail());
        uriParams.add("customerName", "Azgar Idm Test");
        uriParams.add("EditionName", "professional");
        uriParams.add("validity", "50");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(uriParams, headers);
        System.out.println("SSS"+uriParams);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(requestUrl, request, String.class);
//      JSONObject jsonObj = new JSONObject();
//      String balance = jsonObj.get("data").toString();
        return response;
    }





    public String  getLicenceKeyManual(SassOrdersPojo sassOrdersPojo) {
        String requestUrl = "http://cloud.hiaccounts.com:8890/licenser/getlicense";
      //  License license= LicenceController.getManualActivationRequestString();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> uriParams= new LinkedMultiValueMap<String, String>();
        uriParams.add("companyName", sassOrdersPojo.getCompanyName());
        uriParams.add("emailId", sassOrdersPojo.getCompanyEmail());
        uriParams.add("customerName", "Azgar Idm Test");
        uriParams.add("EditionName", "professional");
        uriParams.add("validity", "50");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(uriParams, headers);
        System.out.println("SSS"+uriParams);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(requestUrl, request, String.class);
//      JSONObject jsonObj = new JSONObject();
//      String balance = jsonObj.get("data").toString();
        return response;
    }

   public SassOrders validateParent_Token(String parent_Token,String email){
       SassCustomer sassCustomer=sassCustomerRepository.findByEmailAndLoginUniqueId(parent_Token,email);
    if(sassCustomer!=null){
        String token=randomAlphaNumeric();
    }
       SassOrders sassOrders=new SassOrders();
        return sassOrders;
   }

    public void getSaveNewItemData(IcItem icItem) {
        icItemRepository.save(icItem);
    }
    public void getSaveSalesInvoiceData(IcItem icItem) {
        icItemRepository.save(icItem);
    }
}