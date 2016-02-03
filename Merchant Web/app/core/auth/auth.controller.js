(function(){
	'use strict';
	
	angular.module('merchant.core')
			.controller('AuthController',authController);
			
	authController.$inject = ['$location','$scope','localStorageService','auth.service','serverUrl'];
	
	function authController($location,$scope,localStorageService,service,serverUrl){
		var self = this;
		
		var authentication = {
			isLoggedIn : false,
			username : ""
		};
		
		
		self.register = function(){
			var response = service.register($scope.registerData);
			response.then(function(){
				console.log('Successfully registered');
				$location.path('/');
			},
			function(){
				console.log('Unable to register');
			});
			
		};
		
		self.login = function(){
			service.login($scope.loginData);
					 
		    authentication.isLoggedIn = true;
		    authentication.username = $scope.loginData.username;
		    $location.path('/');
			
			
		};
		
		self.logout = function(){
			service.logout();
		};
		
	}
})();