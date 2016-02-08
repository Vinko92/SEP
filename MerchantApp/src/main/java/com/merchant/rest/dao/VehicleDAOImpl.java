package com.merchant.rest.dao;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.merchant.rest.model.Vehicle;

@Repository("vehicleDAO")
public class VehicleDAOImpl implements VehicleDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired(required = true)
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addVehicle(Vehicle v) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(v);
	}

	@Override
	public double getPriceByOwnerJmbg(String ownerJmbg) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("from Vehicle where ownerJmbg=:ownerJmbg").setString("ownerJmbg", ownerJmbg);
		Vehicle v = (Vehicle) q.uniqueResult();
		return v.getPrice();
	}

}
