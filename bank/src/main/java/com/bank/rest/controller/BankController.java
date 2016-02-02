package com.bank.rest.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
			card.setResrevedAmount(card.getResrevedAmount() + payment.getAmount());
			card.setAmount(card.getAmount() - payment.getAmount());
			cardService.update(card);
			response.setSuccess(true);
			response.addMessage("Funds withdrawn successfully");
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
		
		if(card == null)
		{
			builder.append("Invalid payment data. Please provide valid card details");
			builder.append(System.getProperty("line.separator"));
		}
		else if(new Date().after(card.getExpirationDate()))
		{
			System.out.println("valid to: " + payment.getValidTo());
			builder.append("Card has been expired.");
			builder.append(System.getProperty("line.separator"));
		}
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
	
}
