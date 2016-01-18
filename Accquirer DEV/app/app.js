(function(){

'use strict';

var app = angular.module('merchant.core', ['ui.router','merchant.customer']);

		app.run(function($rootScope) {
			$rootScope.serverUrl =  'http://localhost:8080/MerchantApp/';

		});
})();