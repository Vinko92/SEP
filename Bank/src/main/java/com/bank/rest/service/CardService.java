package com.bank.rest.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.bank.rest.model.Card;


public class CardService extends GenericServiceImpl<Card>{

	public Card find(String securityCode, Date validTo, String cardHolder)
	{
		Session session = dao.getSessionFactory().openSession();
		Query query = session.createQuery("FROM Card WHERE "
										+ "securityCode = :securityCode AND "
										+ "expirationDate = :expirationDate AND "
										+ "cardHolder = :cardHolder");
		query.setParameter("securityCode", securityCode);
		query.setParameter("expirationDate", validTo);
		query.setParameter("cardHolder", cardHolder);
		System.out.println(query.toString());
		return (Card) query.uniqueResult();
	}
	
}
