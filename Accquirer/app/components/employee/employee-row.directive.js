(function() {
	"use strict";
	
	angular
		.module('company-registry.employee')
		.directive('crEmployee', crEmployee);

	function crEmployee() {
		return {
			restrict: "A",
			templateUrl: "app/components/employee/employee-row.html"
		};
	}
})();