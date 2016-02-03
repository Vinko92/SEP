package com.acquirer.rest.service;

import java.util.List;

import com.acquirer.rest.model.AntiForgery;
import com.acquirer.rest.model.User;

public interface UserService{
	
	void add(User entity);
	void update(User entity);
	List<User> get();
	User getById(int id);
	void remove(int id);
    User findUserByUsername(String username);
    AntiForgery getToken(int id);
    AntiForgery getToken(String value);
    void addToken(AntiForgery token);
    void updateToken(AntiForgery token);
    void deleteToken(AntiForgery token);
    /**
     * Checking for presence of a valid anti forgery token. If value is valid, set token object to that value. 
     * @param tokenValue Value of token from the request
     * @param token Valid token stored in database
     * @return flag which indicates if request is invalid
     */
    boolean isForgeryRequest(String tokenValue, AntiForgery token);
}
