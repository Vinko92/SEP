(function() {
	"use strict";

	angular
		.module('company-registry.employee')
		.factory('Employee', Employee);

	Employee.$inject = ['$resource'];
	function Employee($resource) {
		var apiKey = "oNGu6nUpyK2wAxtLwTgFqq83F4cn1kZ4";
		var dbName = "kursevi-angular";
		var collectionName = "employee";
		var employeeService = $resource("https://api.mongolab.com/api/1/databases/:dbName/collections/:collectionName/:id",
			{apiKey: apiKey, id: "@_id", dbName: dbName, collectionName: collectionName},
			{ update: { method: 'PUT' } });

		//Mogli smo da ekstendujemo employeeService, pa da onda u kontroleru koristimo Employee.$saveOrUpdate(ec.employee, successCallback)
		//Kada extendujemo prototip onda kažemo da će ovu metodu imati svaka instanca employeeService-a.
		angular.extend(employeeService.prototype, {
			$saveOrUpdate: function(successCallback) {
				if(!this._id) {
					this._id = this.jmbg;
				}
				this.$update(successCallback);
			}
		});
		return employeeService;
	}
})();