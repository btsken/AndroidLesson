package com.example.service;

import java.util.Date;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class ServicesEx extends Service {
	private Handler handler = new Handler();

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.e("servive", "create");
	}

	@Override
	public void onDestroy() {
		handler.removeCallbacks(showTime);
		super.onDestroy();
	}

	private Runnable showTime = new Runnable() {
		public void run() {
			// log目前時間
			Log.e("time:", new Date().toString());
			handler.postDelayed(this, 1000);
		}
	};
}
