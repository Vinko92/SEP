package com.merchant.rest.controller;



import java.util.List;

import org.apache.log4j.jmx.Agent;
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
import com.merchant.rest.model.Home;
import com.merchant.rest.model.Insurance;
import com.merchant.rest.model.Region;
import com.merchant.rest.model.Risk;
import com.merchant.rest.model.RoadAssistence;
import com.merchant.rest.model.Sport;
import com.merchant.rest.model.User;
import com.merchant.rest.model.Vehicle;
import com.merchant.rest.service.AdditionalInsuranceService;
import com.merchant.rest.service.CustomerService;
import com.merchant.rest.service.HomeService;
import com.merchant.rest.service.InsuranceService;
import com.merchant.rest.service.RegionService;
import com.merchant.rest.service.RiskService;
import com.merchant.rest.service.RoadAssistenceService;
import com.merchant.rest.service.SportService;
import com.merchant.rest.service.VehicleService;
import com.mysql.fabric.xmlrpc.base.Value;

@Controller
@RequestMapping("/")
public class InsuranceController {
	
	@Autowired
	SessionFactory sessionFactory;

	@Autowired(required = true)
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Autowired
	private AdditionalInsuranceService additionalInsuranceService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private InsuranceService insuranceService;
	
	@Autowired
	private SportService sportService;
	
	@Autowired
	private RegionService regionService;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private RoadAssistenceService roadAssistenceService;
	
	@Autowired
	private RiskService riskService;
	
	@Autowired
	private HomeService homeService;
	
	@Autowired(required = true)
	@Qualifier(value = "additionalInsuranceService")
	public void setAdditionalInsuranceService(
			AdditionalInsuranceService additionalInsuranceService) {
		this.additionalInsuranceService = additionalInsuranceService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "homeService")
	public void setHomeService(HomeService homeService) {
		this.homeService = homeService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "insuranceService")
	public void setInsuranceService(InsuranceService insuranceService) {
		this.insuranceService = insuranceService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "riskService")
	public void setRiskService(RiskService riskService) {
		this.riskService = riskService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "regionService")
	public void setRegionService(RegionService regionService) {
		this.regionService = regionService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "sportService")
	public void setSportService(SportService sportService) {
		this.sportService = sportService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "customerService")
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	
	@RequestMapping(value = "/travelInsurance",method = RequestMethod.POST)
	public ResponseEntity<Integer> travelInsurance(@RequestBody Insurance insurance,UriComponentsBuilder ucBuilder){
		
		Region r = regionService.getRegionByName(insurance.getRegion());
		Sport s = sportService.getSportByName(insurance.getSport());
		String regionName = r.getName();
		
		switch (regionName) {
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
					insuranceService.addInsurance(insurance);
					HttpHeaders headers = new HttpHeaders();
			        headers.setLocation(ucBuilder.path("/travelInsurance/{id}").buildAndExpand(insurance.getId()).toUri());
			        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
				}else if (insurance.getAgeOfPersons() >= 18 && insurance.getAgeOfPersons() < 60){
					double price2 = pricePerPerson;
					insurance.setPrice(price2);
					insuranceService.addInsurance(insurance);
					HttpHeaders headers = new HttpHeaders();
			        headers.setLocation(ucBuilder.path("/travelInsurance/{id}").buildAndExpand(insurance.getId()).toUri());
			        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
				}else if (insurance.getAgeOfPersons() >= 60){
					double price3 = pricePerPerson - pricePerPerson/20;
					insurance.setPrice(price3);
					insuranceService.addInsurance(insurance);
					HttpHeaders headers = new HttpHeaders();
			        headers.setLocation(ucBuilder.path("/travelInsurance/{id}").buildAndExpand(insurance.getId()).toUri());
			        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
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
					insuranceService.addInsurance(insurance);
					HttpHeaders headers = new HttpHeaders();
			        headers.setLocation(ucBuilder.path("/travelInsurance/{id}").buildAndExpand(insurance.getId()).toUri());
			        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
				}else if (insurance.getAgeOfPersons() >= 18 && insurance.getAgeOfPersons() < 60){
					double price2 = pricePerPerson;
					insurance.setPrice(price2);
					insuranceService.addInsurance(insurance);
					HttpHeaders headers = new HttpHeaders();
			        headers.setLocation(ucBuilder.path("/travelInsurance/{id}").buildAndExpand(insurance.getId()).toUri());
			        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
				}else if (insurance.getAgeOfPersons() >= 60){
					double price3 = pricePerPerson - pricePerPerson/20;
					insurance.setPrice(price3);
					insuranceService.addInsurance(insurance);
					HttpHeaders headers = new HttpHeaders();
			        headers.setLocation(ucBuilder.path("/travelInsurance/{id}").buildAndExpand(insurance.getId()).toUri());
			        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
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
					insuranceService.addInsurance(insurance);
					HttpHeaders headers = new HttpHeaders();
			        headers.setLocation(ucBuilder.path("/travelInsurance/{id}").buildAndExpand(insurance.getId()).toUri());
			        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
				}else if (insurance.getAgeOfPersons() >= 18 && insurance.getAgeOfPersons() < 60){
					double price2 = pricePerPerson;
					insurance.setPrice(price2);
					insuranceService.addInsurance(insurance);
					HttpHeaders headers = new HttpHeaders();
			        headers.setLocation(ucBuilder.path("/travelInsurance/{id}").buildAndExpand(insurance.getId()).toUri());
			        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
				}else if (insurance.getAgeOfPersons() >= 60){
					double price3 = pricePerPerson - pricePerPerson/20;
					insurance.setPrice(price3);
					insuranceService.addInsurance(insurance);
					HttpHeaders headers = new HttpHeaders();
			        headers.setLocation(ucBuilder.path("/travelInsurance/{id}").buildAndExpand(insurance.getId()).toUri());
			        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
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
					insuranceService.addInsurance(insurance);
					HttpHeaders headers = new HttpHeaders();
			        headers.setLocation(ucBuilder.path("/travelInsurance/{id}").buildAndExpand(insurance.getId()).toUri());
			        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
				}else if (insurance.getAgeOfPersons() >= 18 && insurance.getAgeOfPersons() < 60){
					double price2 = pricePerPerson;
					insurance.setPrice(price2);
					insuranceService.addInsurance(insurance);
					HttpHeaders headers = new HttpHeaders();
			        headers.setLocation(ucBuilder.path("/travelInsurance/{id}").buildAndExpand(insurance.getId()).toUri());
			        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
				}else if (insurance.getAgeOfPersons() >= 60){
					double price3 = pricePerPerson - pricePerPerson/20;
					insurance.setPrice(price3);
					insuranceService.addInsurance(insurance);
					HttpHeaders headers = new HttpHeaders();
			        headers.setLocation(ucBuilder.path("/travelInsurance/{id}").buildAndExpand(insurance.getId()).toUri());
			        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
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
					insuranceService.addInsurance(insurance);
					HttpHeaders headers = new HttpHeaders();
			        headers.setLocation(ucBuilder.path("/travelInsurance/{id}").buildAndExpand(insurance.getId()).toUri());
			        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
				}else if (insurance.getAgeOfPersons() >= 18 && insurance.getAgeOfPersons() < 60){
					double price2 = pricePerPerson;
					insurance.setPrice(price2);
					insuranceService.addInsurance(insurance);
					HttpHeaders headers = new HttpHeaders();
			        headers.setLocation(ucBuilder.path("/travelInsurance/{id}").buildAndExpand(insurance.getId()).toUri());
			        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
				}else if (insurance.getAgeOfPersons() >= 60){
					double price3 = pricePerPerson - pricePerPerson/20;
					insurance.setPrice(price3);
					insuranceService.addInsurance(insurance);
					HttpHeaders headers = new HttpHeaders();
			        headers.setLocation(ucBuilder.path("/travelInsurance/{id}").buildAndExpand(insurance.getId()).toUri());
			        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
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
					insuranceService.addInsurance(insurance);
					HttpHeaders headers = new HttpHeaders();
			        headers.setLocation(ucBuilder.path("/travelInsurance/{id}").buildAndExpand(insurance.getId()).toUri());
			        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
				}else if (insurance.getAgeOfPersons() >= 18 && insurance.getAgeOfPersons() < 60){
					double price2 = pricePerPerson;
					insurance.setPrice(price2);
					insuranceService.addInsurance(insurance);
					HttpHeaders headers = new HttpHeaders();
			        headers.setLocation(ucBuilder.path("/travelInsurance/{id}").buildAndExpand(insurance.getId()).toUri());
			        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
				}else if (insurance.getAgeOfPersons() >= 60){
					double price3 = pricePerPerson - pricePerPerson/20;
					insurance.setPrice(price3);
					insuranceService.addInsurance(insurance);
					HttpHeaders headers = new HttpHeaders();
			        headers.setLocation(ucBuilder.path("/travelInsurance/{id}").buildAndExpand(insurance.getId()).toUri());
			        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
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
					insuranceService.addInsurance(insurance);
					HttpHeaders headers = new HttpHeaders();
			        headers.setLocation(ucBuilder.path("/travelInsurance/{id}").buildAndExpand(insurance.getId()).toUri());
			        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
				}else if (insurance.getAgeOfPersons() >= 18 && insurance.getAgeOfPersons() < 60){
					double price2 = pricePerPerson;
					insurance.setPrice(price2);
					insuranceService.addInsurance(insurance);
					HttpHeaders headers = new HttpHeaders();
			        headers.setLocation(ucBuilder.path("/travelInsurance/{id}").buildAndExpand(insurance.getId()).toUri());
			        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
				}else if (insurance.getAgeOfPersons() >= 60){
					double price3 = pricePerPerson - pricePerPerson/20;
					insurance.setPrice(price3);
					insuranceService.addInsurance(insurance);
					HttpHeaders headers = new HttpHeaders();
			        headers.setLocation(ucBuilder.path("/travelInsurance/{id}").buildAndExpand(insurance.getId()).toUri());
			        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
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
					insuranceService.addInsurance(insurance);
					HttpHeaders headers = new HttpHeaders();
			        headers.setLocation(ucBuilder.path("/travelInsurance/{id}").buildAndExpand(insurance.getId()).toUri());
			        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
				}else if (insurance.getAgeOfPersons() >= 18 && insurance.getAgeOfPersons() < 60){
					double price2 = pricePerPerson;
					insurance.setPrice(price2);
					insuranceService.addInsurance(insurance);
					HttpHeaders headers = new HttpHeaders();
			        headers.setLocation(ucBuilder.path("/travelInsurance/{id}").buildAndExpand(insurance.getId()).toUri());
			        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
				}else if (insurance.getAgeOfPersons() >= 60){
					double price3 = pricePerPerson - pricePerPerson/20;
					insurance.setPrice(price3);
					insuranceService.addInsurance(insurance);
					HttpHeaders headers = new HttpHeaders();
			        headers.setLocation(ucBuilder.path("/travelInsurance/{id}").buildAndExpand(insurance.getId()).toUri());
			        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
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
					insuranceService.addInsurance(insurance);
					HttpHeaders headers = new HttpHeaders();
			        headers.setLocation(ucBuilder.path("/travelInsurance/{id}").buildAndExpand(insurance.getId()).toUri());
			        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
				}else if (insurance.getAgeOfPersons() >= 18 && insurance.getAgeOfPersons() < 60){
					double price2 = pricePerPerson;
					insurance.setPrice(price2);
					insuranceService.addInsurance(insurance);
					HttpHeaders headers = new HttpHeaders();
			        headers.setLocation(ucBuilder.path("/travelInsurance/{id}").buildAndExpand(insurance.getId()).toUri());
			        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
				}else if (insurance.getAgeOfPersons() >= 60){
					double price3 = pricePerPerson - pricePerPerson/20;
					insurance.setPrice(price3);
					insuranceService.addInsurance(insurance);
					HttpHeaders headers = new HttpHeaders();
			        headers.setLocation(ucBuilder.path("/travelInsurance/{id}").buildAndExpand(insurance.getId()).toUri());
			        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
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
					insuranceService.addInsurance(insurance);
					HttpHeaders headers = new HttpHeaders();
			        headers.setLocation(ucBuilder.path("/travelInsurance/{id}").buildAndExpand(insurance.getId()).toUri());
			        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
				}else if (insurance.getAgeOfPersons() >= 18 && insurance.getAgeOfPersons() < 60){
					double price2 = pricePerPerson;
					insurance.setPrice(price2);
					insuranceService.addInsurance(insurance);
					HttpHeaders headers = new HttpHeaders();
			        headers.setLocation(ucBuilder.path("/travelInsurance/{id}").buildAndExpand(insurance.getId()).toUri());
			        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
				}else if (insurance.getAgeOfPersons() >= 60){
					double price3 = pricePerPerson - pricePerPerson/20;
					insurance.setPrice(price3);
					insuranceService.addInsurance(insurance);
					HttpHeaders headers = new HttpHeaders();
			        headers.setLocation(ucBuilder.path("/travelInsurance/{id}").buildAndExpand(insurance.getId()).toUri());
			        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
				}
				break;
			}
			default:
				break;
			}
		
		return new ResponseEntity<Integer>(HttpStatus.CONFLICT);

	}
	
	
	@RequestMapping(value = "/homeInsurance", method = RequestMethod.POST)
	public ResponseEntity<Integer> homeInsrurance(@RequestBody Home home,UriComponentsBuilder ucBuilder){
		
		String riskName = home.getTypeOfRisk();
		Risk r = riskService.findRiskByName(riskName);
		AdditionalInsurance additionalInsurance = new AdditionalInsurance();
		int ownerId = customerService.getCustomerIdByCustomerName(home.getOwnerName());
		int duration = home.getDurationOfInsurance();
		Customer c = customerService.getCustomerByName(home.getOwnerName());
		
		if(r == null || c == null || (c.getJmbg() != home.getOwnerJmbg())){
			return new ResponseEntity<Integer>(HttpStatus.CONFLICT);
		}
		
		double price = home.homeInsurancePrice(home.getDurationOfInsurance(), home.getSurface(), home.getAge(),
				home.getEstimatedValue(),r.getPrice());
		home.setPrice(price);
		homeService.addHomeInsurance(home);
		additionalInsurance.setTypeId(home.getId());
		additionalInsurance.setDurationOfAdditionalInsurance(duration);
		additionalInsurance.setPriceOfAdditionalInsurance(price);
		additionalInsurance.setOwenerId(ownerId);
		additionalInsurance.setNameOfAdditionalInsurance("Home Insurance");
		additionalInsuranceService.addAdditionalInsurance(additionalInsurance);
		
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/homeInsurance/{id}").buildAndExpand(home.getId()).toUri());
        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/vehicleInsurance",method = RequestMethod.POST)
	public ResponseEntity<Integer> vehicleInsurance(@RequestBody Vehicle vehicle,UriComponentsBuilder ucBuilder){

		RoadAssistence ras = roadAssistenceService.getByName(vehicle.getPackages());
		int ownerId = customerService.getCustomerIdByCustomerName(vehicle.getOwner());
		AdditionalInsurance additionalInsurance = new AdditionalInsurance();
		Customer c = customerService.getCustomerByName(vehicle.getOwner());
		
		if(!vehicle.getOwnerJmbg().equals(c.getJmbg())){
			return new ResponseEntity<Integer>(HttpStatus.CONFLICT);
		}
		
		
		
		if(ras.getName().equals("Tow")){
			double price = vehicle.getVehicleInsurancePriceTow(vehicle.getDurationOfInsurance(), ras.getPriceOfRoadAssistence());
			vehicle.setPrice(price);
			vehicleService.addVehicle(vehicle);
			additionalInsurance.setDurationOfAdditionalInsurance(vehicle.getDurationOfInsurance());
			additionalInsurance.setNameOfAdditionalInsurance("Vehicle Insurance");
			additionalInsurance.setOwenerId(ownerId);
			additionalInsurance.setPriceOfAdditionalInsurance(price);
			additionalInsurance.setTypeId(vehicle.getId());
			additionalInsuranceService.addAdditionalInsurance(additionalInsurance);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/vehicle/{id}").buildAndExpand(vehicle.getId()).toUri());
			return new ResponseEntity<Integer>(HttpStatus.OK);	
		}else if(ras.getName().equals("Repair")){
			double price = vehicle.getVehicleInsurancePriceRepair(vehicle.getDurationOfInsurance(), ras.getPriceOfRoadAssistence());
			vehicle.setPrice(price);
			vehicleService.addVehicle(vehicle);
			additionalInsurance.setDurationOfAdditionalInsurance(vehicle.getDurationOfInsurance());
			additionalInsurance.setNameOfAdditionalInsurance("Vehicle Insurance");
			additionalInsurance.setOwenerId(ownerId);
			additionalInsurance.setPriceOfAdditionalInsurance(price);
			additionalInsurance.setTypeId(vehicle.getId());
			additionalInsuranceService.addAdditionalInsurance(additionalInsurance);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/vehicle/{id}").buildAndExpand(vehicle.getId()).toUri());
			return new ResponseEntity<Integer>(HttpStatus.OK);	
		}else if(ras.getName().equals("Hotel")){
			double price = vehicle.getVehicleInsurancePriceHotel(vehicle.getDurationOfInsurance(), ras.getPriceOfRoadAssistence());
			vehicle.setPrice(price);
			vehicleService.addVehicle(vehicle);
			additionalInsurance.setDurationOfAdditionalInsurance(vehicle.getDurationOfInsurance());
			additionalInsurance.setNameOfAdditionalInsurance("Vehicle Insurance");
			additionalInsurance.setOwenerId(ownerId);
			additionalInsurance.setPriceOfAdditionalInsurance(price);
			additionalInsurance.setTypeId(vehicle.getId());
			additionalInsuranceService.addAdditionalInsurance(additionalInsurance);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/vehicle/{id}").buildAndExpand(vehicle.getId()).toUri());
			return new ResponseEntity<Integer>(HttpStatus.OK);	
		}else if(ras.getName().equals("Alternative Transport")){
			double price = vehicle.getVehicleInsurancePriceAlternativeTransport(vehicle.getDurationOfInsurance(), ras.getPriceOfRoadAssistence());
			vehicle.setPrice(price);
			vehicleService.addVehicle(vehicle);
			additionalInsurance.setDurationOfAdditionalInsurance(vehicle.getDurationOfInsurance());
			additionalInsurance.setNameOfAdditionalInsurance("Vehicle Insurance");
			additionalInsurance.setOwenerId(ownerId);
			additionalInsurance.setPriceOfAdditionalInsurance(price);
			additionalInsurance.setTypeId(vehicle.getId());
			additionalInsuranceService.addAdditionalInsurance(additionalInsurance);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/vehicle/{id}").buildAndExpand(vehicle.getId()).toUri());
			return new ResponseEntity<Integer>(HttpStatus.OK);	
		}
		
			return new ResponseEntity<Integer>(HttpStatus.CONFLICT);
		

	}
	
}


