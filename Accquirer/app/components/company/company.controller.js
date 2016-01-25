(function() {
	"use strict";

	angular
		.module('company-registry.company')
		.controller('CompanyController', CompanyController);

	CompanyController.$inject = ['$scope', '$location', 'Company', 'placeModal', 'deleteModal', 'company', 'places', 'title'];
	function CompanyController($scope, $location, Company, placeModal, deleteModal, company, places, title) {
		var cc = this;

		cc.places = places;
		cc.company = company;
		if(!cc.company.headquarters) {
			cc.company.headquarters = cc.places[0].name; //želimo da izbegnemo null vrednost, pa eksplicitno zadajemo da se selektuje prvi element jer angular automatski pravi null koji nestaje (bug i radnom okviru)
		}
		cc.companyCopy = angular.copy(cc.company);
		cc.title = title;
		
		if(cc.company._id) { //Postavljamo watch samo ukoliko je edit, jer nema razloga da disablujemo revert kad je add, a watch, pogotovo sa trećim parametrom true je skup
			cc.disableRevert = true;
			$scope.$watch(function() {
				return cc.company;
			}, function(newVal, oldVal) {
				if(!angular.equals(newVal, oldVal)) {
					cc.disableRevert = angular.equals(cc.company, cc.companyCopy);
				}
			}, true);
		}

		cc.uniqueField = function(value) {
			return Company.get({"id":value});
		};

		cc.newPlace = function() {
			placeModal.open().then(function(data) {
				cc.places.push(data);
				cc.company.headquarters = data.name;
			});
		};

		cc.revertChanges = function() {
			cc.company = angular.copy(cc.companyCopy);
		};

		cc.save = function() {
			cc.company.$saveOrUpdate(success);
		};

		cc.remove = function() {
			deleteModal.open("company").then(function(flag) {
				if(flag) {
					cc.company.$delete(success);
				}
			});
		};

		function success() {
			$location.path('/company');
		}
	}
})();