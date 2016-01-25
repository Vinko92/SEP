(function() {
	"use strict";

	angular
		.module('company-registry.employee')
		.controller('EmployeeListController', EmployeeListController);

	EmployeeListController.$inject = ['$scope', '$location', 'employees', 'places'];
	function EmployeeListController($scope, $location, employees, places) {
		//Objekat koji sadrži podatke vezane za straničenje
		var elc = this;
		elc.employees = employees;
		elc.places = places;

		elc.pagination = {
			currentPage: 1,
			pageSize: 5,
			pages: new Array(Math.ceil(elc.employees.length / 5)),
			changePage: function(page) {
				if(elc.pagination.currentPage != page && page > 0 && page <= elc.pagination.pages.length) {
					elc.pagination.currentPage = page;
				}
			}
		};

		$scope.$watch(function() {
			return elc.filtered; //preporučen način da se posmatra objekat kod controller as
		}, function(newVal, oldVal) {
			if(!angular.equals(newVal, oldVal)) { //koristimo angular.equals zato što nam treba dubinsko poređenje vrednosti objekata
				elc.pagination.pages = new Array(Math.ceil(elc.filtered.length / elc.pagination.pageSize));
			}
		}, true); //postavljamo true jer želimo da pratimo kad se promeni property objekta koji posmatramo, a ne samo njegova referenca

		elc.tableChanged = function(sortParam) {
			if(elc.sort === sortParam) {
				elc.sortDirection = elc.sortDirection == '+' ? '-' : '+';
			} else {
				elc.sort = sortParam;
				elc.sortDirection = '+';
			}
		};

		elc.edit = function(id) {
			$location.path("/employee/"+id);
		};
	}
})();