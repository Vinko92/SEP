package com.merchant.rest.dao;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.merchant.rest.model.Home;

@Repository("homeDAO")
public class HomeDAOImpl implements HomeDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired(required = true)
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Home findHomeById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Home home = (Home) session.load(Home.class,new Integer(id));
		return home;
	}

	@Override
	public Home findHomeByIdAndPrice(int id, double price) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("from Home where id=:id and price=:price").setInteger("id", id).setDouble("peice", price);
		Home h = (Home) q.uniqueResult();
		return h;
	}

	@Override
	public void addHomeInsurance(Home h) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(h);
	}

	@Override
	public double getPriceByOwnerJmbg(String ownerJmbg) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("from Home where ownerJmbg=:ownerJmbg").setString("ownerJmbg", ownerJmbg);
		Home h = (Home) q.uniqueResult();
		return h.getPrice();
	}

}
