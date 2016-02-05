package sep.safeguard.dao;

import java.util.List;

import javax.annotation.Resource;

import javassist.bytecode.SignatureAttribute.TypeParameter;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


@Repository("pcc")
public class GenericDaoImpl implements GenericDao {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired(required = true)
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	
	public static final Logger logger = Logger.getLogger(GenericDaoImpl.class);
			
	
	public <T> T find(final Class<T> type, final int id) {
		Session session = sessionFactory.openSession();	
		T entity = (T) session.load(type, new Integer(id));
		logger.info(type + " loaded successfully. Details="+ entity);
		session.close();
		return entity;
	}

	public <T> List<T> findAll(Class<T> type) {
		final Session session = sessionFactory.openSession();	
		final Criteria criteria = session.createCriteria(type);
		List<T> list = criteria.list();
		session.close();
		return list;
	}

	public <T> void add(T entity) {
		Session session = sessionFactory.openSession();
		session.save(entity);
		session.close();
		
	}

	public <T> void update(T entity) {
		Session session = sessionFactory.openSession();
		session.update(entity);
		session.close();
		
	}

	public <T> void remove(T entity) {
		Session session = sessionFactory.openSession();
		session.delete(entity);
		session.close();
		
	}

	public  void remove(int id) {
		// TODO Auto-generated method stub
		
		
	}

	public <T> boolean exists(T entity) {
		Session session = sessionFactory.openSession();
		boolean result = session.contains(entity);
		session.close();
		return result;
	}
	
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

}
