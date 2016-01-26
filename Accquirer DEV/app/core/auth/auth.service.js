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
			return $http.post(serverUrl + 'user/registration', registrationData).then(function(response){
				return response;
			});
		};
		
	var logIn = function(data){
				var deferred = $q.defer();
				console.log(data);
				$http.post(serverUrl +'user/login', data)
				     .success(function(response){
						 _authentication.isLoggedIn = true;
						 _authentication.username = data.username;
						 localStorageService.set('username',data.username);
						 deferred.resolve(response);
					 })
					 .error(function(error){
						 deferred.reject(error);
					 });
				return deferred.promise;
				
		};
	
	
	var logOut = function(){
			localStorageService.clearAll();
			//TODO: pozovi logout sa servera da se unisti sesija
			_authentication.userName = "";
			_authentication.isLoggedIn = false;
			$location.path('/');
		
	};

	var isLoggedIn = function(){
		var isLogged = localStorageService.get("username");
		if(isLogged){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	return{
		register: register,
		login: logIn,
		isLoggedIn: isLoggedIn,
		logout:logOut,
		test:test
	};
	}]);
	
}());