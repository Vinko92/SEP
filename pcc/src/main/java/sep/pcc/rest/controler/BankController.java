package sep.pcc.rest.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;

import sep.pcc.rest.constants.Constants;
import sep.pcc.rest.model.Bank;
import sep.pcc.rest.model.Payment;
import sep.pcc.rest.model.Response;
import sep.pcc.rest.service.BankService;
import sep.pcc.rest.service.PaymentService;

@Controller
@RequestMapping("/bank")
public class BankController {

	@Autowired
	BankService bankService;
	
	@Autowired(required = true)
	@Qualifier(value = "bankService")
	public void setPaymentService(BankService bankService){
		this.bankService = bankService;
	}
	
	@RequestMapping(value = "/create",method = RequestMethod.POST)
	public ResponseEntity<Response> create(@RequestBody Bank bank, UriComponentsBuilder ucBuilder){
		
		Response response = new Response();
		try
		{
			bankService.add(bank);
			response.setSuccess(true);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/bank/{id}").buildAndExpand(bank.getId()).toUri());
			return new ResponseEntity<Response>(response,headers, HttpStatus.CREATED);
		}
		catch(Exception ex)
		{
			response.setSuccess(false);
			response.addMessage(ex.getMessage());
			return new ResponseEntity<Response>(response,HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	
	@RequestMapping(value = "/requestPayment", method = RequestMethod.POST)
	public ResponseEntity<Response> requestPayment(@RequestBody Payment payment, UriComponentsBuilder ucBuilder){
		Response response = new Response();
		
		try{
			validatePayment(payment);
			RestTemplate client = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Payment> entity = new HttpEntity<Payment>(payment,headers);
			
			response = client.postForObject(Constants.BANK_SERVICE + "/withdraw",entity, Response.class);
			System.out.println(response.isSuccess());
			
		}
		catch(Exception ex){
			for(String message : ex.getMessage().split(System.getProperty("line.separator"))){
				response.addMessage(message);
			}
			response.setSuccess(false);
		}
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	
	///Private helper methods
	
	public void validatePayment(Payment payment)
	{
		
	}
	
	
	
	
}
