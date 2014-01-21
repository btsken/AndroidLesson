package com.example.map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.example.lesson1.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MapActivity extends Activity {

	private GoogleMap map;
	private LocationManager locationManager;
	private String bestProvider = LocationManager.GPS_PROVIDER;
	static final LatLng NKUT = new LatLng(23.979548, 120.696745);

	@Override
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		init();
	}

	@Override
	protected void onResume() {
		check();
		super.onResume();
	}

	private void init() {
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();

		map.setMyLocationEnabled(true);
	}

	public void check() {
		locationManager = (LocationManager) (this
				.getSystemService(Context.LOCATION_SERVICE));
		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
				|| locationManager
						.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			// �p�GGPS�κ����w��}�ҡA�I�slocationServiceInitial()��s��m
			locationServiceInitial();
		} else {
			Toast.makeText(this, "�ж}�ҩw��A��", Toast.LENGTH_LONG).show();
			startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)); // �}�ҳ]�w����
		}
	}

	private void locationServiceInitial() {
		Log.e("locationServiceInitial", "1");
		Criteria criteria = new Criteria(); // ��T���Ѫ̿���з�
		bestProvider = locationManager.getBestProvider(criteria, true); // ��ܺ�ǫ׳̰������Ѫ�
		Location location = locationManager.getLastKnownLocation(bestProvider);

		if (location != null) {
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
					location.getLatitude(), location.getLongitude()), 15.0f));
		} else {
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
					23.97918, 120.69686), 15.0f));
		}
	}

}
