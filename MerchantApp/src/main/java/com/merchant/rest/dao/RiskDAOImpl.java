package com.merchant.rest.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.merchant.rest.model.Risk;


@Repository("riskDAO")
public class RiskDAOImpl implements RiskDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired(required = true)
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Risk findRiskByName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("from Risk where name=:name").setString("name", name);
		Risk r = (Risk) q.uniqueResult();
		return r;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Risk> getAllRisks() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Risk> list = session.createQuery("from Risk").list();
		return list;
	}

	@Override
	public Risk findRiskById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Risk r = (Risk) session.load(Risk.class, new Integer(id));
		return r;
	}

}
