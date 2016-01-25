(function() {
	"use strict";

	angular
		.module('company-registry.shared')
		.controller('DeleteModalController', DeleteModalController);

	DeleteModalController.$inject = ['$modalInstance', 'entity'];
	function DeleteModalController($modalInstance, entity) {
		var dmc = this;
		dmc.entity = entity;
		dmc.yes = yes;
		dmc.cancel = cancel;

		function yes() {
			$modalInstance.close(true);
		}

		function cancel() {
			$modalInstance.dismiss();
		}
	}
})();