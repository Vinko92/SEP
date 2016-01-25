(function() {
	'use strict';

	angular
		.module('company-registry.shared')
		.factory('deleteModal', deleteModal);

	deleteModal.$inject = ['$modal'];
	function deleteModal($modal) {
		return {
			open: openDeleteModal
		};

		function openDeleteModal(entity) {
			var modalInstance = $modal.open({
				animation: true,
				resolve: {
					entity: function() {
						return entity;
					}
				},
				templateUrl: 'app/shared/delete-modal/delete-modal.html',
				controller: 'DeleteModalController',
				controllerAs: 'dmc'
			});

			return modalInstance.result.then(function(flag) {
				return flag;
			});
		}
	}
})();