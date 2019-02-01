// var taskManagerModule = angular.module('taskManagerApp', ['ngRoute','ngAnimate']);
//

var app = angular.module('myApp',['ngRoute', 'ngAnimate',
    'ngSanitize','ui-notification','ngTable',
    'ngCookies', 'angular.filter','ui.bootstrap']);


app.config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when("/login", {
            templateUrl: "partials/login.html",
            controller: "loginController"
        })

        .when("/orderList", {
            templateUrl: "partials/orderList.html",
            controller: "orderListController"
        })
        .when("/paymentList", {
            templateUrl: "partials/payments.html",
            controller: "paymentListController"
        })

        .when("/permissionList", {
            templateUrl: "partials/permissionList.html",
            controller: "permissionListController"
        })
        .when("/countryList", {
            templateUrl: "partials/countryList.html",
            controller: "countryListController"
        })
        .when("/StateList", {
            templateUrl: "partials/StateList.html",
            controller: "StateListController"
        })
        .when("/controlPanel", {
            templateUrl: "partials/controlPanel.html",
            controller: "controlPanelController"
        })
        .when("/currency", {
            templateUrl: "partials/currency.html",
            controller: "currencyCtrl as rest"
        })
        .when("/paymentmethod", {
            templateUrl: "partials/paymentmethod.html",
            controller: "paymentMethodCtrl as rest"
        })
        .when("/versioncontrol", {
            templateUrl: "partials/versioncontrol.html",
            controller: "versionCtrl as rest"
        })
        .when("/subscription", {
            templateUrl: "partials/subscription.html",
            controller: "subscriptionCtrl as rest"
        })
        .when("/packages", {
            templateUrl: "partials/packages.html",
            controller: "packageCtrl as rest"
        })
        .when("/customer", {
            templateUrl: "partials/customer.html",
            controller: "customerCtrl as rest"
        })
        .when("/item", {
            templateUrl: "partials/item.html",
            controller: "itemCtrl as rest"
        })
        .when("/PractitionerRegistration", {
            templateUrl: "partials/PractitionerRegister.html",
            controller: "PractitionerRegistrationCtrl as rest"
        })
        .when("/addon", {
            templateUrl: "partials/addOn.html",
            controller: "addOnCtrl as rest"
        })
        .when("/Practitionerorders", {
            templateUrl: "partials/Practitionerorders.html",
            controller: "PractitionerordersCtrl as rest"
        })
        .when("/cartRegistration", {
            templateUrl: "partials/cartRegistration.html",
            controller: "cartRegCtrl as rest"
        })
        .when("/user", {
            templateUrl: "partials/user.html",
            controller: "userCtrl as rest"
        })
        .when("/cartcustomerlink", {
            templateUrl: "partials/cartcustomerlink.html",
            controller: "cartcustomerlinkCtrl as rest"
        })
        .when("/Hiconnector", {
            templateUrl: "partials/Hiconnector.html",
            controller: "HiconnectorCtrl as rest"
        })
        .when("/customernotification", {
            templateUrl: "partials/customernotif.html",
            controller: "customernotifCtrl as rest"
        })
        .when("/transactionsdata", {
            templateUrl: "partials/transdata.html",
            controller: "transdataCtrl as rest"
        })
        .when("/destinationType", {
            templateUrl: "partials/destinationType.html",
            controller: "destinationTypeCtrl as rest"
        })
        .when("/destinationMap", {
            templateUrl: "partials/destinationMap.html",
            controller: "destinationMapCtrl as rest"
        })
        .when("/rtrSyncSettings",{
            templateUrl:"partials/rtrSyncSettings.html",
            controller:"rtrSyncSettingsCtrl as rest"
        })
        .otherwise({redirectTo:'/login'});
}]);

