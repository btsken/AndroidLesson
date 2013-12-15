package com.example.sqlitehelper;

import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.lesson1.Bmi;
import com.example.sqlite.BmiDb;
import com.example.sqlite.SqliteConstant;

public class BmiDbHelper implements IDbHelper<Bmi> {
	private BmiDb bmidb = null;

	public BmiDbHelper(Context context) {
		bmidb = BmiDb.getInstance(context);
	}

	@Override
	public boolean create(Bmi o) {
		bmidb.create(o.value);
		return false;
	}

	@Override
	public List<Bmi> readAll() {
		Cursor cursor = bmidb.readAll(); // 取得SQLite類別的回傳值:Cursor物件
		int rows_num = cursor.getCount(); // 取得資料表列數

		if (rows_num != 0) {
			cursor.moveToFirst(); // 將指標移至第一筆資料
			for (int i = 0; i < rows_num; i++) {
				Log.e(SqliteConstant.BMI_COLUMNS[0], cursor.getInt(0) + "");
				Log.e(SqliteConstant.BMI_COLUMNS[1], cursor.getDouble(1) + "");
				
				cursor.moveToNext(); // 將指標移至下一筆資料
			}
		}
		cursor.close(); // 關閉Cursor
		return null;
	}

	@Override
	public Bmi read(int id) {
		Cursor cursor = bmidb.read(id); // 取得SQLite類別的回傳值:Cursor物件
		int rows_num = cursor.getCount(); // 取得資料表列數

		if (rows_num != 0) {
			cursor.moveToFirst(); // 將指標移至第一筆資料
			Log.e(SqliteConstant.BMI_COLUMNS[0], cursor.getInt(0) + "");
			Log.e(SqliteConstant.BMI_COLUMNS[1], cursor.getDouble(1) + "");
			cursor.moveToNext(); // 將指標移至下一筆資料
		}
		return null;
	}

	@Override
	public boolean update(int id, Bmi o) {
		bmidb.update(id, o.value);
		return false;
	}

	@Override
	public boolean delete(int id) {
		bmidb.delete(id);
		return false;
	}
}
