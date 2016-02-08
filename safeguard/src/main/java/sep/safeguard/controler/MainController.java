package sep.safeguard.controler;

import java.util.Calendar;
import java.util.Date;

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

import sep.safeguard.model.AcquirerOrder;
import sep.safeguard.model.Merchant;
import sep.safeguard.model.MerchantOrder;
import sep.safeguard.model.Payment;
import sep.safeguard.model.Response;
import sep.safeguard.service.AcquirerOrderService;
import sep.safeguard.service.MerchantService;
import sep.safeguard.service.MerchantOrderService;
import sep.safeguard.service.PaymentService;


@Controller
@RequestMapping("/")
public class MainController {

	@Autowired
	MerchantService merchantService;
	
	@Autowired(required = true)
	@Qualifier(value = "merchantService")
	public void setMerchantService(MerchantService merchantService){
		this.merchantService = merchantService;
	}
	
	@Autowired
	AcquirerOrderService acqurerOrderService;
	
	@Autowired(required = true)
	@Qualifier(value = "acquirerOrderService")
	public void setAcquirerOrderServicee(AcquirerOrderService acqurerOrderService){
		this.acqurerOrderService = acqurerOrderService;
	}
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired(required = true)
	@Qualifier(value = "paymentService")
	public void setPaymentService(PaymentService paymentService){
		this.paymentService = paymentService;
	}
	
	@Autowired
	MerchantOrderService orderService;
	
	@Autowired(required = true)
	@Qualifier(value = "merchantOrderService")
	public void setMerchantOrderService(MerchantOrderService orderService){
		this.orderService = orderService;
	}
	
	@RequestMapping(value = "/hello",method = RequestMethod.GET)
	public ResponseEntity<String> hello()
	{
		return new ResponseEntity<String>("hello", HttpStatus.OK);
	}

	@RequestMapping(value = "/order/create",method = RequestMethod.POST)
	public ResponseEntity<Response> create(@RequestBody MerchantOrder order, UriComponentsBuilder ucBuilder){
		
		Response response = new Response();
		try
		{
			validate(order);	
			orderService.add(order);
			Payment payment = new Payment();
			payment.setUrl("http://localhost:8080/safeguard/payment/create/");
			payment.setAmount(order.getAmount());
			paymentService.add(payment);
			response.setPaymentId(payment.getId());
			response.setPaymentUrl(payment.getUrl() + payment.getId());
			response.setSuccess(true);
			return new ResponseEntity<Response>(response,HttpStatus.CREATED);
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
	

	@RequestMapping(value = "payment/create/{id}", method = RequestMethod.POST)
    public ResponseEntity<Response> createPayment(@PathVariable("id")int id, Payment payment) {
	
		Response response = new Response();
		
		try
		{
			validatePayment(payment);
			response = sendRequest(createAcquirerOrder(payment));
			return new ResponseEntity<Response>(response, HttpStatus.OK);
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
	
	
	///Private helper methods
	
	private Response sendRequest(AcquirerOrder order)
	{
		RestTemplate client = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<AcquirerOrder> entity = new HttpEntity<AcquirerOrder>(order,headers);
		String pccServiceUrl = "http://localhost:8080/pcc";
		return  client.postForObject(pccServiceUrl + "/payment/create",entity, Response.class);
	}
	
	private AcquirerOrder createAcquirerOrder(Payment payment)
	{
		AcquirerOrder order = new AcquirerOrder();
		order.setAmount(payment.getAmount());
		order.setCardHolder(payment.getCardHolder());
		order.setExpirationDate(payment.getExpirationDate());
		order.setTimestamp(new Date());
		order.setPan(payment.getPan());
		order.setSecurityCode(payment.getSecurityCode());
		acqurerOrderService.add(order);
		
		return order;
		
	}
	
	private void validatePayment(Payment payment)
	{
		
	}
	
	private void validate(MerchantOrder order) throws Exception
	{
		authenticateMerchant(order);
		
		StringBuilder sb = new StringBuilder();
		Calendar maxTimestamp  = Calendar.getInstance();
		Calendar minTimestamp  = Calendar.getInstance();
		
		maxTimestamp.add(Calendar.SECOND, 10);
		minTimestamp.add(Calendar.SECOND, -10);
		/*
		if(order.getTimestamp() == null ||
		   order.getTimestamp().before(minTimestamp.getTime()) || 
		   order.getTimestamp().after(maxTimestamp.getTime()))
		{
			sb.append("Invalid timestamp. Please, make sure that your clock is in sync with server clock.");
			sb.append(System.getProperty("line.separator"));
		}*/
		if(!(order.getAmount() > 0))
		{
			sb.append("Invalid amount value! Please, provide number greater then zero.");
			sb.append(System.getProperty("line.separator"));
		}
		
		if(sb.length() > 0)
		{
			throw new Exception(sb.toString());
		}
	}
	
	private void authenticateMerchant(MerchantOrder order) throws Exception
	{
		Merchant merchant = order.getMerchant();
		StringBuilder errorList = new StringBuilder();
		validateMerchant(merchant);
		
		Merchant dbMerchant = merchantService.getByIdentifier(merchant.getIdentifier());
		
		if(dbMerchant == null)
		{
			errorList.append("Merchant with provided identifier does not exist!");
		}
		else if(!merchant.getPassword().equals(dbMerchant.getPassword()))
		{
			errorList.append("Invalid password");
		}
		
		merchant.setId(dbMerchant.getId());
		
		if(errorList.length() > 0)
		{
			throw new Exception(errorList.toString());
		}
	}
	
	public void validateMerchant(Merchant merchant) throws Exception
	{
		StringBuilder errorList = new StringBuilder();

		
		if(merchant == null)
		{
			errorList.append("Please provide merchant details!");
		}
		else if(merchant.getPassword() == null)
		{
			errorList.append("Please provide merchant's password");
		}
		else if(merchant.getIdentifier() == null)
		{
			errorList.append("Please provide merchant's identifier");
		}
		
		if(errorList.length() > 0)
		{
			throw new Exception(errorList.toString());
		}
		
	}
	
	
	
}
