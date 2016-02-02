package sep.pcc.rest.controler;

import java.util.Calendar;

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
@RequestMapping("/")
public class PccController {

	@Autowired
	BankService bankService;
	
	@Autowired(required = true)
	@Qualifier(value = "bankService")
	public void setPaymentService(BankService bankService){
		this.bankService = bankService;
	}
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired(required = true)
	@Qualifier(value = "paymentService")
	public void setPaymentService(PaymentService service){
		this.paymentService = service;
	}

	
	@RequestMapping(value = "/payment/create",method = RequestMethod.POST)
	public ResponseEntity<Response> create(@RequestBody Payment payment, UriComponentsBuilder ucBuilder){
		
		Response response = new Response();
		try
		{
			validate(payment);	
			paymentService.add(payment);
			response.setSuccess(true);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/payment/{id}").buildAndExpand(payment.getId()).toUri());
			return new ResponseEntity<Response>(response,headers,HttpStatus.CREATED);
		}
		catch(Exception ex)
		{
			response.setSuccess(false);
			
			for(String message : ex.getMessage().split(System.getProperty("line.separator")))
			{
				response.addMessage(message);
			}
			return new ResponseEntity<Response>(response,HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@RequestMapping(value = "/bank/create",method = RequestMethod.POST)
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
			
			validate(payment);
			RestTemplate client = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Payment> entity = new HttpEntity<Payment>(payment,headers);
			String bankServiceUri = getBankServiceUri(payment.getPAN());
			
			response = client.postForObject(bankServiceUri + "/withdraw",entity, Response.class);
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
	
	private void validate(Payment payment) throws Exception
	{
		StringBuilder sb = new StringBuilder();
		Calendar threshold = Calendar.getInstance();
		threshold.add(Calendar.SECOND, -10);
		
		if(payment.getTimestamp() == null || !(threshold.getTime().getSeconds() < payment.getTimestamp().getSeconds()))
		{
			sb.append("Invalid timestamp");
			sb.append(System.getProperty("line.separator"));
		}
		if(payment.getPAN() == null || !bankService.exists(payment.getPAN()))
		{
			sb.append("Bank with provided PAN does not exist!");
			sb.append(System.getProperty("line.separator"));
		}
		
		if(sb.length() > 0)
		{
			throw new Exception(sb.toString());
		}
	}
	
	
	private String getBankServiceUri(String pan) throws Exception{
		
			String serviceUri;
			Bank bank = bankService.find(pan);
			
			if(bank == null)
			{
				throw new Exception("Bank with provided pan does not exist.");
			}
			
	
			return bank.getServiceUri();
		
	}
	
	
}
