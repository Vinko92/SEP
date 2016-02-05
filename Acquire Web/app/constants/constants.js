(function(){
	'use strict';
	var app = angular.module('merchant.core');
	
	app.factory('constants', function() {
	  return {
			serverUrl : 'http://localhost:8080/MerchantApp/'
	  };
	});
	
}());