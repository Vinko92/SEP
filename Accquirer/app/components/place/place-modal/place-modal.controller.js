(function() {
	"use strict";

	angular
		.module('company-registry.place.place-modal')
		.controller('PlaceModalController', PlaceModalController);

	PlaceModalController.$inject = ['$modalInstance', 'newPlace'];
	function PlaceModalController($modalInstance, newPlace) {
		var pac = this;
		pac.place = newPlace;
		pac.ok = ok;
		pac.cancel = cancel;

		function ok() {
			$modalInstance.close(pac.place);
		}

		function cancel() {
			$modalInstance.dismiss();
		}
	}
})();