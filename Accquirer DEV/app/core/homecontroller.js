(function(){
	"use strict";
	
	angular
		.module('acquirer.core')
		.controller('HomeController', HomeController);

	
	function HomeController() {
		var self = this;

		self.message = "Ovo je poruka iz kontrolera!";
		self.homeImageUrl = "./content/image/homeImage.jpg";
		self.englishIconUrl = "./content/image/english.png";
		self.espanolIconUrl = "./content/image/espanol.jpg";
		self.franceIconUrl = "./content/image/france.jpg";
		
	}
	
})();