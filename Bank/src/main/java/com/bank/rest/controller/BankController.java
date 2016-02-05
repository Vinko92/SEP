package com.bank.rest.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.bank.rest.model.Card;
import com.bank.rest.model.Payment;
import com.bank.rest.model.Response;
import com.bank.rest.service.CardService;
import com.bank.rest.service.PaymentService;
import com.bank.rest.service.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

@Controller
@RequestMapping("/")
public class BankController {

	@Autowired
	private UserService userService;
	
	@Autowired(required = true)
	@Qualifier(value = "userService")
	public void setUserService(UserService us){
		this.userService = us;
	}
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired(required = true)
	@Qualifier(value = "paymentService")
	public void setUserService(PaymentService paymentService){
		this.paymentService = paymentService;
	}
	

	@Autowired
	private CardService cardService;
	
	@Autowired(required = true)
	@Qualifier(value = "cardService")
	public void setCardService(CardService cardService){
		this.cardService = cardService;
	}
	
	@RequestMapping(value = "/hello",method = RequestMethod.GET)
	public ResponseEntity<String> userRegistration(){

		return new ResponseEntity<String>("hello", HttpStatus.OK);
		
	}

	
	
	@RequestMapping(value = "/withdraw",method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Response> userRegistration(@RequestBody Payment payment, UriComponentsBuilder ucBuilder){
		Response response = processPayment(payment);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}

///Private helper methods	
	private Response processPayment(Payment payment)
	{
		Response response = new Response();
		
		try
		{
			Card card = getCard(payment);
			validatePayment(payment, card);
			paymentService.add(payment);
			System.out.println(payment.getId());
			withdrawMoney(card, payment.getAmount());
			response.setSuccess(true);
			response.addMessage("Funds withdrawn successfully");
			response.setAcquirerOrderId(payment.getOrderId());
			response.setAcquirerTimestamp(payment.getTimestamp());
			response.setIssuerTimestamp(new Date());
			response.setIssuerOrderId(payment.getId());
		}
		catch(Exception ex)
		{
			for(String message : ex.getMessage().split(System.getProperty("line.separator"))){
				response.addMessage(message);
			}
			response.setSuccess(false);
		}
		return response;
		
	}
	
	private Card getCard(Payment payment) throws ParseException
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(format.format(payment.getValidTo()));
		return cardService.find(payment.getSecurityCode(), 
								date,
							 	payment.getCardHolder());
	}
	
	private void validatePayment(Payment payment,Card card) throws Exception
	{
		StringBuilder builder = new StringBuilder();
		Calendar maxTimestamp  = Calendar.getInstance();
		Calendar minTimestamp  = Calendar.getInstance();
		
		maxTimestamp.add(Calendar.SECOND, 10);
		minTimestamp.add(Calendar.SECOND, -10);
		System.out.println(payment.getTimestamp());
		System.out.println(maxTimestamp.getTime());
		System.out.println(minTimestamp.getTime());
		
		if(card == null)
		{
			builder.append("Invalid payment data. Please provide valid card details");
			builder.append(System.getProperty("line.separator"));
		}
		else if(new Date().after(card.getExpirationDate()))
		{
			builder.append("Card has been expired.");
			builder.append(System.getProperty("line.separator"));
		}
		/*else if(payment.getTimestamp() == null ||
				payment.getTimestamp().before(minTimestamp.getTime()) || 
				payment.getTimestamp().after(maxTimestamp.getTime()))
		{
			builder.append("Invalid timestamp. Please, make sure that your clock is in sync with server clock.");
			builder.append(System.getProperty("line.separator"));
		}*/
		else if(payment.getAmount() > card.getAmount())
		{
			builder.append("Insufficient funds in bank account.");
			builder.append(System.getProperty("line.separator"));
		}
		
		
		if(builder.length() > 0)
		{
			throw new Exception(builder.toString());
		}
	}
	
	private void withdrawMoney(Card card, float amount)
	{
		card.setResrevedAmount(card.getResrevedAmount() + amount);
		card.setAmount(card.getAmount() - amount);
		cardService.update(card);
	}
	
	
}
