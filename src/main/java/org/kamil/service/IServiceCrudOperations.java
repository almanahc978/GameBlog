package org.kamil.service;

import java.util.List;

import org.kamil.model.Game;

public interface IServiceCrudOperations<T> {

	T getById(Integer id);
	List<T> getAll();
	List<T> getByName(String name);
	T add(T entity);
	T update(T entity, Integer id);
	void delete(Integer id);
}
