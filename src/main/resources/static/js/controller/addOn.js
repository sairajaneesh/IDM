
app.controller('addOnCtrl',
    function ($scope, $rootScope, $http, $location, $filter, Notification) {
        $scope.hiposServerURL = "/services";
        $scope.customerId = 1;
        $scope.userRights = [];
        $scope.operation = 'Create';
        $scope.customer = 1;
        $scope.today = new Date();

        $scope.removeAddOn = function () {
            $scope.addonNameText="";
            $scope.addonPriceText="";
            $scope.addonPerText="";
        };

        $scope.getAddOnList = function () {
            $(".loader").css("display", "block");
            $http.post($scope.hiposServerURL  + "/getAddOnList").then(function (response) {
                var data = response.data.object;
                console.log(data);
                $scope.addonList = data;

            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            })

        };

        $scope.getAddOnList();

        $scope.getSubscriptionList = function () {
            $(".loader").css("display", "block");
            $http.post($scope.hiposServerURL  + "/getSubscriptionList").then(function (response) {
                var data = response.data.object;
                console.log(data);
                $scope.subscriptionList = data;

            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            })

        };
        $scope.getSubscriptionList();

        $scope.addNewAddon = function () {
            $scope.addonNameText="";
            $scope.addonPriceText="";
            $scope.addonPerText="";
            $scope.itemStatusText="Active";
            $scope.removeAddOn();
            $("#title").text("Add");
            $("#add_new_addon_modal").modal('show');
        };

        ;
        $scope.saveAddOn = function () {
            if (angular.isUndefined($scope.addonNameText) || $scope.addonNameText == '') {
                Notification.warning({message: 'AddOn Name can not be Empty', positionX: 'center', delay: 2000});
            }
            else{
                var saveAddOnDetails;
                saveAddOnDetails = {
                    addonName: $scope.addonNameText,
                    addonPrice: $scope.addonPriceText,
                    addonPermission: $scope.addonPerText,
                    status:$scope.itemStatusText,
                    subscriptionId:$scope.subscriptionText
                };
                $http.post("/services/saveNewAddOn",angular.toJson(saveAddOnDetails)).then(function (response) {
                    var data = response.data;
                    if(data==""){
                        Notification.error({message: 'Already exists', positionX: 'center', delay: 2000});
                    }
                    else {
                        $("#add_new_addon_modal").modal('hide');
                        Notification.success({message: 'AddOn Created  successfully', positionX: 'center', delay: 2000});
                        $scope.removeAddOn();
                        $scope.getAddOnList();
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
