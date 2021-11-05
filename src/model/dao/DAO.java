package model.dao;

import java.util.List;

public interface DAO<T> {
	T getById(int id);
	
	List<T> getAll();
	
	boolean create(T object);

	boolean update(T object);

	boolean delete(T object);
	
}
