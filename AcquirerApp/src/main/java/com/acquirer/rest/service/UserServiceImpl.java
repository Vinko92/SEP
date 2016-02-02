package com.acquirer.rest.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.acquirer.rest.dao.UserDAOImpl;
import com.acquirer.rest.model.AntiForgery;
import com.acquirer.rest.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl  implements UserService {

	@Autowired
	@Qualifier("userDAO")
	private UserDAOImpl userDAO;
	
	
	@Autowired(required = true)
	public void setUserDAO(UserDAOImpl userDAO){
		this.userDAO = userDAO;
	}


	@Override
	public void add(User entity) {
		userDAO.add(entity);
	}

	@Override
	public void update(User entity) {

		userDAO.update(entity);
	}

	@Override
	public List<User> get() {

		return userDAO.get();
	}

	@Override
	public User getById(int id) {
		return userDAO.getById(id);
	}


	@Override
	public void remove(int id) {

		userDAO.remove(id);
	}

	
	@Override
	public User findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
		
	@Override
	public AntiForgery getToken(int id)
	{
		return userDAO.getToken(id);
	}

	@Override
	public AntiForgery getToken(String value)
	{
		return userDAO.getToken(value);
	}

	@Override
	public void addToken(AntiForgery token) {
		userDAO.addToken(token);
		
	}


	@Override
	public void updateToken(AntiForgery token) {
		// TODO Auto-generated method stub
		userDAO.updateToken(token);
	}
		
	@Override
	public void deleteToken(AntiForgery token) {
		// TODO Auto-generated method stub
		userDAO.deleteToken(token);
	}
	public boolean isForgeryRequest(String tokenValue, AntiForgery token)
	{
		token = userDAO.getToken(tokenValue);
		
		if(token != null && token.isActive())
		{
			return true;
		}
		else
		{
			return false;
		}
	}


}
