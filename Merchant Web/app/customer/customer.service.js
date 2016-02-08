(function(){
	var service = function($http){

        var insertCustomer = function (serverUrl, data) {
            return $http.post(serverUrl + 'customer/add', data)
                .then(function (response) {
                    return response.data;
                });
        };
        var insertTravelInsurance = function (serverUrl, data) {
            return $http.post(serverUrl + 'travelInsurance', data)
                .then(function (response) {
                    return response.data;
                });
        };
		var insertHomeInsurance = function(serverUrl, data){
            return $http.post(serverUrl + 'homeInsurance',data)
                .then(function(response){
                    console.log(response);
                    return response.data;
                });
        };
        var insertVehicleInsurance = function(serverUrl,data){
            return $http.post(serverUrl + 'vehicleInsurance',data)
                .then(function(response){
                    return response.data;
                });
        };
		return {
            insertTravelInsurance :insertTravelInsurance,
            insertCustomer :insertCustomer,
            insertHomeInsurance :insertHomeInsurance,
            insertVehicleInsurance :insertVehicleInsurance
		};
		
	};

	angular.module('merchant.customer').factory('customer.service',service);
}());