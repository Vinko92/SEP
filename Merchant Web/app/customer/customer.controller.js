(function(){
	'use strict';
	
	angular.module('merchant.customer')
			.controller('CustomerController',CustomerController);
			
	CustomerController.$inject = ['$location','$scope','$rootScope','localStorageService','customer.service','serverUrl'];
	function CustomerController($location,$scope,$rootScope,localStorageService,service,serverUrl){
		
		
		var self = this;
		$scope.successMessages = [];
		$rootScope.errorMessages = [];
		self.message = "Help us get to know you!";
		
	
		self.activeTab = function(activeTab)
		{
			$rootScope.activeTab = activeTab;
		}
			angular.element(document).ready(function(){
				// if(localStorageService.get('username'))
				// {
					var url = $location.url();
					var regex = /\/([A-z]*)-/i;
					var matches = regex.exec(url);
					if(matches){
						var activeTab = matches[1];
					
						if(activeTab.length > 0){
							$rootScope.activeTab = activeTab;
						}
					}
					else{
						$rootScope.activeTab = 'overview';
					}
				// }
				// else
				// {
					// $location.path('/login');
				// }
				
			});
			
		self.register = function(){
			service.insertCustomer(serverUrl,$scope.customer).then(onSuccessfullRegistration,onErrorRegistration);
		};

        var onSuccessfullRegistration = function(){
			$scope.successMessages.push('You have been registered successfully.');
			window.location.href = '#/customer/travel-insurance';
		};
		var onErrorRegistration = function(){
			$scope.errorMessages.push('Invalid attempt. Please insert valid data.');
			alert($scope.errorMessage);
		};

        self.travel = function(){
            service.insertTravelInsurance(serverUrl,$scope.travel).then(onSuccessfullTravelInsurance,onErrorTravelInsurance);
            console.log($scope.travel);
        }

        var onSuccessfullTravelInsurance = function(){
            $scope.successMessages.push('You have been bought successfully.');
            $scope.travelPrice = response.data;
            window.location.href = '#/customer/home-insurance';
        };
        var onErrorTravelInsurance = function(){
            $scope.errorMessages.push('Invalid attempt. Please insert valid data.');
            alert($scope.errorMessage);
        };

        self.home = function(){
            service.insertHomeInsurance(serverUrl,$scope.home).then(onSuccessfullHomeInsurance,onErrorHomeInsurance);
        }

        var onSuccessfullHomeInsurance = function(){
            $scope.successMessages.push('You have been bought successfully.');
            $scope.homePrice = response.data;
            window.location.href = '#/customer/vehicle-insurance';
        };
        var onErrorHomeInsurance = function(){
            $scope.errorMessages.push('Invalid attempt. Please insert valid data.');
            alert($scope.errorMessage);
        };

        self.vehicle = function(){
            service.insertVehicleInsurance(serverUrl,$scope.vehicle).then(onSuccessfullVehiclensurance,onErrorVehicleInsurance);
        }

        var onSuccessfullVehiclensurance = function(){
            $scope.successMessages.push('You have been bought successfully.');
            $scope.vehiclePrice = response.data;
            window.location.href = '#/customer/overview';
        };
        var onErrorVehicleInsurance = function(){
            $scope.errorMessages.push('Invalid attempt. Please insert valid data.');
            alert($scope.errorMessage);
        };

	}
})();