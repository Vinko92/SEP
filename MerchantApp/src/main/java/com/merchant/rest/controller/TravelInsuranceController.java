package com.merchant.rest.controller;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.merchant.rest.model.AdditionalInsurance;
import com.merchant.rest.model.Customer;
import com.merchant.rest.model.Insurance;

import com.merchant.rest.model.Sport;
import com.merchant.rest.service.AdditionalInsuranceService;
import com.merchant.rest.service.CustomerService;
import com.merchant.rest.service.InsuranceService;
import com.merchant.rest.service.RegionService;
import com.merchant.rest.service.SportService;

@Controller
@RequestMapping("/")
public class TravelInsuranceController {

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Autowired
	private AdditionalInsuranceService additionalInsuranceService;
	
	@Autowired
	private InsuranceService insuranceService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private SportService sportService;
	
	@Autowired
	private RegionService regionService;
	
	@Autowired(required = true)
	@Qualifier(value = "additionalInsuranceService")
	public void setAdditionalInsuranceService(
			AdditionalInsuranceService additionalInsuranceService) {
		this.additionalInsuranceService = additionalInsuranceService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "insuranceService")
	public void setInsuranceService(InsuranceService insuranceService) {
		this.insuranceService = insuranceService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "customerService")
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "sportService")
	public void setSportService(SportService sportService) {
		this.sportService = sportService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "regionService")
	public void setRegionService(RegionService regionService) {
		this.regionService = regionService;
	}
	
	@RequestMapping(value = "/travelInsurance",method = RequestMethod.POST)
	public ResponseEntity<Double> travelInsurance(@RequestBody Insurance insurance,UriComponentsBuilder ucBuilder){

		String regionName = insurance.getRegion();
		Sport s = sportService.getSportByName(insurance.getSport());
		Customer c = customerService.getCustomerByName(insurance.getOwnerName());
		
		
		if(c == null){
			return new ResponseEntity<Double>(HttpStatus.CONFLICT);
		}
		if(!c.isOwner()){
			customerService.setCustomerOwnerByJmbg(c.getJmbg());
		}
				switch(regionName){
				case "Europe":{
					double pricePerPerson = 0;
					if(s == null){
						pricePerPerson = insurance.getDurationOfInsurance() * (10 + insurance.getAmount()/1000);
					}else if(s != null && s.isExtreme() == false){
						pricePerPerson = insurance.getDurationOfInsurance() * (10 + 12 + insurance.getAmount()/1000);
					}else if(s != null && s.isExtreme() == true){
						pricePerPerson = insurance.getDurationOfInsurance() * (10 + 20 + insurance.getAmount()/1000);
					}
					if(insurance.getAgeOfPersons() < 18){
						double price1 = pricePerPerson - pricePerPerson/10;
						insurance.setPrice(price1);
						for(AdditionalInsurance ai : additionalInsuranceService.getAllAdditionalInsurance()){
							if(ai.getOwenerId() == c.getId()){
								insurance.setAdditionalInsuranceId(ai.getId());
							}else{
								continue;
							}
						}
						insuranceService.addInsurance(insurance);			
				        
				        return new ResponseEntity<Double>(price1, HttpStatus.OK);
					}else if (insurance.getAgeOfPersons() >= 18 && insurance.getAgeOfPersons() < 60){
						double price2 = pricePerPerson;
						insurance.setPrice(price2);
						for(AdditionalInsurance ai : additionalInsuranceService.getAllAdditionalInsurance()){
							if(ai.getOwenerId() == c.getId()){
								insurance.setAdditionalInsuranceId(ai.getId());
							}else{
								continue;
							}
						}
						insuranceService.addInsurance(insurance);

						
				        return new ResponseEntity<Double>(price2, HttpStatus.OK);

				
					}else if (insurance.getAgeOfPersons() >= 60){
						double price3 = pricePerPerson - pricePerPerson/20;
						insurance.setPrice(price3);
						for(AdditionalInsurance ai : additionalInsuranceService.getAllAdditionalInsurance()){
							if(ai.getOwenerId() == c.getId()){
								insurance.setAdditionalInsuranceId(ai.getId());
							}else{
								continue;
							}
						}
						insuranceService.addInsurance(insurance);

				        return new ResponseEntity<Double>(price3, HttpStatus.OK);
					}
					break;
					}
				case "North America":{
					double pricePerPerson = 0;
					if(s == null){
						pricePerPerson = insurance.getDurationOfInsurance() * (13 + insurance.getAmount()/1000);
					}else if(s != null && s.isExtreme() == false){
						pricePerPerson = insurance.getDurationOfInsurance() * (13 + 12 + insurance.getAmount()/1000);
					}else if(s != null && s.isExtreme() == true){
						pricePerPerson = insurance.getDurationOfInsurance() * (13 + 20 + insurance.getAmount()/1000);
					}
					if(insurance.getAgeOfPersons() < 18){
						double price1 = pricePerPerson - pricePerPerson/10;
						insurance.setPrice(price1);
						for(AdditionalInsurance ai : additionalInsuranceService.getAllAdditionalInsurance()){
							if(ai.getOwenerId() == c.getId()){
								insurance.setAdditionalInsuranceId(ai.getId());
							}else{
								continue;
							}
						}
						insuranceService.addInsurance(insurance);

				        return new ResponseEntity<Double>(price1, HttpStatus.OK);

						
					}else if (insurance.getAgeOfPersons() >= 18 && insurance.getAgeOfPersons() < 60){
						double price2 = pricePerPerson;
						insurance.setPrice(price2);
						for(AdditionalInsurance ai : additionalInsuranceService.getAllAdditionalInsurance()){
							if(ai.getOwenerId() == c.getId()){
								insurance.setAdditionalInsuranceId(ai.getId());
							}else{
								continue;
							}
						}
						insuranceService.addInsurance(insurance);

				        return new ResponseEntity<Double>(price2, HttpStatus.OK);

						
					}else if (insurance.getAgeOfPersons() >= 60){
						double price3 = pricePerPerson - pricePerPerson/20;
						insurance.setPrice(price3);
						for(AdditionalInsurance ai : additionalInsuranceService.getAllAdditionalInsurance()){
							if(ai.getOwenerId() == c.getId()){
								insurance.setAdditionalInsuranceId(ai.getId());
							}else{
								continue;
							}
						}
						insuranceService.addInsurance(insurance);

				        return new ResponseEntity<Double>(price3, HttpStatus.OK);

					}
					break;
				}
				case "South America":{
					double pricePerPerson = 0;
					if(s == null){
						pricePerPerson = insurance.getDurationOfInsurance() * (20 + insurance.getAmount()/1000);
					}else if(s != null && s.isExtreme() == false){
						pricePerPerson = insurance.getDurationOfInsurance() * (20 + 12 + insurance.getAmount()/1000);
					}else if(s != null && s.isExtreme() == true){
						pricePerPerson = insurance.getDurationOfInsurance() * (20 + 20 + insurance.getAmount()/1000);
					}
					if(insurance.getAgeOfPersons() < 18){
						double price1 = pricePerPerson - pricePerPerson/10;
						insurance.setPrice(price1);
						for(AdditionalInsurance ai : additionalInsuranceService.getAllAdditionalInsurance()){
							if(ai.getOwenerId() == c.getId()){
								insurance.setAdditionalInsuranceId(ai.getId());
							}else{
								continue;
							}
						}
						insuranceService.addInsurance(insurance);

				        return new ResponseEntity<Double>(price1, HttpStatus.OK);

						
					}else if (insurance.getAgeOfPersons() >= 18 && insurance.getAgeOfPersons() < 60){
						double price2 = pricePerPerson;
						insurance.setPrice(price2);
						for(AdditionalInsurance ai : additionalInsuranceService.getAllAdditionalInsurance()){
							if(ai.getOwenerId() == c.getId()){
								insurance.setAdditionalInsuranceId(ai.getId());
							}else{
								continue;
							}
						}
						insuranceService.addInsurance(insurance);

				        return new ResponseEntity<Double>(price2, HttpStatus.OK);

					}else if (insurance.getAgeOfPersons() >= 60){
						double price3 = pricePerPerson - pricePerPerson/20;
						insurance.setPrice(price3);
						for(AdditionalInsurance ai : additionalInsuranceService.getAllAdditionalInsurance()){
							if(ai.getOwenerId() == c.getId()){
								insurance.setAdditionalInsuranceId(ai.getId());
							}else{
								continue;
							}
						}
						insuranceService.addInsurance(insurance);

				        return new ResponseEntity<Double>(price3, HttpStatus.OK);

					}
					break;
				}
				case "Cenrtal America":{
					double pricePerPerson = 0;
					if(s == null){
						pricePerPerson = insurance.getDurationOfInsurance() * (15 + insurance.getAmount()/1000);
					}else if(s != null && s.isExtreme() == false){
						pricePerPerson = insurance.getDurationOfInsurance() * (15 + 12 + insurance.getAmount()/1000);
					}else if(s != null && s.isExtreme() == true){
						pricePerPerson = insurance.getDurationOfInsurance() * (15 + 20 + insurance.getAmount()/1000);
					}
					if(insurance.getAgeOfPersons() < 18){
						double price1 = pricePerPerson - pricePerPerson/10;
						insurance.setPrice(price1);
						for(AdditionalInsurance ai : additionalInsuranceService.getAllAdditionalInsurance()){
							if(ai.getOwenerId() == c.getId()){
								insurance.setAdditionalInsuranceId(ai.getId());
							}else{
								continue;
							}
						}
						insuranceService.addInsurance(insurance);

				        return new ResponseEntity<Double>(price1, HttpStatus.OK);

					}else if (insurance.getAgeOfPersons() >= 18 && insurance.getAgeOfPersons() < 60){
						double price2 = pricePerPerson;
						insurance.setPrice(price2);
						for(AdditionalInsurance ai : additionalInsuranceService.getAllAdditionalInsurance()){
							if(ai.getOwenerId() == c.getId()){
								insurance.setAdditionalInsuranceId(ai.getId());
							}else{
								continue;
							}
						}
						insuranceService.addInsurance(insurance);


				        return new ResponseEntity<Double>(price2, HttpStatus.OK);

					}else if (insurance.getAgeOfPersons() >= 60){
						double price3 = pricePerPerson - pricePerPerson/20;
						insurance.setPrice(price3);
						for(AdditionalInsurance ai : additionalInsuranceService.getAllAdditionalInsurance()){
							if(ai.getOwenerId() == c.getId()){
								insurance.setAdditionalInsuranceId(ai.getId());
							}else{
								continue;
							}
						}
						insuranceService.addInsurance(insurance);

		
				        return new ResponseEntity<Double>(price3, HttpStatus.OK);

					}
					break;
				}
				case "Asia":{
					double pricePerPerson = 0;
					if(s == null){
						pricePerPerson = insurance.getDurationOfInsurance() * (22 + insurance.getAmount()/1000);
					}else if(s != null && s.isExtreme() == false){
						pricePerPerson = insurance.getDurationOfInsurance() * (22 + 12 + insurance.getAmount()/1000);
					}else if(s != null && s.isExtreme() == true){
						pricePerPerson = insurance.getDurationOfInsurance() * (22 + 20 + insurance.getAmount()/1000);
					}
					if(insurance.getAgeOfPersons() < 18){
						double price1 = pricePerPerson - pricePerPerson/10;
						insurance.setPrice(price1);
						for(AdditionalInsurance ai : additionalInsuranceService.getAllAdditionalInsurance()){
							if(ai.getOwenerId() == c.getId()){
								insurance.setAdditionalInsuranceId(ai.getId());
							}else{
								continue;
							}
						}
						insuranceService.addInsurance(insurance);


				        return new ResponseEntity<Double>(price1, HttpStatus.OK);

					}else if (insurance.getAgeOfPersons() >= 18 && insurance.getAgeOfPersons() < 60){
						double price2 = pricePerPerson;
						insurance.setPrice(price2);
						for(AdditionalInsurance ai : additionalInsuranceService.getAllAdditionalInsurance()){
							if(ai.getOwenerId() == c.getId()){
								insurance.setAdditionalInsuranceId(ai.getId());
							}else{
								continue;
							}
						}
						insuranceService.addInsurance(insurance);

				        return new ResponseEntity<Double>(price2, HttpStatus.OK);

					}else if (insurance.getAgeOfPersons() >= 60){
						double price3 = pricePerPerson - pricePerPerson/20;
						insurance.setPrice(price3);
						for(AdditionalInsurance ai : additionalInsuranceService.getAllAdditionalInsurance()){
							if(ai.getOwenerId() == c.getId()){
								insurance.setAdditionalInsuranceId(ai.getId());
							}else{
								continue;
							}
						}
						insuranceService.addInsurance(insurance);

				        return new ResponseEntity<Double>(price3, HttpStatus.OK);

					}
					break;
				}
				case "Japan":{
					double pricePerPerson = 0;
					if(s == null){
						pricePerPerson = insurance.getDurationOfInsurance() * (24 + insurance.getAmount()/1000);
					}else if(s != null && s.isExtreme() == false){
						pricePerPerson = insurance.getDurationOfInsurance() * (24 + 12 + insurance.getAmount()/1000);
					}else if(s != null && s.isExtreme() == true){
						pricePerPerson = insurance.getDurationOfInsurance() * (24 + 20 + insurance.getAmount()/1000);
					}
					if(insurance.getAgeOfPersons() < 18){
						double price1 = pricePerPerson - pricePerPerson/10;
						insurance.setPrice(price1);
						for(AdditionalInsurance ai : additionalInsuranceService.getAllAdditionalInsurance()){
							if(ai.getOwenerId() == c.getId()){
								insurance.setAdditionalInsuranceId(ai.getId());
							}else{
								continue;
							}
						}
						insuranceService.addInsurance(insurance);

			
				        return new ResponseEntity<Double>(price1, HttpStatus.OK);

					}else if (insurance.getAgeOfPersons() >= 18 && insurance.getAgeOfPersons() < 60){
						double price2 = pricePerPerson;
						insurance.setPrice(price2);
						for(AdditionalInsurance ai : additionalInsuranceService.getAllAdditionalInsurance()){
							if(ai.getOwenerId() == c.getId()){
								insurance.setAdditionalInsuranceId(ai.getId());
							}else{
								continue;
							}
						}
						insuranceService.addInsurance(insurance);

					
				        return new ResponseEntity<Double>(price2, HttpStatus.OK);

					}else if (insurance.getAgeOfPersons() >= 60){
						double price3 = pricePerPerson - pricePerPerson/20;
						insurance.setPrice(price3);
						for(AdditionalInsurance ai : additionalInsuranceService.getAllAdditionalInsurance()){
							if(ai.getOwenerId() == c.getId()){
								insurance.setAdditionalInsuranceId(ai.getId());
							}else{
								continue;
							}
						}
						insuranceService.addInsurance(insurance);

						
				        return new ResponseEntity<Double>(price3, HttpStatus.OK);

					}
					break;
				}
				case "North Africa":{
					double pricePerPerson = 0;
					if(s == null){
						pricePerPerson = insurance.getDurationOfInsurance() * (60 + insurance.getAmount()/1000);
					}else if(s != null && s.isExtreme() == false){
						pricePerPerson = insurance.getDurationOfInsurance() * (60 + 12 + insurance.getAmount()/1000);
					}else if(s != null && s.isExtreme() == true){
						pricePerPerson = insurance.getDurationOfInsurance() * (60 + 20 + insurance.getAmount()/1000);
					}
					if(insurance.getAgeOfPersons() < 18){
						double price1 = pricePerPerson - pricePerPerson/10;
						insurance.setPrice(price1);
						for(AdditionalInsurance ai : additionalInsuranceService.getAllAdditionalInsurance()){
							if(ai.getOwenerId() == c.getId()){
								insurance.setAdditionalInsuranceId(ai.getId());
							}else{
								continue;
							}
						}
						insuranceService.addInsurance(insurance);

						
				        return new ResponseEntity<Double>(price1, HttpStatus.OK);

					}else if (insurance.getAgeOfPersons() >= 18 && insurance.getAgeOfPersons() < 60){
						double price2 = pricePerPerson;
						insurance.setPrice(price2);
						for(AdditionalInsurance ai : additionalInsuranceService.getAllAdditionalInsurance()){
							if(ai.getOwenerId() == c.getId()){
								insurance.setAdditionalInsuranceId(ai.getId());
							}else{
								continue;
							}
						}
						insuranceService.addInsurance(insurance);

						
				        return new ResponseEntity<Double>(price2, HttpStatus.OK);

					}else if (insurance.getAgeOfPersons() >= 60){
						double price3 = pricePerPerson - pricePerPerson/20;
						insurance.setPrice(price3);
						for(AdditionalInsurance ai : additionalInsuranceService.getAllAdditionalInsurance()){
							if(ai.getOwenerId() == c.getId()){
								insurance.setAdditionalInsuranceId(ai.getId());
							}else{
								continue;
							}
						}
						insuranceService.addInsurance(insurance);

				        return new ResponseEntity<Double>(price3, HttpStatus.OK);

					}
					break;
				}
				case "South Africa":{
					double pricePerPerson = 0;
					if(s == null){
						pricePerPerson = insurance.getDurationOfInsurance() * (25 + insurance.getAmount()/1000);
					}else if(s != null && s.isExtreme() == false){
						pricePerPerson = insurance.getDurationOfInsurance() * (25 + 12 + insurance.getAmount()/1000);
					}else if(s != null && s.isExtreme() == true){
						pricePerPerson = insurance.getDurationOfInsurance() * (25 + 20 + insurance.getAmount()/1000);
					}
					if(insurance.getAgeOfPersons() < 18){
						double price1 = pricePerPerson - pricePerPerson/10;
						insurance.setPrice(price1);
						for(AdditionalInsurance ai : additionalInsuranceService.getAllAdditionalInsurance()){
							if(ai.getOwenerId() == c.getId()){
								insurance.setAdditionalInsuranceId(ai.getId());
							}else{
								continue;
							}
						}
						insuranceService.addInsurance(insurance);

						
				        return new ResponseEntity<Double>(price1, HttpStatus.OK);

					}else if (insurance.getAgeOfPersons() >= 18 && insurance.getAgeOfPersons() < 60){
						double price2 = pricePerPerson;
						insurance.setPrice(price2);
						for(AdditionalInsurance ai : additionalInsuranceService.getAllAdditionalInsurance()){
							if(ai.getOwenerId() == c.getId()){
								insurance.setAdditionalInsuranceId(ai.getId());
							}else{
								continue;
							}
						}
						insuranceService.addInsurance(insurance);

						
				        return new ResponseEntity<Double>(price2, HttpStatus.OK);

					}else if (insurance.getAgeOfPersons() >= 60){
						double price3 = pricePerPerson - pricePerPerson/20;
						insurance.setPrice(price3);
						for(AdditionalInsurance ai : additionalInsuranceService.getAllAdditionalInsurance()){
							if(ai.getOwenerId() == c.getId()){
								insurance.setAdditionalInsuranceId(ai.getId());
							}else{
								continue;
							}
						}
						insuranceService.addInsurance(insurance);

				
				        return new ResponseEntity<Double>(price3, HttpStatus.OK);

					}
					break;
				}
				case "Central Africa":{
					double pricePerPerson = 0;
					if(s == null){
						pricePerPerson = insurance.getDurationOfInsurance() * (27 + insurance.getAmount()/1000);
					}else if(s != null && s.isExtreme() == false){
						pricePerPerson = insurance.getDurationOfInsurance() * (27 + 12 + insurance.getAmount()/1000);
					}else if(s != null && s.isExtreme() == true){
						pricePerPerson = insurance.getDurationOfInsurance() * (27 + 20 + insurance.getAmount()/1000);
					}
					if(insurance.getAgeOfPersons() < 18){
						double price1 = pricePerPerson - pricePerPerson/10;
						insurance.setPrice(price1);
						for(AdditionalInsurance ai : additionalInsuranceService.getAllAdditionalInsurance()){
							if(ai.getOwenerId() == c.getId()){
								insurance.setAdditionalInsuranceId(ai.getId());
							}else{
								continue;
							}
						}
						insuranceService.addInsurance(insurance);

						
				        return new ResponseEntity<Double>(price1, HttpStatus.OK);

					}else if (insurance.getAgeOfPersons() >= 18 && insurance.getAgeOfPersons() < 60){
						double price2 = pricePerPerson;
						insurance.setPrice(price2);
						for(AdditionalInsurance ai : additionalInsuranceService.getAllAdditionalInsurance()){
							if(ai.getOwenerId() == c.getId()){
								insurance.setAdditionalInsuranceId(ai.getId());
							}else{
								continue;
							}
						}
						insuranceService.addInsurance(insurance);

						
				        return new ResponseEntity<Double>(price2, HttpStatus.OK);

					}else if (insurance.getAgeOfPersons() >= 60){
						double price3 = pricePerPerson - pricePerPerson/20;
						insurance.setPrice(price3);
						for(AdditionalInsurance ai : additionalInsuranceService.getAllAdditionalInsurance()){
							if(ai.getOwenerId() == c.getId()){
								insurance.setAdditionalInsuranceId(ai.getId());
							}else{
								continue;
							}
						}
						insuranceService.addInsurance(insurance);
	
				        return new ResponseEntity<Double>(price3, HttpStatus.OK);

					}
					break;
				}
				case "Australia":{
					double pricePerPerson = 0;
					if(s == null){
						pricePerPerson = insurance.getDurationOfInsurance() * (30 + insurance.getAmount()/1000);
					}else if(s != null && s.isExtreme() == false){
						pricePerPerson = insurance.getDurationOfInsurance() * (30 + 12 + insurance.getAmount()/1000);
					}else if(s != null && s.isExtreme() == true){
						pricePerPerson = insurance.getDurationOfInsurance() * (30 + 20 + insurance.getAmount()/1000);
					}
					if(insurance.getAgeOfPersons() < 18){
						double price1 = pricePerPerson - pricePerPerson/10;
						insurance.setPrice(price1);
						for(AdditionalInsurance ai : additionalInsuranceService.getAllAdditionalInsurance()){
							if(ai.getOwenerId() == c.getId()){
								insurance.setAdditionalInsuranceId(ai.getId());
							}else{
								continue;
							}
						}
						insuranceService.addInsurance(insurance);

					
				        return new ResponseEntity<Double>(price1, HttpStatus.OK);

					
					}else if (insurance.getAgeOfPersons() >= 18 && insurance.getAgeOfPersons() < 60){
						double price2 = pricePerPerson;
						insurance.setPrice(price2);
						for(AdditionalInsurance ai : additionalInsuranceService.getAllAdditionalInsurance()){
							if(ai.getOwenerId() == c.getId()){
								insurance.setAdditionalInsuranceId(ai.getId());
							}else{
								continue;
							}
						}
						insuranceService.addInsurance(insurance);

					
				        return new ResponseEntity<Double>(price2, HttpStatus.OK);

					}else if (insurance.getAgeOfPersons() >= 60){
						double price3 = pricePerPerson - pricePerPerson/20;
						insurance.setPrice(price3);
						for(AdditionalInsurance ai : additionalInsuranceService.getAllAdditionalInsurance()){
							if(ai.getOwenerId() == c.getId()){
								insurance.setAdditionalInsuranceId(ai.getId());
							}else{
								continue;
							}
						}
						insuranceService.addInsurance(insurance);

				
				        return new ResponseEntity<Double>(price3, HttpStatus.OK);

					}
					break;
				}
				default:
					break;
				}
			

		return new ResponseEntity<Double>(HttpStatus.CONFLICT);

		
	}
}
