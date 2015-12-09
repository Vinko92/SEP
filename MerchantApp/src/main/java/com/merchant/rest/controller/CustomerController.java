package com.merchant.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.merchant.rest.model.Customer;
import com.merchant.rest.service.CustomerService;

@Controller
public class CustomerController {

	private CustomerService customerService;
	
	@Autowired(required = true)
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
	}
}
