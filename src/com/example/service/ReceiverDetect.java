package com.example.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ReceiverDetect extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		String strAction = intent.getAction();
		Log.e("kencyang", "action:" + strAction);

		// if you make a phone call...
		if (strAction.equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
			String number = getResultData();
			Log.e("kencyang", "number:" + number);
		}
	}
}