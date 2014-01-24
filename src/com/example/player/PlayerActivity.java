package com.example.player;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lesson1.R;

public class PlayerActivity extends Activity {

	Button imageButtonPlay;

	IServicePlayer iPlayer;
	boolean isPlaying = false;
	boolean isLoop = false;	
	
	String tag = "PlayerActivity";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_service);

		imageButtonPlay = (Button) findViewById(R.id.button1);

		Intent intent = new Intent(PlayerActivity.this, MusicService.class);
		
		bindService(intent, conn, Context.BIND_AUTO_CREATE);
		startService(intent);

		imageButtonPlay.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.e(tag, "imageButtonPlay -> onClick");

				if (!isPlaying) {
					try {
						iPlayer.play();
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					imageButtonPlay.setText(R.string.pause);
					isPlaying = true;

				} else {
					try {
						iPlayer.pause();
					} catch (RemoteException e) {
						e.printStackTrace();
					}
					imageButtonPlay.setText(R.string.play);
					isPlaying = false;
				}
			}
		});

	}

	private ServiceConnection conn = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder service) {
			Log.e("yao", "ServiceConnection -> onServiceConnected");
			iPlayer = IServicePlayer.Stub.asInterface(service);
		}

		public void onServiceDisconnected(ComponentName className) {
			iPlayer = null;
		};
	};

}