package com.merchant.rest.dao;

import java.math.BigDecimal;

import javax.transaction.Transactional;

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
	

	
}
