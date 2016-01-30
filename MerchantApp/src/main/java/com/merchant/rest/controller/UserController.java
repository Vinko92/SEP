package com.merchant.rest.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.merchant.rest.model.User;
import com.merchant.rest.service.UserService;

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
	
	@RequestMapping(value = "/user",method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users = userService.listUsers();
		if(users.isEmpty()){
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/registration",method = RequestMethod.POST)
	public ResponseEntity<Void> userRegistration(@RequestBody User user, UriComponentsBuilder ucBuilder){
		
		User usr = userService.findUserByUsername(user.getUsername());
		
		if(usr != null){
			for(User u : userService.listUsers()){
				if(u.getUsername().equals(usr.getUsername())){
					return new ResponseEntity<Void>(HttpStatus.CONFLICT);
				}
			}
		}
		userService.userRegistration(user);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/update/{username}",method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("username") String username,@RequestBody User user){
		User currentUser = userService.findUserByUsername(username);
		
		if(currentUser == null){
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		
		currentUser.setName(user.getName());
		currentUser.setSurname(user.getSurname());
		currentUser.setUsername(user.getUsername());
		currentUser.setPassword(user.getPassword());
		
		userService.updateUser(currentUser);
		return new ResponseEntity<User>(currentUser,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public ResponseEntity<Void> userLogin(@RequestBody User user, UriComponentsBuilder ucBuilder){
		
		User usr = userService.userLoginCheck(user.getUsername(), user.getPassword());
	
		if(usr != null){
			for(User u : userService.listUsers()){
				if(u.getUsername().equals(usr.getUsername()) && u.getPassword().equals(usr.getPassword())){
					return new ResponseEntity<Void>(HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	

}
