package sep.safeguard.service;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.QueryImpl;
import org.springframework.stereotype.Service;
import sep.safeguard.model.Merchant;

@Service("merchantService")
public class MerchantService extends GenericServiceImpl<Merchant>{
	
	public Merchant getByIdentifier(String identifier)
	{
			SessionFactory factory = dao.getSessionFactory();
			Session session = factory.openSession();
			Query query = (QueryImpl) session.createQuery("from Merchant where identifier = :identifier").
											  setParameter("identifier", identifier);
			Merchant merchant = (Merchant) query.uniqueResult();
			session.close();
			return merchant;
	}
}
