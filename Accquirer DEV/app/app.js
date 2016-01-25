(function(){

'use strict';

var app = angular.module('merchant.core', ['ui.router','merchant.customer','LocalStorageModule']);

app.constant('serverUrl','http://localhost:8080/MerchantApp/');

app.config(function(localStorageServiceProvider){
	localStorageServiceProvider
		.setStorageType('sessionStorage')
		.setPrefix('insurance');
});

})();