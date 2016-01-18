(function() {
	"use strict";

	var app = angular
		.module('merchant.core');

	app.config(['$stateProvider','$urlRouterProvider',
    function($stateProvider,$urlRouterProvider){
		$urlRouterProvider.otherwise('/');
		
        $stateProvider.
            state('main', {
				abstract:true,
				views: {
					'header': {
						templateUrl: '/app/shared/views/header.html',
						controller: 'HeaderController',
						controllerAs: 'header'
					},
					'footer':{
						templateUrl: '/app/shared/views/footer.html',
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
			state('main.about', {
				url:'/about',
				views:{
					'content@' : {
						templateUrl: '/app/core/views/about.html'
					}
				}		
			}).
			state('main.contact', {
				url:'/contact',
				views:{
					'content@' : {
						templateUrl: '/app/core/views/contacts.html',
						controller: 'HomeController',
						controllerAs: 'home'
					}
				}		
			}).
			state('auth',{
				abstract:true
			}).
			state('auth.login',{
				url:'/login',
				views:{
					'content@' : {
						templateUrl: '/app/core/views/login.html',
						controller: 'AuthController',
						controllerAs: 'auth'
					}
				}
			}).
			state('auth.register',{
				url:'/register',
				views:{
					'content@' : {
						templateUrl: '/app/core/views/register.html',
						controller: 'AuthController',
						controllerAs: 'auth'
					}
				}
			});
    }]);

})();