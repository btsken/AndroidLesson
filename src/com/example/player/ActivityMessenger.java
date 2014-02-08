package com.example.player;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lesson1.R;

public class ActivityMessenger extends Activity {
	/** Messenger for communicating with the service. */
	Messenger mService = null;

	/** Flag indicating whether we have called bind on the service. */
	boolean mBound;

	Button imageButtonPlay;
	
	public boolean isPlaying = false;
	
	/**
	 * Class for interacting with the main interface of the service.
	 */
	private ServiceConnection mConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder service) {
			// This is called when the connection with the service has been
			// established, giving us the object we can use to
			// interact with the service. We are communicating with the
			// service using a Messenger, so here we get a client-side
			// representation of that from the raw IBinder object.
			mService = new Messenger(service);
			mBound = true;
		}

		public void onServiceDisconnected(ComponentName className) {
			// This is called when the connection with the service has been
			// unexpectedly disconnected -- that is, its process crashed.
			mService = null;
			mBound = false;
		}
	};

	public void play() {
		if (!mBound) {
			Log.e("play", "unbind");
			return;
		}
		// Create and send a message to the service, using a supported 'what'
		// value
		Message msg = Message.obtain(null, MessengerService.PLAY, 0, 0);
		try {
			mService.send(msg);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void pause() {
		if (!mBound) {
			Log.e("pause", "unbind");
			return;
		}
		// Create and send a message to the service, using a supported 'what'
		// value
		Message msg = Message.obtain(null, MessengerService.PAUSE, 0, 0);
		try {
			mService.send(msg);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_service);
		findViews();
		
		imageButtonPlay.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.e("onClick", "imageButtonPlay -> onClick");

				if (!isPlaying) {
					play();
					imageButtonPlay.setText(R.string.pause);
					isPlaying = true;
				} else {
					pause();
					imageButtonPlay.setText(R.string.play);
					isPlaying = false;
				}
			}
		});
	}
	
	private void findViews() {
		imageButtonPlay = (Button) findViewById(R.id.button1);
	}

	@Override
	protected void onStart() {
		super.onStart();
		// Bind to the service
		bindService(new Intent(this, MessengerService.class), mConnection,
				Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// Unbind from the service
		if (mBound) {
			unbindService(mConnection);
			mBound = false;
		}
		super.onDestroy();
	}
}
