(function() {
	"use strict";

	angular
		.module('company-registry.place')
		.controller('PlaceController', PlaceController);

	PlaceController.$inject = ['$scope', '$location', 'Employee', 'Company', 'deleteModal', 'noDeleteModal', '$q', 'place', 'title'];
	function PlaceController($scope, $location, Employee, Company, deleteModal, noDeleteModal, $q, place, title) {
		var pc = this;
			
		pc.place = place;
		pc.placeCopy = angular.copy(pc.place);
		pc.title = title;

		pc.canDelete = false;
		$q.all([Company.query().$promise, Employee.query().$promise]).then(function(data) {
			var canDelete = true;
			angular.forEach(data[0], function(company) {
				if(company.headquarters === pc.place.name) {
					canDelete = false;
				}
			});
			if(canDelete) {
				angular.forEach(data[1], function(employee) {
					if(employee.placeOfBirth === pc.place.name) {
						canDelete = false;
					}
				});
			}
			pc.canDelete = canDelete;
		});

		if(pc.place._id) { //Postavljamo watch samo ukoliko je edit, jer nema razloga da disablujemo revert kad je add, a watch, pogotovo sa trećim parametrom true je skup
			pc.disableRevert = true;
			$scope.$watch(function() {
				return pc.place;
			}, function(newVal, oldVal) {
				if(!angular.equals(newVal, oldVal)) {
					pc.disableRevert = angular.equals(pc.place, pc.placeCopy);
				}
			}, true);
		}

		pc.revertChanges = function() {
			pc.place = angular.copy(pc.placeCopy);
		};

		pc.save = function() {
			if(!pc.place._id) {
				pc.place._id = pc.place.postalCode;
			}
			pc.place.$update(success);
		};

		pc.remove = function() {
			if(pc.canDelete) {
				deleteModal.open("place").then(function(flag) {
					if(flag) {
						pc.place.$delete(success);
					}
				});
			} else {
				noDeleteModal.open();
			}
		};

		function success() {
			$location.path('/place');
		}
	}
})();