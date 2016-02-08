(function(){
	'use strict';
	
	angular.module('safeguard.core').factory('home.service',['$http','$location','$q','localStorageService','serverUrl', function ($http,$location, $q,localStorageService, serverUrl) {

	var submitPayment = function(data){
				var deferred = $q.defer();
				$http.post(serverUrl +'/payment/create/3', data)
				     .success(function(response){
						 deferred.resolve(response.data);
					 })
					 .error(function(error){
						 deferred.reject(error);
					 });
				return deferred.promise;
				
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
		submitPayment: submitPayment,
	};
	}]);
	
}());