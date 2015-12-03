(function(){
	'use strict';
	
	angular.module('acquirer.core')
			.controller('RegisterController',RegisterController);
			
	function RegisterController(){
		var self = this;
		
		self.message = "Help us get to know you!";
	}
	
})();