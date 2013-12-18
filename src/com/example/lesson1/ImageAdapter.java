package com.example.lesson1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class ImageAdapter extends BaseAdapter {
	private Context mContext;
	private String url[];
	private DisplayImageOptions options;

	public ImageAdapter(Context c, String[] url) {
		mContext = c;
		this.url = url;
		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.ic_stub)
				.showImageForEmptyUri(R.drawable.ic_empty)
				.showImageOnFail(R.drawable.ic_error).cacheInMemory()
				.cacheOnDisc().displayer(new RoundedBitmapDisplayer(5)).build();
	}

	public int getCount() {
		return url.length;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return position;
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null) { // if it's not recycled, initialize some
									// attributes
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(8, 8, 8, 8);
		} else {
			imageView = (ImageView) convertView;
		}

		ImageLoader.getInstance().displayImage(url[position], imageView, options);
		return imageView;
	}

	// references to our images
//	private Integer[] mThumbIds = { R.drawable.ic_launcher, R.drawable.ic_launcher,
//			R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
//			R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
//			R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
//			R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
//			R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
//			R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
//			R.drawable.ic_launcher, R.drawable.ic_launcher };
}
