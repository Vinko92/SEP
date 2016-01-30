package com.merchant.rest.service;

import java.util.List;

import com.merchant.rest.model.User;

public interface UserService {

	public void userRegistration(User u);
	public void updateUser(User u);
	public List<User> listUsers();
	public User findUserById(int id);
	public User findUserByUsername(String username);
	public User userLoginCheck(String username,String password);
}
