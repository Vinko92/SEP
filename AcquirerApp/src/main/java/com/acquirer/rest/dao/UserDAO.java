package com.acquirer.rest.dao;

import java.util.List;

import com.acquirer.rest.model.AntiForgery;
import com.acquirer.rest.model.User;

public interface UserDAO {

	void add(User user);
	void update(User user);
	List<User> get();
	User getById(int id);
	void remove(int id);
	AntiForgery getToken(int id);
	AntiForgery getToken(String value);
	void addToken(AntiForgery token);
	void updateToken(AntiForgery token);
	void deleteToken(AntiForgery token);
}
