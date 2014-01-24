package com.example.player;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.lesson1.R;

public class MusicService extends Service {
	
	String tag = "MusicService";

	public MediaPlayer mPlayer;

	public boolean isPause = false;

	IServicePlayer.Stub stub = new IServicePlayer.Stub() {
		
		@Override
		public void play() throws RemoteException {
			mPlayer.start();
		}

		@Override
		public void pause() throws RemoteException {
			mPlayer.pause();
		}

	};

	@Override
	public void onCreate() {
		Log.e(tag, "MusicService onCreate()");
	    
		mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.e(tag, "MusicService onBind()");
		return stub;
	}

}
