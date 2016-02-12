(function(){
	'use strict';
	
	var authDirectives = angular.module('authDirectives', []);
	
	authDirectives.directive('validEmail', function () {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function (scope, elm, attrs, model) {

            var EMAIL_REGEXP = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$/;
            var emailValidator = function (value) {
                if (!value || EMAIL_REGEXP.test(value)) {
                    model.$setValidity('email', true);
                    return value;
                } else {
                    model.$setValidity('email', false);
                    return undefined;
                }
               
            };
            model.$parsers.push(emailValidator);
            model.$formatters.push(emailValidator);
        }
    };
});
authDirectives.directive("passwordVerify", function () {
    return {
        require: "ngModel",
        scope: {
            passwordVerify: '='
        },
        link: function (scope, element, attrs, ctrl) {
            scope.$watch(function () {
                var combined;
				console.log('test');
                if (scope.passwordVerify || ctrl.$viewValue) {
                    combined = scope.passwordVerify + '_' + ctrl.$viewValue;
                }
                return combined;
            }, function (value) {
                if (value) {
                    ctrl.$parsers.unshift(function (viewValue) {
                        var origin = scope.passwordVerify;
                        if (origin !== viewValue) {
                            ctrl.$setValidity("passwordVerify", false);
                            return undefined;
                        } else {
                            ctrl.$setValidity("passwordVerify", true);
                            return viewValue;
                        }
                    });
                }
            });
        }
    };
});
	
}());