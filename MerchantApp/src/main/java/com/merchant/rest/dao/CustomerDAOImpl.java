package com.merchant.rest.dao;

import java.util.List;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.merchant.rest.model.Customer;

@Repository("customerDAO")
public class CustomerDAOImpl implements CustomerDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired(required = true)
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	
	@Override
	public void addCustomer(Customer c) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(c);
	}

	@Override
	public void updateCustomer(Customer c) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(c);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> listCustomers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Customer> customersList = session.createQuery("from Customer").list();
		return customersList;
	}

	@Override
	public Customer getCustomerById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Customer c = (Customer) session.load(Customer.class, new Integer(id));
		return c;
	}

	@Override
	public void removeCustomer(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Customer c = (Customer) session.load(Customer.class, new Integer(id));
		if(null != c){
			session.delete(c);
		}
	}


	@Override
	public Customer getCustomerByName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("from Customer where name=:name").setString("name", name);
		Customer c= (Customer) q.uniqueResult();
		return c;
	}


	@Override
	public int getCustomerIdByCustomerName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("from Customer where name=:name").setString("name", name);
		Customer c = (Customer) q.uniqueResult();
		return c.getId();
	}


	@Override
	public void setCustomerOwnerByJmbg(String jmbg) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("from Customer where jmbg=:jmbg").setString("jmbg", jmbg);
		Customer c = (Customer) q.uniqueResult();
		c.setOwner(true);
		session.persist(c);
	}

	
}
