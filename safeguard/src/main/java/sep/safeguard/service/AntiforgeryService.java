package sep.safeguard.service;

import org.hibernate.Query;
import org.hibernate.Session;

import sep.safeguard.model.AntiforgeryToken;

public class AntiforgeryService extends GenericServiceImpl<AntiforgeryToken>{

	public boolean isValid(AntiforgeryToken token)
	{
		Session session = dao.getSessionFactory().openSession();
		Query query = session.createQuery("FROM antiforgery WHERE token = :token AND identifier = :identifier");
		query.setParameter("token", token.getValue());
		query.setParameter("identifier", token.getMerchantIdentifier());
		return query.uniqueResult() != null;
	}
	
}
