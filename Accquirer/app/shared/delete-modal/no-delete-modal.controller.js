(function() {
	"use strict";

	angular
		.module('company-registry.shared')
		.controller('NoDeleteModalController', NoDeleteModalController);

	NoDeleteModalController.$inject = ['$modalInstance'];
	function NoDeleteModalController($modalInstance) {
		var ndmc = this;
		ndmc.dismiss = $modalInstance.dismiss;
	}
})();