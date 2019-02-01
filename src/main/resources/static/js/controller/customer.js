app.controller('customerCtrl',
    function ($scope, $rootScope, $http, $location, $filter, Notification) {
        $scope.hiposServerURL = "/services";
        $scope.word = /^[a-z]+[a-z0-9._]+@[a-z]+\.[a-z.]{2,5}$/;
        $scope.customerId = 1;
        $scope.userRights = [];
        $scope.operation = 'Create';
        $scope.customer = 1;
        $scope.today = new Date();


        $scope.removeCustomerDetails = function () {
            $scope.custNameText = "";
            $scope.customerEmailText = "";
            $scope.customerContactText = "";
            $scope.customerAddressText = "";
            $scope.creditLimitText = "";
            $scope.creditTermText = "";
            $scope.fromRegNo = "";
            $scope.gstText = "";
            $scope.accNoText = "";
            $scope.bankNameText = "";
            $scope.IFSCText = "";
            $scope.bankBranchText = "";
            $scope.personInchargeText = "";
            $scope.panNumberText = "";
            $scope.websiteText = "";
            $scope.custStatusText = "";

        };

        $scope.addNewCustomer = function () {
            $scope.custNameText = "";
            $scope.customerEmailText = "";
            $scope.customerContactText = "";
            $scope.customerAddressText = "";
            $scope.creditLimitText = "";
            $scope.creditTermText = "";
            $scope.fromRegNo = "";
            $scope.gstText = "";
            $scope.accNoText = "";
            $scope.bankNameText = "";
            $scope.IFSCText = "";
            $scope.bankBranchText = "";
            $scope.personInchargeText = "";
            $scope.panNumberText = "";
            $scope.websiteText = "";
            $scope.custStatusText = "Active";
            $http.post("/services/getCountry").then(function (response) {
                var data = response.data.object;
                $scope.countryPojoList=data;
            },function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            });
            $('#customer-title').text("Add Customer");
            $("#add_new_Customer_modal").modal('show');

        };

        $scope.getCurrencyList = function () {
            $(".loader").css("display", "block");
            $http.post($scope.hiposServerURL  + "/getPaginatedCurrencyList").then(function (response) {
                var data = response.data.object;
                console.log(data);
                $scope.currencyList = data;

            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            })

        };
     $scope.getCurrencyList();


        $scope.getstateList = function () {
            $http.post($scope.hiposServerURL  + "/stateList").then(function (response) {
                var data = response.data.object;
                $scope.stateList= data;
            },function (error) {
                Notification.error({message: 'Something went wrong, please try again', positionX: 'center', delay: 2000});
            })
        };

        $scope.getstateList();

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

     $scope.saveCustomer=function () {

    if (angular.isUndefined($scope.custNameText) || $scope.custNameText == '') {
        Notification.warning({message: 'Customer Name can not be Empty', positionX: 'center', delay: 2000});

    }

    else{
        var saveCustomerDetails;
        saveCustomerDetails = {
            customerName: $scope.custNameText,
            customerNumber: $scope.customerContactText,
            email:$scope.customerEmailText,
            gstCode:$scope.gstText,
            bankName:$scope.bankNameText,
            accountNo:$scope.accNoText,
            iFSCCode:$scope.IFSCText,
            branchName:$scope.bankBranchText,
            personIncharge:$scope.personInchargeText,
            panNO:$scope.panNumberText,
            website:$scope.websiteText,
            address:$scope.customerAddressText,
            creditedLimit:$scope.creditTermText,
            status:$scope.custStatusText,
            countryId:$scope.countryId,
            currencyId:$scope.currencyId,
            stateId:$scope.stateId


        };


        $http.post("/services/saveNewCustomer",angular.toJson(saveCustomerDetails)).then(function (response) {
            var data = response.data;
            if(data==""){
                Notification.error({message: 'Email Already exists', positionX: 'center', delay: 2000});
            }
            else {
                $("#add_new_Customer_modal").modal('hide');
                Notification.success({message: 'Customer Created  successfully', positionX: 'center', delay: 2000});
                $scope.removeCustomerDetails();
                $scope.getCustomerList();
            }
        },function (error) {
            Notification.error({
                message: 'Something went wrong, please try again',
                positionX: 'center',
                delay: 2000
            });
        });
    }

    };

    });
