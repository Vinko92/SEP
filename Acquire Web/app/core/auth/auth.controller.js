(function(){
	'use strict';
	
	angular.module('safeguard.core')
			.controller('AuthController',authController);
			
	authController.$inject = ['$location','$scope','$http','localStorageService','auth.service','serverUrl'];
	
	function authController($location,$scope,$http,localStorageService,service,serverUrl){
		var self = this;
		
		var authentication = {
			isLoggedIn : false,
			username : ""
		};
		
		
		self.register = function(){
			var response = service.register($scope.registerData);
			response.then(function(){
				console.log('Successfully registered');
				$location.path('/card-details');
			},
			function(){
				console.log('Unable to register');
			});
			
		};
		
		self.login = function(){
			$http.post(serverUrl +'/login', $scope.loginData)
			   .success(function(response){
					 localStorageService.set('username',$scope.loginData.username);
					 $location.path('/card-details');
				 })
				 .error(function(response){
					 console.log(response.message[0]);
					 $scope.errors = response.message;
				 });
			
		};
		
		
		self.logout = function(){
			service.logout();
		};
		
	}
})();