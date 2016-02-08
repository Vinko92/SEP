(function(){
	"use strict";
	
	angular
		.module('safeguard.core')
		.controller('HomeController', HomeController);

	HomeController.$inject = ['$location','$scope','$http','localStorageService','home.service','serverUrl'];
	
	function HomeController($location, $scope,$http,localStorageService,service, serverUrl) {
		var self = this;
		self.submitPayment = function(){
		if(localStorageService.get("username"))
		{
			$http.post(serverUrl +'/payment/create/3', $scope.cardData)
				 .success(function(response){
					 console.log(response);
					 $scope.success = response.message;
				 })
				 .error(function(response){
					$scope.messages = response.message;
				 });
					
			
		}
		else{
			$location.path("/login");
		}
		
		};
		
	}
	
})();