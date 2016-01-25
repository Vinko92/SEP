(function() {
	"use strict";
	
	angular
		.module('company-registry.core')
		.controller('HeaderController', HeaderController);

	HeaderController.$inject = ['$translate', 'tmhDynamicLocale', 'crTranslations'];
	function HeaderController($translate, tmhDynamicLocale, crTranslations) {
		var hc = this;
		hc.currentLanguage = crTranslations[$translate.use()].LANGUAGE;
		hc.setLanguage = setLanguage;

		function setLanguage(language) {
			$translate.use(language);
			tmhDynamicLocale.set(language);
			hc.currentLanguage = crTranslations[language].LANGUAGE;
		}
	}
})();