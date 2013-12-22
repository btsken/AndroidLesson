package com.example.lesson1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gallery.GalleryActivity;
import com.example.list.ArrayAdapterActivity;
import com.example.list.BaseAdapterActivity;
import com.example.list.SimpleAdapterActivity;
import com.example.sqlitehelper.BmiDbHelper;
import com.example.web.DownloadWebPicture;

public class MainActivity extends Activity {
	private EditText heightET;
	private EditText weightET;
	private Button submitBTN;
	private Button intentBTN;
	private Button folderBTN;
	private Button getFileBTN;
	private TextView bmiTV;
	private Bmi bmi;
	private String s = "";
	private GestureDetector gesture;
	private Context context;
	private ProgressDialog myDialog;
	private ImageView imageView;
	private DownloadWebPicture loadPic;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		findViews();
		setViews();
		
	}

	private void setViews() {
		submitBTN.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				DecimalFormat nf = new DecimalFormat("0.00");
				bmi.height = Double.valueOf(heightET.getText().toString()) / 100;
				bmi.weight = Double.valueOf(weightET.getText().toString());

				s += "BMI: " + nf.format(bmi.caculaterBmi()) + "\n";
				bmiTV.setText(s);
			}
		});
		intentBTN.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, Activity2.class);
				// 砞﹚肚癳把计
				Bundle bundle = new Bundle();
				bundle.putString("bmi", bmiTV.getText().toString());
				intent.putExtras(bundle); // 盢把计intent

				startActivity(intent); // ㊣page2璶―肚
			}
		});
		folderBTN.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				String[] fileNames = { "hello_file", "34", "fff" };
				String string = "hello world!";
				try {
					for (String fileName : fileNames) {
						FileOutputStream fos = openFileOutput(fileName,
								Context.MODE_PRIVATE);
						fos.write(string.getBytes());
						fos.close();
					}
				} catch (IOException e) {
					Log.e("panel", "IOEception", e);
				}
			}
		});
		getFileBTN.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				s = "";
				for (String fileName : fileList()) {
					s += fileName + "\n";
					bmiTV.setText(s);
				}
			}
		});
	}

	private void findViews() {
		heightET = (EditText) findViewById(R.id.heightET);
		weightET = (EditText) findViewById(R.id.weightET);
		submitBTN = (Button) findViewById(R.id.submitBTN);
		bmiTV = (TextView) findViewById(R.id.bmiTV);
		intentBTN = (Button) findViewById(R.id.intentBTN);
		folderBTN = (Button) findViewById(R.id.folderBTN);
		getFileBTN = (Button) findViewById(R.id.getFileBTN);
		imageView = (ImageView) findViewById(R.id.imageView1);
	}

	private void init() {
		context = this;
		bmi = new Bmi();
		IntentFilter ifilter = new IntentFilter();
		ifilter.addAction(Intent.ACTION_NEW_OUTGOING_CALL); // if make a phone
															// call
		ifilter.addAction(Intent.ACTION_SCREEN_OFF); // if screen turn off
		ifilter.addAction(Intent.ACTION_SCREEN_ON); // if screen turn on


		gesture = new GestureDetector(this, gestureListener);
		loadPic = new DownloadWebPicture();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the mBuenu; this adds items to the action bar if it is
		// present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return gesture.onTouchEvent(event);
	}

	OnGestureListener gestureListener = new OnGestureListener() {

		@Override
		public boolean onDown(MotionEvent arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {

			if (e1.getX() - e2.getX() > 120) { // オ
				Toast.makeText(context, "オ菲", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(context, BaseAdapterActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left); 
			} else if (e1.getX() - e2.getX() < -120) { // 
				Toast.makeText(context, "菲", Toast.LENGTH_LONG).show();
			} else {
				return false;
			}

			return true;
		}

		@Override
		public void onLongPress(MotionEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
				float arg3) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void onShowPress(MotionEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean onSingleTapUp(MotionEvent arg0) {
			// TODO Auto-generated method stub
			return false;
		}
	};

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
		switch (item.getItemId()) {

		case R.id.action_settings:
			intent = new Intent(this, Activity2.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		case R.id.dialog:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("About");
			builder.setMessage("Author: btsken");
			builder.show();
			return true;
		case R.id.gridView:
			intent = new Intent(this, HelloGridView.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		case R.id.sqlite:
			BmiDbHelper BmiDbHelper = new BmiDbHelper(this);
			BmiDbHelper.create(bmi);
			// bmi.value = 100;
			// BmiDbHelper.update(1, bmi);
			// BmiDbHelper.read(1);
			// BmiDbHelper.delete(2);
			// BmiDbHelper.readAll();
			return true;
		case R.id.gallery:
			intent = new Intent(this, GalleryActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		case R.id.arrayAdapter:
			intent = new Intent(this, ArrayAdapterActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		case R.id.simpleAdapter:
			intent = new Intent(this, SimpleAdapterActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		case R.id.baseAdapter:
			intent = new Intent(this, BaseAdapterActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		case R.id.thread:
			myDialog = ProgressDialog.show(MainActivity.this, "Please Wait",
					"Connecting...");

			final String url = "http://uploadingit.com/file/lltpirkd9pk3jbuw/raccoon.png";
			loadPic.handleWebPic(url, mHandler);
			
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private static class MyHandler extends Handler {
		private final WeakReference<MainActivity> mActivity;

		public MyHandler(MainActivity activity) {
			mActivity = new WeakReference<MainActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			MainActivity activity = mActivity.get();
			if (activity != null) {
				switch (msg.what) {
				case 1:
					activity.myDialog.dismiss();
					activity.imageView
							.setImageBitmap(activity.loadPic.getImg());

					break;
				}
			}

		}
	}

	private final MyHandler mHandler = new MyHandler(this);
}
