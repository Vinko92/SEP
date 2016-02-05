package sep.safeguard.dao;

import java.util.List;

import org.hibernate.SessionFactory;

public interface GenericDao {
	
	<T> T find(final Class<T> type, final int id);
	<T> List<T> findAll(final Class<T> type);
	<T> void add(final T entity);
	<T> void update(final T entity);
	<T> void remove(final T entity);
	<T> void remove(int id);
	<T> boolean exists(final T entity);
	SessionFactory getSessionFactory();

}
