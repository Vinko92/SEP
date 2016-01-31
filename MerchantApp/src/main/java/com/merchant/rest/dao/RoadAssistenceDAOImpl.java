package com.merchant.rest.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.merchant.rest.model.RoadAssistence;

@Repository("roadAssistenceDAO")
public class RoadAssistenceDAOImpl implements RoadAssistenceDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired(required = true)
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoadAssistence> getAllRoadAssistence() {
		Session session = this.sessionFactory.getCurrentSession();
		List<RoadAssistence> list = session.createQuery("from RoadAssistence").list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoadAssistence> findByName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("from RoadAssistence where name=:name").setString("name", name);
		return q.list();
	}

	@Override
	public void addRoadAssistence(RoadAssistence ra) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(ra);
	}

	@Override
	public RoadAssistence getByName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("from RoadAssistence where name=:name").setString("name", name);
		RoadAssistence ra = (RoadAssistence) q.uniqueResult();
		return ra;
	}
	
	
}
