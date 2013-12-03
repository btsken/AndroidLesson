package com.example.sqlitehelper;

import java.util.List;

import android.content.Context;

import com.example.sqlite.BmiDb;
import com.example.sqlite.ISqlite;

public class BmiDbHelper implements ISqlite{
	private BmiDb bmidb = null;
	
	public BmiDbHelper(Context context) {
		bmidb = BmiDb.getInstance(context);
	}

	@Override
	public boolean create(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object read(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(int id, Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
