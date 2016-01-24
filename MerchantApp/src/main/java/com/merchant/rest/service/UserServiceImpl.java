package com.merchant.rest.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.merchant.rest.dao.UserDAOImpl;
import com.merchant.rest.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("userDAO")
	private UserDAOImpl userDAO;
	
	
	@Autowired(required = true)
	public void setUserDAO(UserDAOImpl userDAO){
		this.userDAO = userDAO;
	}
	
	@Transactional
	@Override
	public void userRegistration(User u) {
		this.userDAO.userRegistration(u);
		
	}

	@Transactional
	@Override
	public void updateUser(User u) {
		this.userDAO.updateUser(u);
		
	}

	@Transactional
	@Override
	public List<User> listUsers() {
		return this.userDAO.listUsers();
	}

	@Transactional
	@Override
	public User findUserById(int id) {
		return this.userDAO.findUserById(id);
	}

//	@Override
//	public User checkUserByUsername(String username) {
//		return this.userDAO.checkUserByUsername(username);
//	}
//
//	@Override
//	public List<User> selectUser() {
//		return this.userDAO.selectUser();
//	}	
	
}
