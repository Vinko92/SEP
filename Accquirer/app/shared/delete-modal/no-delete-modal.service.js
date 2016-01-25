(function() {
	'use strict';

	angular
		.module('company-registry.shared')
		.factory('noDeleteModal', noDeleteModal);

	noDeleteModal.$inject = ['$modal'];
	function noDeleteModal($modal) {
		return {
			open: openNoDeleteModal
		};

		function openNoDeleteModal() {
			var modalInstance = $modal.open({
				animation: true,
				templateUrl: 'app/shared/delete-modal/no-delete-modal.html',
				controller: 'NoDeleteModalController',
				controllerAs: 'ndmc'
			});
		}
	}
})();