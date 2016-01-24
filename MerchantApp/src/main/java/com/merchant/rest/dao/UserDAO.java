package com.merchant.rest.dao;

import java.util.List;

import com.merchant.rest.model.User;

public interface UserDAO {

	public void userRegistration(User u);
	public void updateUser(User u);
	public List<User> listUsers();
	public User findUserById(int id);
//	public User checkUserByUsername(String username);
//	public List<User> selectUser();
}
