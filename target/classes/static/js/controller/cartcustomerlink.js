
app.controller('cartcustomerlinkCtrl',
    function ($scope, $rootScope, $http, $location, $filter, Notification) {
        $scope.hiposServerURL = "/services";
        $scope.customerId = 1;
        $scope.userRights = [];
        $scope.operation = 'Create';
        $scope.customer = 1;
        $scope.today = new Date();
        $scope.today = function() {
            $scope.activDate = new Date();
            $scope.expiryDate = new Date();
        };
        $scope.today();
        $scope.format = 'dd/MM/yyyy';

        $scope.open1 = function() {
            $scope.popup1.opened = true;
        };

        $scope.popup1 = {
            opened: false
        };

        $scope.open2 = function() {
            $scope.popup2.opened = true;
        };

        $scope.isFilterApplied = false;
        $scope.popup2 = {
            opened: false
        };
        $scope.editCartCustomer = function (data) {
            $scope.cartCustId=data.cartCustId;
            $scope.clientsecret=data.clientSecret;
            $scope.clientidText = data.clientId;
            $scope.urlText = data.url;
            $scope.customerId = data.customerId;
            $scope.expiryDate = data.expiryDate;
            $scope.activDate = data.activationDate;
            $scope.cartId = data.cartId;
            $scope.StatusText = data.status;
            $scope.operation = 'edit';
            $('#title').text("Edit Customer");
            $("#add_new_cartcustomer_modal").modal('show');
        };

        $scope.removeCartCustomerDetails = function () {
            $scope.cartCustId="";
            $scope.clientidText="";
            $scope.clientsecret="";
            $scope.customerId="";
            $scope.cartId="";
            $scope.urlText="";
            $scope.clientid="";
        };

        $scope.addNewCartCustomer = function () {
            $scope.clientsecret="";
            $scope.clientidText="";
            $scope.StatusText="Active";
            $scope.removeCartCustomerDetails();
            $("#title").text("Add Customer");
            $("#add_new_cartcustomer_modal").modal('show');
        };

        $scope.getCartCustomerList = function (val) {
            $(".loader").css("display", "block");
            if (angular.isUndefined(val)) {
                val = "";
            }
            $http.post($scope.hiposServerURL  + "/getCartCustomerList?SearchText=" + val).then(function (response) {
                var data = response.data.object;
                console.log(data);
                $scope.cartCustomerList = data;

            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            })

        };

        $scope.getCartCustomerList();

        $scope.getCustomerList = function (val) {
            $(".loader").css("display", "block");
            if (angular.isUndefined(val)) {
                val = "";
            }
            $http.post($scope.hiposServerURL  + "/getCustomerList?SearchText=" + val).then(function (response) {
                var data = response.data.object;
                console.log(data);
                $scope.customerList = data;

            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            })

        };

        $scope.getCustomerList();

        $scope.getCartRegList = function () {
            $(".loader").css("display", "block");
            $http.post($scope.hiposServerURL  + "/getCartRegList").then(function (response) {
                var data = response.data.object;
                console.log(data);
                $scope.cartRegList = data;

            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            })

        };

        $scope.getCartRegList();


        $scope.saveNewCartCustomer = function () {
                var saveCartCustomerDetails;
            saveCartCustomerDetails = {
                clientId: $scope.clientid,
                cartCustId: $scope.cartCustId,
                clientSecret: $scope.clientsecret,
                status: $scope.currencyStatusText,
                cartId:$scope.cartId,
                customerId:$scope.customerId,
                url:$scope.urlText,
                activationDate:$scope.activDate,
                expiryDate:$scope.expiryDate,
                status:$scope.StatusText
                };
                $http.post("/services/saveCartCustomer",angular.toJson(saveCartCustomerDetails)).then(function (response) {
                    var data = response.data;
                    if(data==""){
                        Notification.error({message: 'Already exists', positionX: 'center', delay: 2000});
                    }
                    else {

                        $scope.removeCartCustomerDetails();
                        $scope.getCartCustomerList();
                        $("#add_new_cartcustomer_modal").modal('hide');
                        Notification.success({message: 'CartCustomer Created  successfully', positionX: 'center', delay: 2000});

                    }
                },function (error) {
                    Notification.error({
                        message: 'Something went wrong, please try again',
                        positionX: 'center',
                        delay: 2000
                    });
                });
        };

    });
