package com.bank.rest.service;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;

import com.bank.rest.dao.GenericDao;

public class GenericServiceImpl<T> implements GenericService<T> {

	
	@Autowired
	protected GenericDao dao;
	
	protected final Class genericType;
	
	protected GenericServiceImpl(){
		this.genericType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@Autowired(required = true)
	public void setDao(GenericDao dao){
		this.dao = dao;
	}
	
	@Override
	public void add(T entity) {
		dao.add(entity);
		
	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		dao.update(entity);
		
	}

	@Override
	public List<T> get() {
		// TODO Auto-generated method stub
		return dao.findAll(genericType);
	}

	@Override
	public T getById(int id) {
		// TODO Auto-generated method stub
		return (T) dao.find(genericType, id);
	}

	@Override
	public void remove(int id) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean exists(T entity){
		return dao.exists(entity);
	}

}
