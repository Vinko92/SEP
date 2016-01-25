(function(){
	'use strict';
	
	angular.module('merchant.core').factory('auth.service',['$http','$location','$q','localStorageService','serverUrl', function ($http,$location, $q,localStorageService, serverUrl) {

    var _authentication = {
        isLoggedIn: false,
        userName: "",
    };
	
	var test = function(){
		localStorageService.clearAll();
		localStorageService.set('username','new test');
		alert(localStorageService.get('username'));
	};
	
	var register = function(registrationData){
			return $http.post(serverUrl + '/register', registrationData).then(function(response){
				return response;
			});
		};
		
	var logIn = function(loginData){
			if(isValid(loginData)){
				var deferred = $q.defer();
				
				$http.post(serverUrl +'/login', loginData)
				     .success(function(response){
						 authentication.isLoggedIn = true;
						 authentication.username = loginData.username;
						 localStorageService.set('authorizationData',{username:loginData.username});
						 deferred.resolve(response);
					 })
					 .error(function(error){
						 deferred.reject(error);
					 });
				return deferred.promise;
			}	
		};
	
	
	var logOut = function(){
		if(localStorageService.isSupported){
			localStorageService.clearAll();
			console.log('Good by' + _authentication.userName);
			_authentication.userName = "";
			_authentication.isLoggedIn = false;
			$location.path('/');
		}
	};

	
	
	return{
		register: register,
		login: logIn,
		isLoggedIn: _authentication.isLoggedIn,
		logout:logOut,
		test:test
	};
	}]);
	
}());