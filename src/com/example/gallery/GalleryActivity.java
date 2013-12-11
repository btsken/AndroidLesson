package com.example.gallery;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageView;

import com.example.lesson1.R;
import com.google.ads.AdRequest;
import com.google.ads.AdView;

public class GalleryActivity extends Activity {

	Gallery gallery;
	ImageView img;
	AdView ad;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_gallery);
		findViews();
		setViews();
		GalleryAdapter imageAdapter = new GalleryAdapter(this);
		// 設定圖片來源
		final Integer[] mImageIds = { R.drawable.g1,
				R.drawable.g2, R.drawable.g3,
				R.drawable.g4, R.drawable.g5, R.drawable.g6 };
		// 設定圖片的位置
		imageAdapter.setmImageIds(mImageIds);
		gallery.setAdapter(imageAdapter);
		gallery.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View view,
					int position, long id) {
				// Toast.makeText(MainActivity.this, "您選的是第" + position + "張圖",
				// Toast.LENGTH_LONG).show();
				img.setImageResource(mImageIds[position]);
			}
		});
	}

	private void findViews() {
		gallery = (Gallery) findViewById(R.id.gallery);
		img = (ImageView) findViewById(R.id.imageView1);
		ad = (AdView) findViewById(R.id.adView);
		
	}

	private void setViews() {
		ad.loadAd(new AdRequest());
	}
}
