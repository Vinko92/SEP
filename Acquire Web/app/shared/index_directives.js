(function(){
	'use strict';
	
	var app = angular.module('safeguard.core');
	
	
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
	
	app.directive('widget-feedback', function(){
		return{
			restrict: 'E',
			templateUrl: './app/shared/widgets/widget-feedback.html',
			link: function(scope){
				if(scope.success-message){
					document.getElementById('success-message').fadeIn(300).fadeOut(6000);
				}else if(scope.error-message){
					document.getElementById('error-messsage').show();
				}
			}
		}
	});
	
})();