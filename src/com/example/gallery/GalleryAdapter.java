package com.example.gallery;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.example.lesson1.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class GalleryAdapter extends BaseAdapter {
	private Context mContext;
	private String[] mImageIds;
	private final int mGalleryItemBackground;
	private final float mDensity;
	private static final int ITEM_WIDTH = 300;
	private static final int ITEM_HEIGHT = 200;
	private DisplayImageOptions options;

	public GalleryAdapter(Context c) {
		mContext = c;
		// See res/values/attrs.xml for the <declare-styleable> that defines
		// Gallery1.
		TypedArray a = mContext.obtainStyledAttributes(R.styleable.Gallery1);
		mGalleryItemBackground = a.getResourceId(
				R.styleable.Gallery1_android_galleryItemBackground, 0);
		a.recycle();

		mDensity = c.getResources().getDisplayMetrics().density;

		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.ic_stub)
				.showImageForEmptyUri(R.drawable.ic_empty)
				.showImageOnFail(R.drawable.ic_error).cacheInMemory()
				.cacheOnDisc().displayer(new RoundedBitmapDisplayer(5)).build();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null) {
			convertView = new ImageView(mContext);

			imageView = (ImageView) convertView;
			imageView.setScaleType(ImageView.ScaleType.FIT_XY);
			imageView.setLayoutParams(new Gallery.LayoutParams(
					(int) (ITEM_WIDTH * mDensity + 0.5f), (int) (ITEM_HEIGHT
							* mDensity + 0.5f)));

			// The preferred Gallery item background
			imageView.setBackgroundResource(mGalleryItemBackground);
		} else {
			imageView = (ImageView) convertView;
		}

		ImageLoader.getInstance().displayImage(mImageIds[position], imageView, options);
		return imageView;
	}

	public String[] getmImageIds() {
		return mImageIds;
	}

	public void setmImageIds(String[] mImageIds) {
		this.mImageIds = mImageIds;
	}

	public int getCount() {
		return mImageIds.length;
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}
}
