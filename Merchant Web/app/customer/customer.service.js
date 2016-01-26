(function(){
	var service = function($http){
		
		var insertCustomer = function(serverUrl,data){
			return $http.post(serverUrl+'customer/add/', data)
						.then(function(response) {
							return response.data;
						});
		};
		
		return {
			insertCustomer :insertCustomer
		};
		
	};
	angular.module('merchant.customer').factory('customer.service',service);
}());