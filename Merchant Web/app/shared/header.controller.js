(function(){
	'use strict';
	
	angular.module('merchant.core')
		   .controller('HeaderController',header);
	
	header.$inject = ['auth.service'];
	
	function header(service){
		var self = this;
		
		self.isLoggedIn = function(){
			return service.isLoggedIn();
		};
		
		self.logOut = function(){
			service.logout();
		};
		
	}
	
}());