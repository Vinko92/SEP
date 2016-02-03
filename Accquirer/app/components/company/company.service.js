(function() {
	"use strict";

	angular
		.module('company-registry.company')
		.factory('Company', Company);

	Company.$inject = ['$resource'];
	function Company($resource) {
		var apiKey = "oNGu6nUpyK2wAxtLwTgFqq83F4cn1kZ4";
		var dbName = "kursevi-angular";
		var collectionName = "company";
		var companyService = $resource("https://api.mongolab.com/api/1/databases/:dbName/collections/:collectionName/:id",
			{apiKey: apiKey, id: "@_id", dbName: dbName, collectionName: collectionName},
			{ update: { method: 'PUT' } });

		//Mogli smo da ekstendujemo companyService, pa da onda u kontroleru koristimo Company.$saveOrUpdate(cc.company, successCallback)
		//Kada extendujemo prototip onda kažemo da će ovu metodu imati svaka instanca companyService-a.
		angular.extend(companyService.prototype, {
			$saveOrUpdate: function(successCallback) {
				if(!this._id) {
					this._id = this.pib;
				}
				this.$update(successCallback);
			}
		});
		return companyService;
	}
})();