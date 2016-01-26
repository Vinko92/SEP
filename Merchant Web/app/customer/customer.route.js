(function() {
	'use strict';

	var insurance = angular
		.module('merchant.customer');

	insurance.config(['$stateProvider',
    function($stateProvider){
          $stateProvider.
			state('customer', {
				abstract:true,
				views: {
					'header': {
						templateUrl: '/app/shared/views/header.html',
					},
					'footer':{
						templateUrl: '/app/shared/views/footer.html',
					}
				}
            }).state('customer.personal', {
				url:'/customer/personal-info',
				views:{
					'content@' : {
						templateUrl: '/app/customer/views/personal-info.html',
						controller: 'CustomerController',
						controllerAs: 'ctrl'
					}
				}		
			})
           .state('customer.adress', {
				url:'/customer/adress-info',
				views:{
					'content@' : {
						templateUrl: '/app/customer/views/adress-info.html',
						controller: 'CustomerController',
						controllerAs: 'ctrl'
					}
				}		
			});
    }]);

}());