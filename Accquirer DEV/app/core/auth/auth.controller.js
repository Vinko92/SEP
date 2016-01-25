(function(){
	'use strict';
	
	angular.module('merchant.core')
			.controller('AuthController',authController);
			
	authController.$inject = ['$location','$scope','localStorageService','auth.service','serverUrl'];
	
	function authController($location,$scope,localStorageService,service,serverUrl){
		var self = this;
		
		self.test = function(){
			service.test();
		};
		
		var authentication = {
			isLoggedIn : false,
			username : ""
		};
		
		
		self.register = function(){
			service.register(registration)
					.success(function(response){
						console.log('Successfully registered');
					})
					.error(function(error){
						console.log('error');
					});
		};
		
		self.login = function(){
			/*if(isValid(loginData)){
				var deferred = $q.defer();
				
				$http.post(serverUrl +'/login', login)
				     .success(function(response){
						 authentication.isLoggedIn = true;
						 authentication.username = login.username;
						 deferred.resolve(response);
					 })
					 .error(function(error){
						 deferred.reject(error);
					 });
					 return deferred.promise;
			}*/
					 
		    authentication.isLoggedIn = true;
		    authentication.username = loginData.username;
		    $location.path('/');
			
			
		};
		
		self.logout = function(){
			service.logout();
		};
		
	}
})();