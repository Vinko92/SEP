(function() {
	"use strict";

	angular
		.module('company-registry.place')
		.factory('Place', Place);

	Place.$inject = ['$resource'];
	function Place($resource) {
		var apiKey = "oNGu6nUpyK2wAxtLwTgFqq83F4cn1kZ4";
		var dbName = "kursevi-angular";
		var collectionName = "place";
		return $resource("https://api.mongolab.com/api/1/databases/:dbName/collections/:collectionName/:id",
			{apiKey: apiKey, id: "@_id", dbName: dbName, collectionName: collectionName},
			{ update: { method: 'PUT' } });
	}
})();