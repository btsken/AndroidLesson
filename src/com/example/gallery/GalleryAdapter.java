package com.example.gallery;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.example.lesson1.R;

public class GalleryAdapter extends BaseAdapter {
	private Context mContext;
	private Integer[] mImageIds;
	private final int mGalleryItemBackground;
	private final float mDensity;
	private static final int ITEM_WIDTH = 300;
    private static final int ITEM_HEIGHT = 200;

	public GalleryAdapter(Context c) {
		mContext = c;
		// See res/values/attrs.xml for the <declare-styleable> that defines
		// Gallery1.
		TypedArray a = mContext.obtainStyledAttributes(R.styleable.Gallery1);
		mGalleryItemBackground = a.getResourceId(
				R.styleable.Gallery1_android_galleryItemBackground, 0);
		a.recycle();

		mDensity = c.getResources().getDisplayMetrics().density;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
        if (convertView == null) {
            convertView = new ImageView(mContext);

            imageView = (ImageView) convertView;
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setLayoutParams(new Gallery.LayoutParams(
                    (int) (ITEM_WIDTH * mDensity + 0.5f),
                    (int) (ITEM_HEIGHT * mDensity + 0.5f)));
        
            // The preferred Gallery item background
            imageView.setBackgroundResource(mGalleryItemBackground);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mImageIds[position]);

        return imageView;
	}

	public Integer[] getmImageIds() {
		return mImageIds;
	}

	public void setmImageIds(Integer[] mImageIds) {
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
