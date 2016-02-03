package com.acquirer.rest.dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.acquirer.rest.model.AntiForgery;
import com.acquirer.rest.model.User;

@Repository("userDao")
public class UserDAOImpl implements UserDAO {

	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UserDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired(required = true)
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	
	@Override
	public void add(User entity) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(entity);
		logger.info("Entity" + entity.getClass() + " saved successfully");
		
	}

	@Override
	public void update(User entity) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(entity);
		logger.info("Entity" + entity.getClass() + " saved successfully");
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> get(){
		Session session = this.sessionFactory.getCurrentSession();
		List<User> list = session.createQuery("from User").list();
		return list;
	}

	@Override
	public User getById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		User entity = (User) session.load(User.class, new Integer(id));
		logger.info(User.class + " loaded successfully. Details="+ entity);
		return entity;
	}

	@Override
	public void remove(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		User entity = (User) session.load(User.class, new Integer(id));
		if(null != entity){
			session.delete(entity);
		}
		logger.info(User.class.getSimpleName() + " deleted successfully. Details="+ entity);
		
	}
	
	@Override 
	public AntiForgery getToken(int id)
	{
		Session session = this.sessionFactory.getCurrentSession();
		
		AntiForgery token = (AntiForgery) session.createQuery("FROM antiforgery WHERE userId = :id")
											     .setParameter("id", id);
		
		return token;
	}
	
	@Override 
	public AntiForgery getToken(String value)
	{
		Session session = this.sessionFactory.getCurrentSession();
		
		AntiForgery token = (AntiForgery) session.createQuery("FROM antiforgery WHERE token = :token")
											     .setParameter("token", value);
		
		return token;
	}

	@Override
	public void addToken(AntiForgery token) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(token);
		logger.info("Entity" + token.getClass() + " saved successfully");
		
	}

	@Override
	public void updateToken(AntiForgery token) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(token);
		logger.info("Entity" + token.getClass() + " saved successfully");
		
	}
	@Override
	public void deleteToken(AntiForgery token) {
		Session session = this.sessionFactory.getCurrentSession();
		AntiForgery entity = (AntiForgery) session.load(AntiForgery.class, (int)token.getId());
		if(null != entity){
			session.delete(entity);
		}
		logger.info(User.class.getSimpleName() + " deleted successfully. Details="+ entity);
	}


}