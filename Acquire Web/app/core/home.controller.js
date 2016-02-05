(function(){
	"use strict";
	
	angular
		.module('safeguard.core')
		.controller('HomeController', HomeController);

	
	function HomeController() {
		var self = this;

		self.message = "Ovo je poruka iz kontrolera!";
		self.homeImageUrl = "./content/image/OurMoto.jpg";
		self.englishIconUrl = "./content/image/english.png";
		self.espanolIconUrl = "./content/image/espanol.jpg";
		self.franceIconUrl = "./content/image/france.jpg";
		
	}
	
})();