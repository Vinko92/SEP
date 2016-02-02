package sep.pcc.rest.service;

import java.util.List;

import sep.pcc.rest.model.User;

public interface GenericService<T>{

	void add(final T entity);
	void update(final T entity);
	List<T> get();
	T getById(int id);
	void remove(int id);
	boolean exists(T entity);
	
}
