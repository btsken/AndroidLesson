package com.example.gallery;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lesson1.ImageUrl;
import com.example.lesson1.R;
import com.example.service.DownLoadService;
import com.example.service.NewService;
import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

public class GalleryActivity extends Activity {

	private Gallery gallery;
	private ImageView img;
//	private TextView hiddenTv;
	private AdView ad;
	private GalleryAdapter imageAdapter;
	private String imageUrl;
	private int imagePosition;

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
//				hiddenTv.setText(String.valueOf(ImageUrl.IMAGES[position]));
				imageUrl = ImageUrl.IMAGES[position];
				imagePosition = position;
			}
		});
	}

	private void loadImg(int position) {
		ImageLoader.getInstance().loadImage(ImageUrl.IMAGES[position],
				new SimpleImageLoadingListener() {
					public void onLoadingComplete(String imageUri, View view,
							Bitmap loadedImage) {
						img.setImageBitmap(loadedImage);
					};

					public void onLoadingFailed(String imageUri, View view,
							FailReason failReason) {
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
//		hiddenTv = (TextView) findViewById(R.id.url);
	}

	private void setViews() {
		ad.loadAd(new AdRequest());
		imageAdapter.setmImageIds(ImageUrl.IMAGES);
		gallery.setAdapter(imageAdapter);
		img.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				Intent serviceIntent = new Intent(GalleryActivity.this,
						NewService.class);
				serviceIntent.putExtra("url", imageUrl);
				serviceIntent.putExtra("notifyId", imagePosition);
				startService(serviceIntent);
				return false;
			}
		});
	}

	private void init() {
		imageAdapter = new GalleryAdapter(this);
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				this).threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO).build();
		ImageLoader.getInstance().init(config);
		
		imageUrl = "";
		imagePosition = 0;
	}
}
