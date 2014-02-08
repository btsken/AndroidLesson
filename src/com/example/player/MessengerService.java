package com.example.player;

import java.lang.ref.WeakReference;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

import com.example.lesson1.R;

public class MessengerService extends Service {
	/** Command to the service to display a message */
	static final int PLAY = 1;
	static final int PAUSE = 2;

	public boolean isPause = false;

	public MediaPlayer mPlayer;

	/**
	 * Handler of incoming messages from clients.
	 */
	static class IncomingHandler extends Handler {
		private final WeakReference<MessengerService> mService;

		IncomingHandler(MessengerService service) {
			mService = new WeakReference<MessengerService>(service);
		}

		@Override
		public void handleMessage(Message msg) {

			final MessengerService service = mService.get();
			if (service != null) {
				switch (msg.what) {
				case PLAY:
					Toast.makeText(service.getApplicationContext(), "start!",
							Toast.LENGTH_SHORT).show();
					service.mPlayer.start();
					service.isPause = false;
					break;
				case PAUSE:
					Toast.makeText(service.getApplicationContext(), "pause!",
							Toast.LENGTH_SHORT).show();
					service.mPlayer.pause();
					service.isPause = true;
					break;
				default:
					Log.e("default", "default");
					super.handleMessage(msg);
				}
			}
		}
	}

	/**
	 * Target we publish for clients to send messages to IncomingHandler.
	 */
	final Messenger mMessenger = new Messenger(new IncomingHandler(this));

	/**
	 * When binding to the service, we return an interface to our messenger for
	 * sending messages to the service.
	 */
	@Override
	public IBinder onBind(Intent intent) {
		Toast.makeText(getApplicationContext(), "binding", Toast.LENGTH_SHORT)
				.show();
		return mMessenger.getBinder();
	}

	@Override
	public void onCreate() {
		Log.e("onCreate", "MusicService onCreate()");
		mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);
	}
}
