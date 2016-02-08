package sep.safeguard.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sep.safeguard.model.AntiforgeryToken;
import sep.safeguard.service.AntiforgeryService;
import sep.safeguard.utility.RandomStringGenerator;

@Controller
@RequestMapping("/")
public class AntiforgeryController {

	AntiforgeryService antiforgeryService;
	
	@Autowired(required = true)
	@Qualifier(value = "antiforgeryService")
	public void setAntiforgeryService(AntiforgeryService antiforgeryService){
		this.antiforgeryService = antiforgeryService;
	}
	
	@RequestMapping(value = "/auth/token/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getToken(@PathVariable("id")int id) {
		
		String value = RandomStringGenerator.getRandomString();
		AntiforgeryToken token = new AntiforgeryToken(value,String.valueOf(id));
		antiforgeryService.add(token);
		
		return new ResponseEntity<String>(value, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/auth/validate", method = RequestMethod.POST)
	public ResponseEntity<Boolean> validateToken(@RequestBody AntiforgeryToken token)
	{
		return new ResponseEntity<Boolean>(antiforgeryService.isValid(token), HttpStatus.OK);
	}
	
	
}
