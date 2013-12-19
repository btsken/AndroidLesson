package com.example.gallery;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lesson1.ImageUrl;
import com.example.lesson1.R;
import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

public class GalleryActivity extends Activity {

	Gallery gallery;
	ImageView img;
	AdView ad;
	GalleryAdapter imageAdapter;

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_gallery);
		init();
		findViews();
		setViews();

		gallery.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				loadImg(position);
			}
		});
	}

	private void loadImg(int position) {
		ImageLoader.getInstance().loadImage(ImageUrl.IMAGES[position],
				new SimpleImageLoadingListener() {
					public void onLoadingComplete(String imageUri,
							android.view.View view,
							android.graphics.Bitmap loadedImage) {
						img.setImageBitmap(loadedImage);
					};

					public void onLoadingFailed(
							String imageUri,
							android.view.View view,
							com.nostra13.universalimageloader.core.assist.FailReason failReason) {
						Toast.makeText(GalleryActivity.this, "download error",
								Toast.LENGTH_LONG).show();
					};

					@Override
					public void onLoadingStarted(String imageUri, View view) {

					}

					@Override
					public void onLoadingCancelled(String imageUri, View view) {
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
		imageAdapter.setmImageIds(ImageUrl.IMAGES);
		gallery.setAdapter(imageAdapter);
	}

	private void init() {
		imageAdapter = new GalleryAdapter(this);
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				this).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO).build();
		ImageLoader.getInstance().init(config);
	}
}
