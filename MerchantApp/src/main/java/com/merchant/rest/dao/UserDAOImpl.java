package com.merchant.rest.dao;

import java.util.List;










import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;













import com.merchant.rest.model.User;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO{

	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired(required = true)
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void userRegistration(User u) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(u);
		logger.info("User registrated successfully, Details="+u);
	}


	@Override
	public void updateUser(User u) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(u);
		logger.info("User updated successfully,  Details="+u);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<User> listUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> usersList = session.createQuery("from User").list();
		for(User u : usersList){
			logger.info("Users List::"+u);
		}
		return usersList;
	}


	@Override
	public User findUserById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		User u = (User) session.load(User.class, new Integer(id));
		logger.info("User loaded successfully, details="+u);
		return u;
	}

	@Override
	public User findUserByUsername(String username) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("from User where username=:username").setString("username", username);
		User u = (User) q.uniqueResult();
		return u;
	}

	@Override
	public User userLoginCheck(String username, String password) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("from User where username=:username and password=:password").setString("username", username).setString("password", password);
		User u = (User) q.uniqueResult();
		return u;
	}
}