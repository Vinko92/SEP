package com.merchant.rest.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.merchant.rest.model.Region;

@Repository("regionDAO")
public class RegionDAOImpl implements RegionDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired(required = true)
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Region getRegionByName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("from Region where name=:name").setString("name", name);
		Region r = (Region) q.uniqueResult();
		return r;
	}

}
