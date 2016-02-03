(function() {
	"use strict";

	angular
		.module('company-registry.company')
		.controller('CompanyListController', CompanyListController);

	CompanyListController.$inject = ['$scope', '$location', 'companies'];
	function CompanyListController($scope, $location, companies) {
		//Objekat koji sadrži podatke vezane za straničenje
		var clc = this;
		clc.companies = companies;

		clc.pagination = {
			currentPage: 1,
			pageSize: 5,
			pages: new Array(Math.ceil(clc.companies.length / 5)),
			changePage: function(page) {
				if(clc.pagination.currentPage != page && page > 0 && page <= clc.pagination.pages.length) {
					clc.pagination.currentPage = page;
				}
			}
		};

		$scope.$watch(function() {
			return clc.filtered;
		}, function(newVal, oldVal) {
			if(!angular.equals(newVal, oldVal)) {
				clc.pagination.pages = new Array(Math.ceil(clc.filtered.length / clc.pagination.pageSize));
			}
		}, true);

		clc.tableChanged = function(sortParam) {
			if(clc.sort === sortParam) {
				clc.sortDirection = clc.sortDirection == '+' ? '-' : '+';
			} else {
				clc.sort = sortParam;
				clc.sortDirection = '+';
			}
		};

		clc.edit = function(id) {
			$location.path("/company/"+id);
		};
	}
})();