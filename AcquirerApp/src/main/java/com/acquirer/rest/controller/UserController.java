package com.acquirer.rest.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.acquirer.rest.model.AntiForgery;
import com.acquirer.rest.model.User;
import com.acquirer.rest.service.UserService;
import com.acquirer.rest.utils.RandomGenerator;

@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired(required = true)
	@Qualifier(value = "userService")
	public void setUserService(UserService us){
		this.userService = us;
	}
	
	@RequestMapping(value="/antiForgeryToken", method= RequestMethod.POST)
	public ResponseEntity<String> antiforgeryToken(){
		
		RandomGenerator gen = new RandomGenerator();
		String token = gen.generateAntiForgeryToken();
		AntiForgery antiForgery = new AntiForgery(token, true);
		userService.addToken(antiForgery);
		return new ResponseEntity<String>(gen.generateAntiForgeryToken(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/users",method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users = userService.get();
		if(users.isEmpty()){
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/registration",method = RequestMethod.POST)
	public ResponseEntity<Void> userRegistration(@RequestBody User user, UriComponentsBuilder ucBuilder){
		
		for(User u : userService.get()){
			if(u.getId() == user.getId()){
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}
		}
		userService.add(user);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") int id,@RequestBody User user){
		User currentUser = userService.getById(id);
		
		if(currentUser == null){
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		currentUser.setId(id);
		currentUser.setFirstName(user.getFirstName());
		currentUser.setLastName(user.getLastName());
		currentUser.setUsername(user.getUsername());
		currentUser.setPassword(user.getPassword());
		
		userService.update(currentUser);
		return new ResponseEntity<User>(currentUser,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public ResponseEntity<Void> userLogin(@RequestBody User user, UriComponentsBuilder ucBuilder){
		
		ResponseEntity<Void> response;
		User usr = userService.findUserByUsername(user.getUsername());
		
		if(usr != null){
			response = new ResponseEntity<Void>(HttpStatus.OK);
		}
		else
		{
			response = new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}

		return response;
		}
	
}
