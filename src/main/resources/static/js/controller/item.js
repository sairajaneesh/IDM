app.controller('itemCtrl',
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

        $scope.addNewItem = function () {
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
            $('#Item-title').text("Add Item");
            $("#add_new_Item_modal").modal('show');

        };

        $scope.getIcItemList = function () {
            $(".loader").css("display", "block");
            $http.post($scope.hiposServerURL  + "/getItemList").then(function (response) {
                var data = response.data.object;
                console.log(data);
                $scope.ictemList = data;

            }, function (error) {
                Notification.error({
                    message: 'Something went wrong, please try again',
                    positionX: 'center',
                    delay: 2000
                });
            })

        };

        $scope.getIcItemList();

        $scope.saveItem=function () {
                var saveItemDetails;
                saveItemDetails = {
                    itemCode: $scope.itemCodeText,
                    itemName: $scope.itemNameText,
                    itemDesc:$scope.itemDescText,
                    stock:$scope.stockText,
                    discountAmountRpercent:$scope.discountAmountRpercentText,
                    isDiscountInPercent:$scope.DiscountInPercentText,
                    salesPrice:$scope.salesPriceText,
                    purchasePrice:$scope.purchasePriceText,
                    itemTypeName:$scope.itemTypeNameText,
                    itemCategoryName:$scope.itemCategoryNameText,
                    brandName:$scope.brandNameText,
                    itemStatus:$scope.status,
                    inclusiveJSON:$scope.inclusiveJSONText,
                    hsnCode:$scope.hsnCodeText,
                    unitPrice:$scope.unitPriceText,
                    unitPriceIn:$scope.unitPriceInText,
                    qty:$scope.qtyText,
                    cess:$scope.cessText,
                    inventoryMovementName:$scope.inventoryMovementNameText,
                    barCode:$scope.barCodeText,
                    fileName:$scope.fileNameText,
                    discountAmt:$scope.discountAmtText


                };


                $http.post("/services/saveNewItem",angular.toJson(saveItemDetails)).then(function (response) {
                    var data = response.data;
                    if(data==""){
                        Notification.error({message: 'Email Already exists', positionX: 'center', delay: 2000});
                    }
                    else {
                        $("#add_new_Item_modal").modal('hide');
                        Notification.success({message: 'Item Created  successfully', positionX: 'center', delay: 2000});
                        // $scope.removeCustomerDetails();
                        $scope.getIcItemList();
                    }
                },function (error) {
                    Notification.error({
                        message: 'Something went wrong, please try again',
                        positionX: 'center',
                        delay: 2000
                    });
                });
            }



    });
