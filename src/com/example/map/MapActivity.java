package com.example.map;

import android.app.Activity;
import android.os.Bundle;

import com.example.lesson1.R;

public class MapActivity extends Activity {
//	static final LatLng NKUT = new LatLng(23.979548, 120.696745);
//	private GoogleMap map;

	@Override
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
//		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
//				.getMap();
//		Marker nkut = map.addMarker(new MarkerOptions().position(NKUT)
//				.title("南開科技大學").snippet("數位生活創意系"));
//
//		// Move the camera instantly to NKUT with a zoom of 16.
//		map.moveCamera(CameraUpdateFactory.newLatLngZoom(NKUT, 16));
	}

}
