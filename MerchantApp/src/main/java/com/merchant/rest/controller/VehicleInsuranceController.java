package com.merchant.rest.controller;

import org.hibernate.Session;
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
import com.merchant.rest.model.RoadAssistence;
import com.merchant.rest.model.Vehicle;
import com.merchant.rest.service.AdditionalInsuranceService;
import com.merchant.rest.service.CustomerService;
import com.merchant.rest.service.InsuranceService;
import com.merchant.rest.service.RoadAssistenceService;
import com.merchant.rest.service.VehicleService;

@Controller
@RequestMapping("/")
public class VehicleInsuranceController {


	@Autowired
	SessionFactory sessionFactory;

	@Autowired(required = true)
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Autowired
	private InsuranceService insuranceService;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private RoadAssistenceService roadAssistenceService;
	
	@Autowired
	private AdditionalInsuranceService additionalInsuranceService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired(required = true)
	@Qualifier(value = "additionalInsuranceService")
	public void setAdditionalInsuranceService(
			AdditionalInsuranceService additionalInsuranceService) {
		this.additionalInsuranceService = additionalInsuranceService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "customerService")
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "roadAssistenceService")
	public void setRoadAssistenceService(
			RoadAssistenceService roadAssistenceService) {
		this.roadAssistenceService = roadAssistenceService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "vehicleService")
	public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "insuranceService")
	public void setInsuranceService(InsuranceService insuranceService) {
		this.insuranceService = insuranceService;
	}
	
	@RequestMapping(value = "/vehicleInsurance",method = RequestMethod.POST)
	public ResponseEntity<Integer> vehicleInsurance(@RequestBody Vehicle vehicle,UriComponentsBuilder ucBuilder){

		RoadAssistence ras = roadAssistenceService.getByName(vehicle.getPackages());
		int ownerId = customerService.getCustomerIdByCustomerName(vehicle.getOwner());
		AdditionalInsurance additionalInsurance = new AdditionalInsurance();
		Customer c = customerService.getCustomerByName(vehicle.getOwner());
		Insurance i = insuranceService.getInsuranceByOwnerName(vehicle.getOwner());
		
		Session session = this.sessionFactory.getCurrentSession();
		
		if(c == null || (!vehicle.getOwnerJmbg().equals(c.getJmbg()))){
			return new ResponseEntity<Integer>(HttpStatus.CONFLICT);
		}
		if(!c.isOwner()){
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
			i.setAdditionalInsuranceId(vehicle.getId());
			insuranceService.saveInsurance(i);
			additionalInsuranceService.addAdditionalInsurance(additionalInsurance);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(vehicle.getId()).toUri());
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
			i.setAdditionalInsuranceId(vehicle.getId());
			insuranceService.saveInsurance(i);
			additionalInsuranceService.addAdditionalInsurance(additionalInsurance);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(vehicle.getId()).toUri());
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
			i.setAdditionalInsuranceId(vehicle.getId());
			insuranceService.saveInsurance(i);
			additionalInsuranceService.addAdditionalInsurance(additionalInsurance);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(vehicle.getId()).toUri());
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
			i.setAdditionalInsuranceId(vehicle.getId());
			insuranceService.saveInsurance(i);
			additionalInsuranceService.addAdditionalInsurance(additionalInsurance);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(vehicle.getId()).toUri());
			return new ResponseEntity<Integer>(HttpStatus.OK);	
		}
		
			return new ResponseEntity<Integer>(HttpStatus.CONFLICT);
		

	}
}
