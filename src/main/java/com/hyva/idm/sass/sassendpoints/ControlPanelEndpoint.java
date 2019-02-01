package com.hyva.idm.sass.sassendpoints;
import com.hyva.idm.constants.EndpointConstants;
import com.hyva.idm.icitem.IcItem;
import com.hyva.idm.icitem.IcItempojo;
import com.hyva.idm.pojo.IDMResponse;
import com.hyva.idm.sass.sassentities.*;
import com.hyva.idm.sass.sasspojo.*;
import com.hyva.idm.sass.sassservice.SassControlPanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(EndpointConstants.SERVICES_ENDPOINT)

public class ControlPanelEndpoint {
    @Autowired
    SassControlPanelService sassControlPanelService;

    @RequestMapping(value = "/getPaginatedCurrencyList",
            method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public IDMResponse getPaginatedCurrencyList(){
        List<SassCurrencyPojo> sassCurrencyPojos= sassControlPanelService.CurrencyList();
        return new IDMResponse(HttpStatus.OK.value(), "success",sassCurrencyPojos);
    }
    @RequestMapping(value = "/saveNewCurrency",
            method = RequestMethod.POST,consumes = "application/json",
            produces = "application/json")
    public SassCurrency saveNewCurrency(@RequestBody SassCurrencyPojo saveNewCurrencyDetails )  {
        return sassControlPanelService.SaveCurrency(saveNewCurrencyDetails);
    }
    @RequestMapping(value = "/saveNewCustomer",
            method = RequestMethod.POST,consumes = "application/json",
            produces = "application/json")
    public SassCustomer saveNewCustomer(@RequestBody SassCustomerPojo saveCustomerDetails )  {
        return sassControlPanelService.SaveCustomer(saveCustomerDetails);
    }
    @RequestMapping(value = "/saveCountry",method = RequestMethod.POST,consumes ="application/json")
    public IDMResponse saveCountry(@RequestBody SassCountryPojo saveCountryDetails )  {
        SassCountry SassCountry=sassControlPanelService.SaveCountryDetails(saveCountryDetails);
        return new IDMResponse(HttpStatus.OK.value(), " success",SassCountry);
    }
    @RequestMapping(value = "/savePaymentMethod", method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    public SassPaymentMethod savePaymentMethod(@RequestBody SassPaymentMethodPojo details )  {
        SassPaymentMethod sassPaymentMethod = null;
        sassPaymentMethod = sassControlPanelService.savePaymentMethodList(details);
        return sassPaymentMethod;
    }
    @RequestMapping(value = "/getPayMethodList",
            method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public IDMResponse getPayMethodList(){
        List<SassPaymentMethodPojo> sassPaymentMethodPojoList= sassControlPanelService.PaymentMethodList();
        return new IDMResponse(HttpStatus.OK.value(), "success",sassPaymentMethodPojoList);
    }
    @RequestMapping(value = "/getItemList",
            method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public IDMResponse getItemList(){
        List<IcItempojo> icItempojosList= sassControlPanelService.IcItemMethodList();
        return new IDMResponse(HttpStatus.OK.value(), "success",icItempojosList);
    }
    @RequestMapping(value = "/getCustomerList",
            method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public IDMResponse getCustomerList(@RequestParam(value = "SearchText")String SearchText){
        List<SassCustomerPojo> sassCustomerPojos= sassControlPanelService.CustomerList(SearchText);
        return new IDMResponse(HttpStatus.OK.value(), "success",sassCustomerPojos);
    }

    @RequestMapping(value = "/saveStateDetails",method = RequestMethod.POST,consumes ="application/json",produces = "application/json")
    public IDMResponse  savestate(@RequestBody SaasStatePojo saveStateDetails )  {
        SaasState saasState=sassControlPanelService.SavestateDetails(saveStateDetails);
        return new IDMResponse(HttpStatus.OK.value(), " success",saasState);
    }
    @RequestMapping(value = "/stateList",
            method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public IDMResponse stateList(){
        List<SaasStatePojo> saasStatePojos= sassControlPanelService.stateList();
        return new IDMResponse(HttpStatus.OK.value(), "success",saasStatePojos);
    }

    @RequestMapping(value = "/getCountry",
            method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public IDMResponse getCountry(){
        List<SassCountryPojo> sassCountryPojos= sassControlPanelService.getCountryList();
        return new IDMResponse(HttpStatus.OK.value(), "success",sassCountryPojos);
    }



    @RequestMapping(value = "/saveVersionControl", method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    public SassProjectModule saveVersionControl(@RequestBody SassProjectModulePojo modulePojo )  {
        SassProjectModule sassProjectModule = null;
        sassProjectModule = sassControlPanelService.saveVersionControlList(modulePojo);
        return sassProjectModule;
    }

    @RequestMapping(value = "/getVersionControlList",
            method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public IDMResponse getVersionControlList(){
        List<SassProjectModulePojo> sassProjectModule= sassControlPanelService.VersionControlList();
        return new IDMResponse(HttpStatus.OK.value(), "success",sassProjectModule);
    }

    @RequestMapping(value = "/saveNewSubscription", method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    public SassSubscriptions saveNewSubscription(@RequestBody SassSubscriptionsPojo subPojo )  {
        SassSubscriptions sassSubscriptions = null;
        sassSubscriptions = sassControlPanelService.saveSubscriptionList(subPojo);
        return sassSubscriptions;
    }


//
//    @RequestMapping(value = "/getSubscriptionByName",
//            method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
//    public IDMResponse getSubscriptionByName(@RequestBody String subcriptionName){
//        List<SassSubscriptionsPojo> sassSubscriptionsPojoList= sassControlPanelService.SubscriptionByName(subcriptionName);
//        return new IDMResponse(HttpStatus.OK.value(), "azgar success",sassSubscriptionsPojoList);
//    }

    @RequestMapping(value = "/getSubscriptionList",
            method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public IDMResponse getSubscriptionList(){
        List<SassSubscriptionsPojo> sassSubscriptionsPojoList= sassControlPanelService.SubscriptionList();
        return new IDMResponse(HttpStatus.OK.value(), "azgar success",sassSubscriptionsPojoList);
    }

    @RequestMapping(value = "/saveNewPackage", method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    public SassPackages saveNewPackage(@RequestBody SassPackagesPojo pkgPojo )  {
        SassPackages sassPackages = null;
        sassPackages = sassControlPanelService.savePackageList(pkgPojo);
        return sassPackages;
    }

    @RequestMapping(value = "/getPackageList",
            method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public IDMResponse getPackageList(){
        List<SassPackagesPojo> sassPackagesPojos= sassControlPanelService.PackagesList();
        return new IDMResponse(HttpStatus.OK.value(), "success",sassPackagesPojos);
    }

    @RequestMapping(value = "/getPermissionMasterList",method = RequestMethod.GET,produces="application/json")
    public List<PermissionMaster> getPermissionMasterList()
    {
        return sassControlPanelService.PermissionMasterList();
    }

    @RequestMapping(value = "/savepayment",method = RequestMethod.POST,consumes ="application/json",produces = "application/json")
    public IDMResponse  savepayment(@RequestBody PaymentTypePojo saveDetails )  {
        PosPaymentTypes PosPaymentTypes=sassControlPanelService.Savespayments(saveDetails);
        return new IDMResponse(HttpStatus.OK.value(), " success",PosPaymentTypes);
    }
    @RequestMapping(value = "/paymentList",
            method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public IDMResponse paymentList(){
        List<PaymentTypePojo> PaymentTypePojo= sassControlPanelService.paymenTtypeList();
        return new IDMResponse(HttpStatus.OK.value(), "success",PaymentTypePojo);
    }
    @RequestMapping(value = "/customernotifyList",
            method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public IDMResponse customernotifyList(){
        List<CustomerNotificationsPojo> custinfo= sassControlPanelService.customernotify();
        return new IDMResponse(HttpStatus.OK.value(), "success",custinfo);
    }
    @RequestMapping(value = "/transdataList",
            method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public IDMResponse transdataList(){
        List<TransactionsDataPojo> data= sassControlPanelService.transactiondataList();
        return new IDMResponse(HttpStatus.OK.value(), "success",data);
    }

    @RequestMapping(value = "/savepractitioner",method = RequestMethod.POST,consumes ="application/json",produces = "application/json")
    public IDMResponse  savepractitioner(@RequestBody PractitionerRegistrPojo saveDetails )  {
        PractitionerRegistration register=sassControlPanelService.SavepracReg(saveDetails);
        return new IDMResponse(HttpStatus.OK.value(), " success",register);
    }
    @RequestMapping(value = "/practitionerReg",
            method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public IDMResponse practitionerReg(@RequestParam(value = "SearchText")String SearchText){

        List<PractitionerRegistrPojo> reg= sassControlPanelService.practitionerReg(SearchText);
        return new IDMResponse(HttpStatus.OK.value(), "success",reg);
    }
    @RequestMapping(value = "/saveNewAddOn",method = RequestMethod.POST,consumes ="application/json",produces = "application/json")
    public IDMResponse  saveNewAddOn(@RequestBody AddOnnPojo addOnnPojo )  {
        AddOnn addOnn=sassControlPanelService.SaveAddOn(addOnnPojo);
        return new IDMResponse(HttpStatus.OK.value(), " success",addOnn);
    }

    @RequestMapping(value = "/saveCartCustomer",method = RequestMethod.POST,consumes ="application/json",produces = "application/json")
    public IDMResponse  saveCartCustomer(@RequestBody CartCustomerLinkPojo cartCustomerLinkPojo )  {
        CartCustomerLink cartCustomerLink=sassControlPanelService.SaveCartCustomer(cartCustomerLinkPojo);
        return new IDMResponse(HttpStatus.OK.value(), " success",cartCustomerLink);
    }
    @RequestMapping(value = "/getCartCustomerList",
            method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public IDMResponse getCartCustomerList(@RequestParam(value = "SearchText")String SearchText){

        List<CartCustomerLinkPojo> cartCustomerLinkPojos= sassControlPanelService.CartCustomerList(SearchText);
        return new IDMResponse(HttpStatus.OK.value(), " success",cartCustomerLinkPojos);
    }

    @RequestMapping(value = "/getAddOnList",
            method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public IDMResponse getAddOnList(){
        List<AddOnnPojo> addOnnPojos= sassControlPanelService.AddOnList();
        return new IDMResponse(HttpStatus.OK.value(), " success",addOnnPojos);
    }

    @RequestMapping(value = "/savepractitionerorders",method = RequestMethod.POST,consumes ="application/json",produces = "application/json")
    public IDMResponse  savepractitionerorders(@RequestBody PractitionerordersPojo saveDetails )  {
        Practitionerorders register=sassControlPanelService.Savepracorders(saveDetails);
        return new IDMResponse(HttpStatus.OK.value(), " success",register);
    }
    @RequestMapping(value = "/pracorderList",
            method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public IDMResponse pracorderList(){
        List<PractitionerordersPojo> order= sassControlPanelService.practorderList();
        return new IDMResponse(HttpStatus.OK.value(), "success",order);
    }

    @RequestMapping(value = "/saveCartRegistration",method = RequestMethod.POST,consumes ="application/json",produces = "application/json")
    public IDMResponse  saveCartRegistration(@RequestBody CartRegistrationPojo saveCartRegDetails )  {
        Cartregistration cartregistration=sassControlPanelService.saveCartRegistration(saveCartRegDetails);
        return new IDMResponse(HttpStatus.OK.value(), " success",cartregistration);
    }
    @RequestMapping(value = "/getCartRegList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public IDMResponse getCartRegList(){
        List<CartRegistrationPojo> cartRegistrationPojos= sassControlPanelService.cartRegistrationPojoList();
        return new IDMResponse(HttpStatus.OK.value(), " success",cartRegistrationPojos);
    }
    @RequestMapping(value = "/getPermissionRights",method = RequestMethod.POST,produces="application/json")
    public ResponseEntity getPermissionRights() {
        HashMap<Object,Object> companyAccessRightsDTO = sassControlPanelService.retriveCompanyPermissions();
        return ResponseEntity.status(200).body(companyAccessRightsDTO);
    }
    @RequestMapping(value = "/savedestination",method = RequestMethod.POST,consumes ="application/json")
    public IDMResponse savedestination(@RequestBody DestinationTypePojo savedestination )  {
        DestinationType destination=sassControlPanelService.SaveDestination(savedestination);
        return new IDMResponse(HttpStatus.OK.value(), " success",destination);
    }
    @RequestMapping(value = "/saveNewItem",
            method = RequestMethod.POST,consumes = "application/json",
            produces = "application/json")
    public IcItem saveNewItem(@RequestBody IcItempojo saveItemDetails )  {
        return sassControlPanelService.SaveItem(saveItemDetails);
    }

    @RequestMapping(value = "/detinationtypeList{sourceType}",
            method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public IDMResponse detinationtypeList(@PathVariable String sourceType){
        List<DestinationTypePojo> desttype= sassControlPanelService.destinationList(sourceType);
        return new IDMResponse(HttpStatus.OK.value(), "success",desttype);
    }
    @RequestMapping(value = "/detinationtypeList",
            method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public IDMResponse detinationtypeList(){
        List<DestinationTypePojo> desttype= sassControlPanelService.destinationtype();
        return new IDMResponse(HttpStatus.OK.value(), "success",desttype);
    }
    @RequestMapping(value = "/savedestinationmap",method = RequestMethod.POST,consumes ="application/json")
    public IDMResponse savedestinationmap(@RequestBody DestinationMapPojo savedestination )  {
        DestinationMap destination=sassControlPanelService.SaveDestinationMap(savedestination);
        return new IDMResponse(HttpStatus.OK.value(), " success",destination);
    }
    @RequestMapping(value = "/detinationtypeMapList",
            method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public IDMResponse detinationtypeMapList(){
        List<DestinationMapPojo> destmap= sassControlPanelService.destinationMapList();
        return new IDMResponse(HttpStatus.OK.value(), "success",destmap);
    }
    @RequestMapping(value = "/saveRTRSyncDetails",method = RequestMethod.POST,consumes ="application/json")
    public IDMResponse saveRTRSyncDetails(@RequestBody RTRSyncSettingsPojo rtrSyncSettingsPojo )  {
        RTRSyncSettings rtrSyncSettings=sassControlPanelService.saveRTRSyncsettingDetails(rtrSyncSettingsPojo);
        return new IDMResponse(HttpStatus.OK.value(), " success",rtrSyncSettings);
    }
    @RequestMapping(value = "/getRTRSyncList",
            method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public IDMResponse getRTRSyncList(@RequestParam(value = "SearchText")String SearchText){
        List<RTRSyncSettingsPojo> rtrSyncsettingList= sassControlPanelService.getRTRSyncsettingList(SearchText);
        return new IDMResponse(HttpStatus.OK.value(), "success",rtrSyncsettingList);
    }
}
