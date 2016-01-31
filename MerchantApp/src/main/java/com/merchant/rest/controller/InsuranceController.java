package com.merchant.rest.controller;



import java.util.List;

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

import com.merchant.rest.model.Home;
import com.merchant.rest.model.Risk;
import com.merchant.rest.model.RoadAssistence;
import com.merchant.rest.model.User;
import com.merchant.rest.model.Vehicle;

import com.merchant.rest.service.HomeService;
import com.merchant.rest.service.InsuranceService;
import com.merchant.rest.service.RiskService;
import com.merchant.rest.service.RoadAssistenceService;
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
	private VehicleService vehicleService;
	
	@Autowired
	private RoadAssistenceService roadAssistenceService;
	
	@Autowired
	private InsuranceService insuranceService;
	
	@Autowired
	private RiskService riskService;
	
	@Autowired
	private HomeService homeService;
	
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
	
	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public ResponseEntity<Integer> homeInsrurancePrice(@RequestBody Home home,UriComponentsBuilder ucBuilder){
		
		String riskName = home.getTypeOfRisk();
		Risk r = riskService.findRiskByName(riskName);
		
		if(r == null){
			return new ResponseEntity<Integer>(HttpStatus.CONFLICT);
		}
		
		double price = home.homeInsurancePrice(home.getDurationOfInsurance(), home.getSurface(), home.getAge(),
				home.getEstimatedValue(),r.getPrice());
		home.setPrice(price);
		homeService.addHomeInsurance(home);
		
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/home/{id}").buildAndExpand(home.getId()).toUri());
        return new ResponseEntity<Integer>(headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/vehicle",method = RequestMethod.POST)
	public ResponseEntity<Void> vehicleInsurancePrice(@RequestBody Vehicle vehicle,UriComponentsBuilder ucBuilder){

		RoadAssistence ras = roadAssistenceService.getByName(vehicle.getPackages());

		if(ras.getName().equals("Tow")){
			double price = vehicle.getVehicleInsurancePriceTow(vehicle.getDurationOfInsurance(), ras.getPriceOfRoadAssistence());
			vehicle.setPrice(price);
			vehicleService.addVehicle(vehicle);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/vehicle/{id}").buildAndExpand(vehicle.getId()).toUri());
			return new ResponseEntity<Void>(HttpStatus.CREATED);	
		}else if(ras.getName().equals("Repair")){
			double price = vehicle.getVehicleInsurancePriceRepair(vehicle.getDurationOfInsurance(), ras.getPriceOfRoadAssistence());
			vehicle.setPrice(price);
			vehicleService.addVehicle(vehicle);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/vehicle/{id}").buildAndExpand(vehicle.getId()).toUri());
			return new ResponseEntity<Void>(HttpStatus.CREATED);	
		}else if(ras.getName().equals("Hotel")){
			double price = vehicle.getVehicleInsurancePriceHotel(vehicle.getDurationOfInsurance(), ras.getPriceOfRoadAssistence());
			vehicle.setPrice(price);
			vehicleService.addVehicle(vehicle);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/vehicle/{id}").buildAndExpand(vehicle.getId()).toUri());
			return new ResponseEntity<Void>(HttpStatus.CREATED);	
		}else if(ras.getName().equals("Alternative Transport")){
			double price = vehicle.getVehicleInsurancePriceAlternativeTransport(vehicle.getDurationOfInsurance(), ras.getPriceOfRoadAssistence());
			vehicle.setPrice(price);
			vehicleService.addVehicle(vehicle);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/vehicle/{id}").buildAndExpand(vehicle.getId()).toUri());
			return new ResponseEntity<Void>(HttpStatus.CREATED);	
		}
		
		return new ResponseEntity<Void>(HttpStatus.CONFLICT);

	}
	
}


