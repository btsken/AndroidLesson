package com.example.sqlite;

import java.util.List;


public interface ISqlite<T> {	
	public boolean create(T o);
	public List<T> readAll();
	public T read(int id);
	public boolean update(int id, T o);
	public boolean delete(int id);
}
