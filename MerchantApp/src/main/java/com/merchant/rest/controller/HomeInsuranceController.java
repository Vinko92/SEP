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
import com.merchant.rest.model.Home;
import com.merchant.rest.model.Insurance;
import com.merchant.rest.model.Risk;
import com.merchant.rest.service.AdditionalInsuranceService;
import com.merchant.rest.service.CustomerService;
import com.merchant.rest.service.HomeService;
import com.merchant.rest.service.InsuranceService;
import com.merchant.rest.service.RiskService;

@Controller
@RequestMapping("/")
public class HomeInsuranceController {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired(required = true)
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Autowired
	private InsuranceService insuranceService;
	
	@Autowired
	private HomeService homeService;
	
	@Autowired
	private AdditionalInsuranceService additionalInsuranceService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private RiskService riskService;
	
	@Autowired(required = true)
	@Qualifier(value = "homeService")
	public void setHomeService(HomeService homeService) {
		this.homeService = homeService;
	}
	
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
	@Qualifier(value = "riskService")
	public void setRiskService(RiskService riskService) {
		this.riskService = riskService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "insuranceService")
	public void setInsuranceService(InsuranceService insuranceService) {
		this.insuranceService = insuranceService;
	}
	
	@RequestMapping(value = "/homeInsurance", method = RequestMethod.POST)
	public ResponseEntity<Double> homeInsrurance(@RequestBody Home home,UriComponentsBuilder ucBuilder){
		
		String riskName = home.getTypeOfRisk();
		Risk r = riskService.findRiskByName(riskName);
		AdditionalInsurance additionalInsurance = new AdditionalInsurance();
		int ownerId = customerService.getCustomerIdByCustomerName(home.getOwnerName());
		int duration = home.getDurationOfInsurance();
		Customer c = customerService.getCustomerByName(home.getOwnerName());
		Insurance i = insuranceService.getInsuranceByOwnerName(home.getOwnerName());
		
		
		
		if(r == null || c == null || (!c.getJmbg().equals(home.getOwnerJmbg()))){
			return new ResponseEntity<Double>(HttpStatus.CONFLICT);
		}
		if(!c.isOwner()){
			return new ResponseEntity<Double>(HttpStatus.CONFLICT);
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
		i.setAdditionalInsuranceId(home.getId());
		insuranceService.saveInsurance(i);
		additionalInsuranceService.addAdditionalInsurance(additionalInsurance);
		
        return new ResponseEntity<Double>(price, HttpStatus.OK);
	}
}
