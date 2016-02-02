package sep.pcc.rest.controler;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import sep.pcc.rest.model.Bank;
import sep.pcc.rest.model.Payment;
import sep.pcc.rest.model.Response;
import sep.pcc.rest.service.BankService;
import sep.pcc.rest.service.PaymentService;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	
	@Autowired(required = true)
	@Qualifier(value = "paymentService")
	public void setPaymentService(PaymentService service){
		this.paymentService = service;
	}
	
	@Autowired
	BankService bankService;
	
	@Autowired(required = true)
	@Qualifier(value = "bankService")
	public void setBankService(BankService bankService){
		this.bankService = bankService;
	}
	
	@RequestMapping(value = "/create",method = RequestMethod.POST)
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
	
	
}
