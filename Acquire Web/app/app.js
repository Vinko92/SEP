(function(){

'use strict';

var app = angular.module('safeguard.core', ['ui.router','LocalStorageModule']);

app.constant('serverUrl','http://localhost:8080/safeguard/');

app.config(function(localStorageServiceProvider){
	localStorageServiceProvider
		.setStorageType('sessionStorage')
		.setPrefix('safeguard');
});

})();