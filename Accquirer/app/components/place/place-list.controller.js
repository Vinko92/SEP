(function() {
	"use strict";

	angular
		.module('company-registry.place')
		.controller('PlaceListController', PlaceListController);

	PlaceListController.$inject = ['$scope', '$location', 'places'];
	function PlaceListController($scope, $location, places) {
		var plc = this;
		plc.places = places;

		plc.pagination = {
			currentPage: 1,
			pageSize: 5,
			pages: new Array(Math.ceil(plc.places.length / 5)),
			changePage: function(page) {
				if(plc.pagination.currentPage != page && page > 0 && page <= plc.pagination.pages.length) {
					plc.pagination.currentPage = page;
				}
			}
		};

		$scope.$watch(function() {
			return plc.filtered;
		}, function(newVal, oldVal) {
			if(!angular.equals(newVal, oldVal)) {
				plc.pagination.pages = new Array(Math.ceil(plc.filtered.length / plc.pagination.pageSize));
			}
		}, true);

		plc.tableChanged = function(sortParam) {
			if(plc.sort === sortParam) {
				plc.sortDirection = plc.sortDirection == '+' ? '-' : '+';
			} else {
				plc.sort = sortParam;
				plc.sortDirection = '+';
			}
		};

		plc.edit = function(id) {
			$location.path("/place/"+id);
		};
	}
})();