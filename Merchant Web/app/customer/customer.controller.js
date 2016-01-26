(function(){
	'use strict';
	
	angular.module('merchant.customer')
			.controller('CustomerController',CustomerController);
			
	CustomerController.$inject = ['$location','$scope','customer.service','serverUrl'];
	function CustomerController($location,$scope,service,serverUrl){
		var self = this;
		$scope.successMessages = [];
		$scope.errorMessages = [];
		self.message = "Help us get to know you!";
		
		var customer = {
			  'name': $scope.name,
			  'surname':$scope.surname,
			  'username':$scope.username,
			  'password':$scope.password,
			  'jmbg':$scope.idNumber,
			  'passportNumber':$scope.passportNumber,
			  'address':$scope.address,
			  'phoneNumber':$scope.phoneNumber,
			  'accountNumber':$scope.accountNumber,
			  'email':$scope.email,
			  "owner":false,
			  'age':$scope.age
			  };
		
		self.register = function(){
			service.insertCustomer(serverUrl,customer).then(onSuccessfullRegistration,onErrorRegistration);
		};
			
		var onSuccessfullRegistration = function(){
			$scope.successMessages.push('You have been registered successfully.');
			window.location.href = '#/home';
		};
		var onErrorRegistration = function(){
			$scope.errorMessages.push('Invalid attempt. Please insert valid data.');
			alert($scope.errorMessage);
		};
		
	}
})();