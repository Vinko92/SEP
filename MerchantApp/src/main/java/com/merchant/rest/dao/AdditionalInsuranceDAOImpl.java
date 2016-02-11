package com.merchant.rest.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.merchant.rest.model.AdditionalInsurance;

@Repository("additionalInsuranceDAO")
public class AdditionalInsuranceDAOImpl implements AdditionalInsuranceDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired(required = true)
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addAdditionalInsurance(AdditionalInsurance ai) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(ai);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdditionalInsurance> getAllAdditionalInsurance() {
		Session session = this.sessionFactory.getCurrentSession();
		List<AdditionalInsurance> list = session.createQuery("from AdditionalInsurance").list();
		return list;
	}

}
