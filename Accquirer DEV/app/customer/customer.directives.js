(function(){
	'use strict';
	var app = angular.module('merchant.customer');
	
	app.directive('navigation',function(){
		return{
			restrict:'E',
			templateUrl:'./app/customer/views/partials/navigation.html'
		};
	});
	
	app.directive('email-validator', function(){
		return{
			restrict: 'A',
			require: 'ngModel',
			link: function(scope,elm,attrs,model){
				var regex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$/;
				var validator = function(value){
					model.$setValiditiy('email', regex.test(value));
					return value;
				};
				model.$parsers.push(validator);
				model.$formatters.push(validator);
			}
			
		};
	});
})();