(function() {
	'use strict';

	angular
		.module('company-registry.place.place-modal')
		.factory('placeModal', placeModal);

	placeModal.$inject = ['Place', '$modal'];
	function placeModal(Place, $modal) {
		return {
			open: openPlaceModal
		};

		function openPlaceModal() {
			var modalInstance = $modal.open({
				animation: true,
				resolve: {
					newPlace: function() {
						return new Place();
					}
				},
				templateUrl: 'app/components/place/place-modal/place-modal.html',
				controller: 'PlaceModalController',
				controllerAs: 'pac'
			});

			return modalInstance.result.then(function(newPlace) {
				newPlace._id = newPlace.postalCode;
				return newPlace.$update(function(data) {
					return data;
				});
			});
		}
	}
})();