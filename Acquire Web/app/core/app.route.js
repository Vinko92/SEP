(function() {
	"use strict";

	var app = angular
		.module('safeguard.core');

	app.config(['$stateProvider','$urlRouterProvider',
    function($stateProvider,$urlRouterProvider){
		$urlRouterProvider.otherwise('/');
		
        $stateProvider.
            state('main', {
				abstract:true,
				views: {
					'horizontal-navigation': {
						templateUrl: '/app/shared/views/horizontal-navigation.html',
						controller: 'HeaderController',
						controllerAs: 'header'
					}
				}
               
            }).
			state('main.home', {
				url:'/',
				views:{
					'content@' : {
						templateUrl: '/app/core/views/home.html',
						controller: 'HomeController',
						controllerAs: 'home'
					}
				}		
			}).
			state('main.login',{
				url:'/login',
				views:{
					'content@' : {
						templateUrl: '/app/core/views/login.html',
						controller: 'AuthController',
						controllerAs: 'auth'
					}
				}
			}).
			state('main.card-details',{
				url:'/card-details',
				views:{
					'content@' : {
						templateUrl: '/app/core/views/card-details.html',
						controller: 'HomeController',
						controllerAs: 'ctrl'
					}
				}
			})
    }]);

})();