package sep.pcc.rest.service;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.QueryImpl;

import sep.pcc.rest.model.Bank;
import sep.pcc.rest.model.Payment;

public class BankService extends GenericServiceImpl<Bank> {

	public boolean exists(String pan)
	{
		SessionFactory factory = dao.getSessionFactory();
		Session session = factory.openSession();
		Query query = (QueryImpl) session.createQuery("from Bank where pan = :pan").setParameter("pan", pan);
		Bank bank = (Bank) query.uniqueResult();
		session.close();
		return bank != null;
	}
	

	public Bank find(String pan)
	{
		SessionFactory factory = dao.getSessionFactory();
		Session session = factory.openSession();
		Query query = (QueryImpl) session.createQuery("from Bank where pan = :pan").setParameter("pan", pan);
		Bank bank = (Bank) query.uniqueResult();
		session.close();
		return bank;
	}
	
}
