package com.example.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;


public abstract class ConcurrentIntentService extends Service {
	protected abstract void onHandleIntent(Intent intent);

	private boolean m_bRedelivery;
	private Map<Intent, MyAsyncTask> m_mapIntent2AsyncTask;

	public ConcurrentIntentService() {
		m_mapIntent2AsyncTask = new ConcurrentHashMap<Intent, MyAsyncTask>();
	}

	public void setIntentRedelivery(boolean enabled) {
		m_bRedelivery = enabled;
	}

	public void cancel() {
		for (MyAsyncTask task : m_mapIntent2AsyncTask.values()) {
			task.cancel(true);
		}
		m_mapIntent2AsyncTask.clear();
		stopSelf();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (!m_mapIntent2AsyncTask.containsKey(intent)) {
			MyAsyncTask task = new MyAsyncTask();
			m_mapIntent2AsyncTask.put(intent, task);
			task.execute(intent);
		}
		
		return m_bRedelivery ? START_REDELIVER_INTENT : START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	// ///////////////////////////////////////////////////////////
	private class MyAsyncTask extends AsyncTask<Intent, Void, Void> {
		protected Void doInBackground(Intent... its) {
			final int nCount = its.length;
			for (int i = 0; i < nCount; i++) {
				Intent it = its[i];

				m_mapIntent2AsyncTask.remove(it);
				onHandleIntent(it);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (m_mapIntent2AsyncTask.isEmpty()) {
				stopSelf();
			}
		}

		@Override
		protected void onCancelled() {
			if (m_mapIntent2AsyncTask.isEmpty()) {
				stopSelf();
			}
		}
	}
}
