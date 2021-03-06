package com.example.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.lesson1.R;
import com.example.web.DownloadWebPicture;

public class DownLoadService extends Service {

	private String bitmapUrl;
	private DownloadWebPicture loadPic;
	private NotificationManager manager;
	private NotificationCompat.Builder mBuilder;
	private static final int NOTIFICATION_ID = 1;

	@Override
	public void onCreate() {
		super.onCreate();
		loadPic = new DownloadWebPicture();
		init();
	}

	private void init() {
		manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Bundle extras = intent.getExtras();
		if (extras == null) {
			Log.e("Service", "null");
		} else {
			Log.e("Service", "no null");
			bitmapUrl = (String) extras.get("url");
			createNotification("Downloading...");
			loadPic.handleWebPic(bitmapUrl, mHandler);
		}
		return super.onStartCommand(intent, flags, startId);
	}

	private void createNotification(String text) {
		mBuilder = new NotificationCompat.Builder(this)
				.setSmallIcon(R.drawable.ic_launcher).setContentTitle("MyApp")
				.setContentText(text).setTicker(text);
		manager.notify(NOTIFICATION_ID, mBuilder.build());
	}

	public void updateNotificationTicker(String newTickerText) {
		Log.d("", "updateNotificationTicker");

		if (mBuilder != null && manager != null) {
			mBuilder.setContentText(newTickerText);
			mBuilder.setTicker(newTickerText);
			manager.notify(NOTIFICATION_ID, mBuilder.build());
		}
	}

	public void saveBitmap(Bitmap bitmap) {

		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 40, bytes);

		File wallpaperDirectory = new File(
				Environment.getExternalStorageDirectory() + File.separator
						+ "/MyApp/");
		try {
			wallpaperDirectory.mkdirs();
			File outputFile = new File(wallpaperDirectory, getTimeName()
					+ ".jpg");
			outputFile.createNewFile();
			FileOutputStream fo = new FileOutputStream(outputFile);
			fo.write(bytes.toByteArray());
			fo.close();
			Log.e("saveBitmap", "done");
			updateNotificationTicker("Download Done!");
		} catch (IOException e) {
			Log.e("saveBitmap", e.toString());
			e.printStackTrace();
		}
	}

	public String getTimeName() {
		Date _date = new Date(System.currentTimeMillis());
		SimpleDateFormat _sdf = new SimpleDateFormat("yyyyMMddHHmmss",
				Locale.TAIWAN);
		Random _random = new Random();
		return _sdf.format(_date) + _random.nextInt(999);
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.e("onBind", "onBind");
		return null;
	}

	private static class MyHandler extends Handler {
		private final WeakReference<DownLoadService> mActivity;

		public MyHandler(DownLoadService activity) {
			mActivity = new WeakReference<DownLoadService>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			DownLoadService activity = mActivity.get();
			if (activity != null) {
				switch (msg.what) {
				case DownloadWebPicture.DOWNLOAD_SUCCESS:
					activity.saveBitmap(activity.loadPic.getImg());
					break;
				case DownloadWebPicture.DOWNLOAD_FAIL:
					activity.updateNotificationTicker("Download Fail!");
					break;
				}
			}

		}
	}

	private final MyHandler mHandler = new MyHandler(this);
}
