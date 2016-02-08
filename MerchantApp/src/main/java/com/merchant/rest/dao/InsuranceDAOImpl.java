package com.merchant.rest.dao;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.merchant.rest.model.Insurance;

@Repository("insuranceDAO")
public class InsuranceDAOImpl implements InsuranceDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired(required = true)
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addInsurance(Insurance insurance) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(insurance);
	}

	@Override
	public Insurance getInsuranceByOwnerName(String ownerName) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("from Insurance where ownerName=:ownerName").setString("ownerName", ownerName);
		Insurance i = (Insurance) q.uniqueResult();
		return i;
	}

	@Override
	public void saveInsurance(Insurance i) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(i);
	}
	

	
}
