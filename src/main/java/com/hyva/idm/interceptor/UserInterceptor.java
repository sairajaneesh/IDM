//package com.hyva.idm.interceptor;
//
//import com.hyva.idm.sass.sassendpoints.SassAuthorizationEndpoint;
//import com.hyva.idm.sass.sassentities.SassUser;
//import com.hyva.idm.sass.sassrespositories.SassUserRepository;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.CookieValue;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Arrays;
//import java.util.Map;
//import java.util.Optional;
//import java.util.stream.Stream;
//
//
//
//@Component
//public class UserInterceptor extends HandlerInterceptorAdapter {
//    @Autowired
//    SassUserRepository sassUserRepository;
//
//    private final Logger logger = Logger.getLogger(getClass());
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("request = " + request.getRequestURL());
//        Cookie[] accessTokens = request.getCookies();
//        Cookie accessTokenCookie = null;
//        String accessToken = null;
//        if (accessTokens != null) {
//            Stream<Cookie> cookieStream = Arrays.stream(accessTokens)
//                    .filter(cookie -> cookie.getName().equalsIgnoreCase("email"));
//            Optional<Cookie> first = cookieStream.findFirst();
//            if (first.isPresent()) {
//                accessTokenCookie = first.get();
//                accessToken = accessTokenCookie.getValue();
//            }
//        }
//
//
//        if (StringUtils.isBlank(accessToken)) {
//            response.setStatus(HttpStatus.UNAUTHORIZED.value());
//            return true;
//        }
//        Map<String, String> credentialsMap = null;
////        try {
//        SassUser sassUser =  sassUserRepository.findByEmail(accessToken);
////            credentialsMap = hiposService.validateToken(accessToken);//If token is not present in db or expired in db token time will be expired.
////            if (credentialsMap == null) {
////                accessTokenCookie.setPath("/");
////                accessTokenCookie.setMaxAge(0);
////                response.addCookie(accessTokenCookie);
////                response.getWriter().write("Session Expired");
////                return false;
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////            accessTokenCookie.setPath("/");
////            accessTokenCookie.setMaxAge(0);
////            response.getWriter().write("Session Expired");
////            response.addCookie(accessTokenCookie);
////            return false;
////        }
//        accessTokenCookie.setPath("/");
//        accessTokenCookie.setMaxAge(518400);//extending time for 60 days
//        response.addCookie(accessTokenCookie);
////        String dbKeyword = credentialsMap.get(HiNextConstants.DATABASE);
////        request.setAttribute("companyName", dbKeyword);
////        TenantContext.setCurrentTenant(dbKeyword);//setting tenant key for current thread.
////        String userId = credentialsMap.get("userId");
//        if(sassUser != null)
//        request.setAttribute("userId", sassUser.getUseraccountid());
//
//        return true;
//    }
//}
