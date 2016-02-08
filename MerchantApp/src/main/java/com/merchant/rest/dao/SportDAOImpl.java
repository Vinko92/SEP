package com.merchant.rest.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.merchant.rest.model.Sport;

@Repository("sportDAO")
public class SportDAOImpl implements SportDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired(required = true)
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Sport getSportByName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("from Sport where name=:name").setString("name", name);
		Sport s = (Sport) q.uniqueResult();
		return s;
	}

}
