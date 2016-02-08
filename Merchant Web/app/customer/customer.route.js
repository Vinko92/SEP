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
			}).state('customer.travel', {
				url:'/customer/travel-insurance',
				views:{
					'content@' : {
						templateUrl: '/app/customer/views/travel-insurance.html',
						controller: 'CustomerController',
						controllerAs: 'ctrl'
					}
				}
			}).state('customer.home',{
                url:'/customer/home-insurance',
                  views:{
                      'content@' : {
                          templateUrl: '/app/customer/views/home-insurance.html',
                          controller: 'CustomerController',
                          controllerAs: 'ctrl'
                      }
                  }
            }).state('customer.vehicle',{
                  url:'/customer/vehicle-insurance',
                  views:{
                      'content@' : {
                          templateUrl: '/app/customer/views/vehicle-insurance.html',
                          controller: 'CustomerController',
                          controllerAs: 'ctrl'
                      }
                  }
              }).state('customer.price',{
                  url:'/customer/home-insurance-price',
                  views:{
                      'content@' : {
                          templateUrl: '/app/customer/views/home-insurance-price.html',
                          controller: 'CustomerController',
                          controllerAs: 'ctrl'
                      }
                  }
              }).state('customer.overview',{
                  url:'/customer/overview',
                  views:{
                      'content@' : {
                          templateUrl: '/app/customer/views/overview.html',
                          controller: 'CustomerController',
                          controllerAs: 'ctrl'
                      }
                  }
              })
    }]);

}());