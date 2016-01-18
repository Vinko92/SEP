(function() {
	'use strict';

	var insurance = angular
		.module('merchant.customer');

	insurance.config(['$routeProvider',
    function($routeProvider){
        $routeProvider.
            when('/customer/personal-info', {
                templateUrl: '/app/customer/views/personal-info.html',
                controller: 'CustomerController',
				controllerAs: 'ctrl'
            }).
            when('/customer/adress-info', {
                templateUrl: '/app/customer/views/adress-info.html',
                controller: 'CustomerController',
				controllerAs: 'ctrl'
            }).
            when('/customer/employment-info', {
                templateUrl: '/app/customer/views/employment-info.html',
                controller: 'CustomerController',
				controllerAs: 'ctrl'
            }).
            otherwise({
                redirectTo: '/'
            });
    }]);

})();