(function(){
	'use strict';
	
	var app = angular.module('acquirer.core');
	
	
	app.directive('header', function(){
		return {
			restrict: 'E',
			templateUrl: './app/shared/views/header.html'
		};
	});
	
	app.directive('footer', function(){
		return {
			restrict: 'E',
			templateUrl: './app/shared/views/footer.html'
		};
	});
	
})();