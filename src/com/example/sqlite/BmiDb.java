package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BmiDb extends SQLiteOpenHelper {
	private static volatile BmiDb bmiDb = null;
	private final String TABLE_NAME = "bmi";
	private SQLiteDatabase db;

	private BmiDb(Context context) {
		super(context, SqliteConstant.DB_NAME, null,
				SqliteConstant.DATABASE_VERSION);
		db = this.getWritableDatabase();
	}

	public static BmiDb getInstance(Context context) {
		if (bmiDb == null) {
			synchronized (BmiDb.class) {
				if (bmiDb == null) {
					bmiDb = new BmiDb(context);
				}
			}
		}
		return bmiDb;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String DATABASE_CREATE_TABLE = "create table IF NOT EXISTS "
				+ TABLE_NAME + " (" + SqliteConstant.BMI_COLUMNS[0]
				+ " INTEGER PRIMARY KEY, " + SqliteConstant.BMI_COLUMNS[1]
				+ " REAL);";
		Log.d("DATABASE_CREATE_TABLE", DATABASE_CREATE_TABLE);
		db.execSQL(DATABASE_CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS config"); // 刪除舊有的資料表
		onCreate(db);
	}

	public boolean create(String value) {
		ContentValues args = new ContentValues();
		args.put("value", value);

		return db.insert(TABLE_NAME, null, args) != -1;
	}

	public Cursor readAll() {
		return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
	}

	public Cursor read(int id) {
		Cursor cursor = db.query(true, TABLE_NAME, // 資料表名稱
				SqliteConstant.BMI_COLUMNS, // 欄位名稱
				"_ID=" + id, // WHERE
				null, // WHERE 的參數
				null, // GROUP BY
				null, // HAVING
				null, // ORDOR BY
				null // 限制回傳的rows數量
				);

		// 注意：不寫會出錯
		if (cursor != null) {
			cursor.moveToFirst(); // 將指標移到第一筆資料
		}
		return cursor;
	}

	public boolean update(int id, double value) {
		ContentValues args = new ContentValues();
		args.put("value", value);

		return db.update(TABLE_NAME, // 資料表名稱
				args, // VALUE
				"_ID=" + id, // WHERE
				null // WHERE的參數
				) == 1;
	}

	public boolean delete(int id) {
		return db.delete(TABLE_NAME, // 資料表名稱
				"_ID=" + id, // WHERE
				null // WHERE的參數
				) == 1;
	}
}
