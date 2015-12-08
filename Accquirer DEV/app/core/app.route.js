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
            });
    }]);

})();