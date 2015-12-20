package com.merchant.rest.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.merchant.rest.model.Customer;
import com.merchant.rest.service.CustomerService;





@RestController
public class CustomerController {

	private CustomerService customerService;
	@Autowired(required = true)
	@Qualifier(value = "customerService")
	public void setCustomerService(CustomerService cs){
		this.customerService = cs;
	}
	
	
	/**
	 * 
	 * Metode koje se testiraju u Postman-u
	 */
	@RequestMapping(value = "/customer/", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> listAllCustomers() {
        List<Customer> customers = customerService.listCustomers();
        if(customers.isEmpty()){
            return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }
	
	 @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Customer> getCustomer(@PathVariable("id") int id) {
	       
	        Customer customer = customerService.getCustomerById(id);
	        if (customer == null) {
	            System.out.println("Customer with id " + id + " not found");
	            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	 
	 
	 @RequestMapping(value = "/customer/add/", method = RequestMethod.POST)
	    public ResponseEntity<Void> createCustomer (@RequestBody Customer customer,    UriComponentsBuilder ucBuilder) {
	        
	 
	        for(Customer c : customerService.listCustomers()){
	        	if(c.getId() == customer.getId()){
	  	            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	        	}
	        }
	        customerService.addCustomer(customer);
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/customer/{id}").buildAndExpand(customer.getId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	    }
	 
	 
	 @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") int id) {
	        
	        Customer customer = customerService.getCustomerById(id);
	        if (customer == null) {
	            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	        }
	 
	        customerService.removeCustomer(id);
	        return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	    }
	
	/*@Autowired(required = true)
	@Qualifier(value = "customerService")
	public void setCustomerService(CustomerService cs){
		this.customerService = cs;
	}
	
	@RequestMapping(value = "/customers",method = RequestMethod.GET)
	public String listCustomers(Model model){
		model.addAttribute("customer", new Customer());
		model.addAttribute("listCustomers", this.customerService.listCustomers());
		return "customer";
	}
	
	@RequestMapping(value = "/customer/add",method = RequestMethod.POST)
	public String addCustomer(@ModelAttribute("customer") Customer c){
		if(c.getId() == 0){
			this.customerService.addCustomer(c);
		}else{
			this.customerService.updateCustomer(c);
		}
		return "redirect:/customers";
	}
	
	@RequestMapping(value = "/remove/{id}")
	public String removeCustomer(@PathVariable("id") int id){
		this.customerService.removeCustomer(id);
		return "redirect:/customers";
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String editCustomer(@PathVariable("id") int id,Model model){
		model.addAttribute("customer", this.customerService.getCustomerById(id));
		model.addAttribute("listCustomers", this.customerService.listCustomers());
		return "customer";
	}*/
}
