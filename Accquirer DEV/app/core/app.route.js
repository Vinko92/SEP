(function() {
	"use strict";

	var app = angular
		.module('acquirer.core');

	app.config(['$routeProvider',
    function($routeProvider){
        $routeProvider.
            when('/', {
                templateUrl: '/app/core/views/home.html',
                controller: 'HomeController',
				controllerAs: 'home'
            }).
            when('/customer/register', {
                templateUrl: '/app/core/views/register.html',
                controller: 'RegisterController',
				controllerAs: 'register'
            }).
            when('events/+', {
                templateUrl: '/app/views/CreateEvent.html',
                controller: 'EventController'
            }).
            otherwise({
                redirectTo: '/'
            });
    }]);

})();