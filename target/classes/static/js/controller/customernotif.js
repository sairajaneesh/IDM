app.controller('customernotifCtrl',
    function ($scope, $rootScope, $http, $location, $filter, Notification) {
        $scope.hiposServerURL = "/services";





        $scope.addcustnotif = function () {
            $("#add_new_cust_notif_modal").modal('show');

        };

        $scope.getcustnotifList = function () {
            $http.post($scope.hiposServerURL  + "/customernotifyList").then(function (response) {
                var data = response.data.object;
                $scope.customernotify= data;
            },function (error) {
                Notification.error({message: 'Something went wrong, please try again', positionX: 'center', delay: 2000});
            })
        };

        $scope.getcustnotifList();



    });