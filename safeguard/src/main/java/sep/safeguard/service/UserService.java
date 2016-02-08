package sep.safeguard.service;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import sep.safeguard.model.User;

@Service
public class UserService extends GenericServiceImpl<User> {
	
	public User find(String username, String password)
	{
		Session session = dao.getSessionFactory().openSession();
		Query query = session.createQuery("FROM User WHERE username = :username AND password = :password");
		query.setParameter("username", username);
		query.setParameter("password", password);
		return (User) query.uniqueResult();
		
	}

}
