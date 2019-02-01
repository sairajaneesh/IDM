    package com.hyva.idm.sass.sassendpoints;
import com.hyva.idm.constants.EndpointConstants;
import com.hyva.idm.pojo.IDMResponse;
import com.hyva.idm.sass.sassentities.SassCustomer;
import com.hyva.idm.sass.sassentities.SassUser;
import com.hyva.idm.sass.sasspojo.SassUserPojo;
import com.hyva.idm.sass.sassservice.SassOrdersService;
import com.hyva.idm.sass.sassservice.SassUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;


    /**
     * Created by azgar on 3/13/18.
     */
    @RestController
    @RequestMapping(EndpointConstants.SASS_ENDPOINT)
    public class SassAuthorizationEndpoint {
        @Autowired
        SassOrdersService sassOrdersService;
        @Autowired
        SassUserService sassUserService;
        @RequestMapping(value = "/login",method = RequestMethod.POST,
                produces = MediaType.APPLICATION_JSON_VALUE)
        public IDMResponse login(@RequestBody SassCustomer credentials) throws Exception {
            SassCustomer sassCustomer=sassOrdersService.getCustomerObj(credentials) ;
            String accessToken="12345";
            if (StringUtils.isBlank(credentials.getEmail()) ||StringUtils.isBlank(credentials.getLoginName()) || StringUtils.isBlank(credentials.getLoginPassword())) {
                return new IDMResponse(HttpStatus.OK.value(), "Invalid User");
            }
            return new IDMResponse(HttpStatus.OK.value(), "success",sassCustomer);
        }

//        @RequestMapping(value = "/logout")
//        public String logout(@CookieValue("accessToken") String accessToken) throws Exception {
//            if (!StringUtils.isBlank(accessToken)) {
//            hiposService.deleteToken(accessToken);
//            }
//            JSONObject myObject = new JSONObject();
//            myObject.put("message", "success");
//            return myObject.toString();
//        }
        @RequestMapping(value = "/saveLoginDetails", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
        public SassUser saveLoginDetails(@RequestBody SassUserPojo sassUserPojo)  {
            return sassUserService.saveUserDetails(sassUserPojo);
        }
        @RequestMapping(value = "/userValidate", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
        public SassUser userValidate(@RequestBody SassUserPojo sassUserPojo)  {
            return sassUserService.userValidate(sassUserPojo);
        }
        @RequestMapping(value = "/getUserDetailsList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
        public IDMResponse getUserDetailsList(){
            List<SassUserPojo> sassUserPojos= sassUserService.sassUserList();
            return new IDMResponse(HttpStatus.OK.value(), " success",sassUserPojos);
        }

    }
