app.controller('subscriptionCtrl',
    function ($scope, $rootScope, $http, $location, $filter, Notification) {
        $scope.hiposServerURL = "/services";
        $scope.permsubscription=[];


        $scope.removeSubscriptionDetails = function () {
            $scope.subscriptionNameText="";
            $scope.validityText="";
            $scope.validityTypeText="";
            $scope.subscriptionForText="";
            $scope.orgnlchrgText="";
            $scope.versionText="";
            $scope.permissionText="";
            $scope.addonPerText="";
            $scope.users="";
            $scope.companies="";
            $scope.subscriptionId="";
        };
        $scope.editsubscription = function (data) {
            $scope.subscriptionId=data.subscriptionId;
            $scope.subscriptionNameText = data.subscriptionName;
            $scope.validityText = data.validity;
            $scope.validityTypeText = data.validityType;
            $scope.subscriptionForText = data.subscriptionFor;
            $scope.orgnlchrgText = data.originalCharge;
            $scope.versionText = data.version;
            $scope.permissionText = data.permission;
            $scope.addonPerText = data.addonPermission;
            $scope.users = data.users;
            $scope.companies = data.companies;
            $scope.operation = 'Edit';
            $('#title').text("Edit Subscription");
            $("#add_new_subscription_modal").modal('show');
        };


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
        $scope.addNewSubscription = function () {
            $scope.subscriptionNameText="";
            $scope.validityText="";
            $scope.validityTypeText="";
            $scope.subscriptionForText="";
            $scope.subsStatusText="Active";
            $scope.orgnlchrgText="";
            $scope.versionText="";
            $scope.permissionText="";
            $scope.addonPerText="";
            $scope.statusText="Active"
            $scope.removeSubscriptionDetails();
            $("#title").text("Add Subscription");
            $("#add_new_subscription_modal").modal('show');
        };
        $scope.selectedList={};
        $scope.addData=function (status,name) {
            $scope.selectedList[name]=status;
        }
        $scope.getPermissions = function () {
            $http.post($scope.hiposServerURL + "/getPermissionRights").then(function (response) {
                var data =response.data;
                $scope.permissionList =data;
                $("#userPermission3").modal('show');
                // $("#Permissions").modal('show');
                console.log(data);
            })
        }
        $scope.list={};

        $scope.permissionEvent = function(current,event) {
            if (current == "Active") {
                angular.forEach($scope.permissionList, function (value, key) {
                    if (event.target.parentElement.parentElement.parentElement.id.substring(7) == key) {
                        value.status = 'Active';
                        angular.forEach(value.children, function (value1, key1) {
                            value1.status = 'Active';
                            angular.forEach(value1.children, function (value2, key2) {
                                value2.status = 'Active';
                                angular.forEach(value2.children, function (value3, key3) {
                                    value3.status = 'Active';
                                    angular.forEach(value3.children, function (value4, key4) {
                                        value4.status = 'Active';
                                      angular.forEach(value4.children, function (value5, key5) {
                                        value5.status = 'Active';
                                        angular.forEach(value5.children, function (value6, key6) {
                                            value6.status = 'Active';
                                        })
                                      })
                                    })
                                })
                            })
                        })
                    }else{

                        if (event.target.id.substring(6) == key) {
                            value.status = 'Active';
                            return;
                        }
                        angular.forEach(value.children, function (value1, key1) {
                            if (event.target.id.substring(6) == key1) {
                                value1.status = 'Active';
                                value.status = 'Active';
                                return;
                            }
                        })

                        angular.forEach(value.children, function (value1, key1) {
                            angular.forEach(value1.children, function (value2, key2) {
                                if (event.target.id.substring(6) == key2) {
                                    value.status = 'Active';
                                    value1.status = 'Active';
                                    value2.status = 'Active';
                                    return;
                                }
                            })
                        })

                        angular.forEach(value.children, function (value1, key1) {
                            angular.forEach(value1.children, function (value2, key2) {
                                angular.forEach(value2.children, function (value3, key3) {
                                    if (event.target.id.substring(6) == key3) {
                                        value.status = 'Active';
                                        value1.status = 'Active';
                                        value2.status = 'Active';
                                        value3.status = 'Active';
                                        return;
                                    }
                                })
                            })
                        })

                        angular.forEach(value.children, function (value1, key1) {
                            angular.forEach(value1.children, function (value2, key2) {
                                angular.forEach(value2.children, function (value3, key3) {
                                    angular.forEach(value3.children, function (value4, key4) {
                                        if (event.target.id.substring(6) == key4) {
                                            value1.status = 'Active';
                                            value2.status = 'Active';
                                            value3.status = 'Active';
                                            value4.status = 'Active';
                                            return;
                                        }
                                    })
                                })
                            })
                        })
                        angular.forEach(value.children, function (value1, key1) {
                            angular.forEach(value1.children, function (value2, key2) {
                                angular.forEach(value2.children, function (value3, key3) {
                                    angular.forEach(value3.children, function (value4, key4) {
                                        angular.forEach(value4.children, function (value5, key5) {
                                        if (event.target.id.substring(6) == key4) {
                                            value2.status = 'Active';
                                            value3.status = 'Active';
                                            value4.status = 'Active';
                                            value5.status = 'Active';
                                            return;
                                        }
                                        })
                                    })
                                })
                            })
                        })
                        angular.forEach(value.children, function (value1, key1) {
                            angular.forEach(value1.children, function (value2, key2) {
                                angular.forEach(value2.children, function (value3, key3) {
                                    angular.forEach(value3.children, function (value4, key4) {
                                        angular.forEach(value4.children, function (value5, key5) {
                                            angular.forEach(value5.children, function (value6, key6) {
                                            if (event.target.id.substring(6) == key4) {
                                                value3.status = 'Active';
                                                value4.status = 'Active';
                                                value5.status = 'Active';
                                                value6.status = 'Active';
                                                return;
                                              }
                                            })
                                        })
                                    })
                                })
                            })
                        })
                    }
                })
            }

            if(current == "InActive"){
                angular.forEach($scope.permissionList,function (value,key) {
                    if (event.target.parentElement.parentElement.parentElement.id.substring(7) == key) {
                        value.status = 'InActive';
                        angular.forEach(value.children, function (value1, key1) {
                            value1.status = 'InActive';
                            angular.forEach(value1.children, function (value2, key2) {
                                value2.status = 'InActive';
                                angular.forEach(value2.children, function (value3, key3) {
                                    value3.status = 'InActive';
                                    angular.forEach(value3.children, function (value4, key4) {
                                        value4.status = 'InActive';
                                    angular.forEach(value4.children, function (value5, key5) {
                                        value5.status = 'InActive';
                                        angular.forEach(value5.children, function (value6, key6) {
                                            value6.status = 'InActive';
                                        })
                                    })
                                    })
                                })
                            })
                        })
                    }
                });

            }else if(current == "Restrict"){
                angular.forEach($scope.permissionList,function (value,key) {
                    if (event.target.parentElement.parentElement.parentElement.id.substring(7) == key) {
                        value.status = 'Restrict';
                        angular.forEach(value.children, function (value1, key1) {
                            value1.status = 'Restrict';
                            angular.forEach(value1.children, function (value2, key2) {
                                value2.status = 'Restrict';
                                angular.forEach(value2.children, function (value3, key3) {
                                    value3.status = 'Restrict';
                                    angular.forEach(value3.children, function (value4, key4) {
                                        value4.status = 'Restrict';
                                        angular.forEach(value4.children, function (value5, key5) {
                                            value5.status = 'Restrict';
                                            angular.forEach(value5.children, function (value6, key6) {
                                                value6.status = 'Restrict';
                                            })
                                        })
                                    })
                                })
                            })
                        })
                    }
                });
            }
        }

        $scope.addlist=function () {
            angular.forEach($scope.permissionList,function (value,key) {
                if(value.status=='Active') {
                    $scope.list[value.permissionString]=value.status;
                }else if(value.status=='Restrict'){
                    $scope.list[value.permissionString]="Restrict";
                }
                else{
                    $scope.list[value.permissionString]="InActive";
                }
                angular.forEach(value.children,function (value1,key1) {
                    if(value1.status=='Active') {
                        $scope.list[value1.permissionString]=value1.status;
                    }
                    else if(value1.status=='Restrict'){
                        $scope.list[value1.permissionString]="Restrict";
                        $scope.list[value1.permissionString]=value1.status;
                    }
                    else
                    {
                        $scope.list[value.permissionString]="InActive";
                        $scope.list[value1.permissionString]=value1.status;
                    }
                    angular.forEach(value1.children,function (value2,key2) {
                        if(value2.status=='Active') {
                            $scope.list[value2.permissionString]=value2.status;
                        }
                        else if(value2.status=='Restrict'){
                            $scope.list[value2.permissionString]="Restrict";
                            $scope.list[value2.permissionString]=value2.status;
                        }
                        else{
                            $scope.list[value.permissionString]="InActive";
                            $scope.list[value2.permissionString]=value2.status;
                        }
                        angular.forEach(value2.children,function (value3,key3) {
                            if(value3.status=='Active') {
                                $scope.list[value3.permissionString]=value3.status;
                            }
                            else if(value3.status=='Restrict'){
                                $scope.list[value3.permissionString]="Restrict";
                                $scope.list[value3.permissionString]=value3.status;
                            }
                            else {
                                $scope.list[value.permissionString]="InActive";
                                $scope.list[value3.permissionString]=value3.status;
                            }
                            angular.forEach(value3.children,function (value4,key4) {
                                if(value4.status=='Active') {
                                    $scope.list[value4.permissionString] = value4.status;
                                }
                                else if(value4.status=='Restrict'){
                                    $scope.list[value4.permissionString]="Restrict";
                                    $scope.list[value4.permissionString] = value4.status;
                                }
                                else{
                                    $scope.list[value.permissionString]="InActive";
                                    $scope.list[value4.permissionString] = value4.status;
                                }
                                angular.forEach(value4.children,function (value5,key5) {
                                    if(value5.status=='Active') {
                                        $scope.list[value5.permissionString] = value5.status;
                                    }
                                    else if(value5.status=='Restrict'){
                                        $scope.list[value5.permissionString]="Restrict";
                                        $scope.list[value5.permissionString] = value5.status;
                                    }
                                    else{
                                        $scope.list[value.permissionString]="InActive";
                                        $scope.list[value5.permissionString] = value5.status;
                                    }
                                    angular.forEach(value5.children,function (value6,key6) {
                                        if(value6.status=='Active') {
                                            $scope.list[value6.permissionString] = value6.status;
                                        }
                                        else if(value6.status=='Restrict'){
                                            $scope.list[value6.permissionString]="Restrict";
                                            $scope.list[value6.permissionString] = value6.status;
                                        }
                                        else{
                                            $scope.list[value.permissionString]="InActive";
                                            $scope.list[value6.permissionString] = value6.status;
                                        }
                                    })
                                })
                            })
                        })
                    })
                })
            })
        }

        $('input[type="radio"]').on('change', function() {
            $('.child').prop("checked", false); // Reset all child checkbox
            // Check if it's a parent or child being checked
            if ($(this).hasClass('parent')) {
                $('.child').prop('required', false); // Set all children to not required
                $(this).next('ul').find('.child').prop('required', true);  // Set the children of the selected parent to required
            } else if ($(this).hasClass('child')) {
                $(this).prop("checked", true); // Check the selected child
                $(this).parent().parent().prev('.parent').prop('checked', true); // Check the selected parent
            }
        });
        $scope.saveNewSubscription = function () {
            $scope.addlist()
            if (angular.isUndefined($scope.subscriptionNameText) || $scope.subscriptionNameText == '') {
                Notification.warning({message: 'Subscription Name can not be Empty', positionX: 'center', delay: 2000});
            }
            else{
                var saveSubscriptionDetails;
                saveSubscriptionDetails = {
                    subscriptionId:$scope.subscriptionId,
                    subscriptionName: $scope.subscriptionNameText,
                    validity: $scope.validityText,
                    validityType: $scope.validityTypeText,
                    subscriptionFor:$scope.subscriptionForText,
                    status:$scope.statusText,
                    originalCharge:$scope.orgnlchrgText,
                    version:$scope.versionText,
                    permission:angular.toJson($scope.list),
                    addonPermission:$scope.addonPerText,
                    users:$scope.users,
                    companies:$scope.companies,
                    hiSync:$scope.hiSync,
                    hiAccount:$scope.hiAccount
                };

                console.log(permission);
                $http.post("/services/saveNewSubscription" , angular.toJson(saveSubscriptionDetails)).then(function (response) {
                    var data = response.data;
                    if(data==""){
                        Notification.error({message: 'Already exists', positionX: 'center', delay: 2000});
                    }

                    else {
                        $("#add_new_subscription_modal").modal('hide');
                        Notification.success({message: 'Subscription Created  successfully', positionX: 'center', delay: 2000});
                        $scope.removeSubscriptionDetails();
                        $scope.getSubscriptionList();
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

        $(".check_span_info_1").on("click", function() {
            $(".check_span_info").prop("selected", $(this).prop("selected"));
        });
        // $scope.getPermissionMasterList = function () {
        //     $(".loader").css("display", "block");
        //     $http.get($scope.hiposServerURL  + "/getPermissionMasterList").then(function (response) {
        //          var data = response.data;
        //             $scope.permissionMasterList = angular.copy(data);
        //          //   $scope.status ="Active";
        //             $("#selectPermissionMaster").modal('show');
        //         }, function (error) {
        //             Notification.error({
        //                 message: 'Something went wrong, please try again',
        //                 positionX: 'center',
        //                 delay: 2000
        //             });
        //         }
        //     )
        // };

    });