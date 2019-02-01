app.controller('packageCtrl',
    function ($scope, $rootScope, $http, $location, $filter, Notification) {
        $scope.hiposServerURL = "/services";
        $scope.customerId = 1;
        $scope.userRights = [];
        $scope.operation = 'Create';
        $scope.customer = 1;
        $scope.today = new Date();

        $scope.removePackageDetails = function () {
            $scope.packagenameText="";
            $scope.redirecturlText="";
            $scope.syncurlText="";
            $scope.licncurlText="";
            $scope.cmpnyurlText="";
        };

        $scope.getPackageList = function () {
            $(".loader").css("display", "block");
            $http.post($scope.hiposServerURL  + "/getPackageList").then(function (response) {
                var data = response.data.object;
                console.log(data);
                $scope.packageList = data;

            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            })

        };

        $scope.getPackageList();
        $scope.addNewPackage = function () {
            $scope.packagenameText="";
            $scope.redirecturlText="";
            $scope.syncurlText="";
            $scope.licncurlText="";
            $scope.pkgStatusText="Active";
            $scope.cmpnyurlText="";
            $scope.removePackageDetails();
            $("#title").text("Add Package");
            $("#add_new_package_modal").modal('show');
        };
        $scope.saveNewPackage = function () {
            if (angular.isUndefined($scope.packagenameText) || $scope.packagenameText == '') {
                Notification.warning({message: 'Package Name can not be Empty', positionX: 'center', delay: 2000});
            }
            else{
                var savePackageDetails;
                savePackageDetails = {
                    packageName: $scope.packagenameText,
                    redirectUrl: $scope.redirecturlText,
                    syncUrl: $scope.syncurlText,
                    licncUrl:$scope.licncurlText,
                    status:$scope.pkgStatusText,
                    companyCreateurl:$scope.cmpnyurlText
                };
                $http.post("/services/saveNewPackage",angular.toJson(savePackageDetails)).then(function (response) {
                    var data = response.data;
                    if(data==""){
                        Notification.error({message: 'Already exists', positionX: 'center', delay: 2000});
                    }
                    else {
                        $("#add_new_package_modal").modal('hide');
                        Notification.success({message: 'Package Created  successfully', positionX: 'center', delay: 2000});
                        $scope.removePackageDetails();
                        $scope.getPackageList();
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
