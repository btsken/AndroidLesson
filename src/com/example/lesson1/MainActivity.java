package com.example.lesson1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gallery.GalleryActivity;
import com.example.service.ServicesEx;
import com.example.sqlitehelper.BmiDbHelper;

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
				// �]�w�ǰe�Ѽ�
				Bundle bundle = new Bundle();
				bundle.putString("bmi", bmiTV.getText().toString());
				intent.putExtras(bundle); // �N�ѼƩ�Jintent

				startActivity(intent); // �I�spage2�ín�D�^�ǭ�
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

	private void savePreference() {
		SharedPreferences settings = getSharedPreferences("pref_name", 0);
		// ���o�ɦW��pref_name�����n�]�w
		// ���إ߼Ҧ�0=MODE_PRIVATE�G�w�]�Ҧ��A���u��Q�إߪ����ε{���s���]�Ψ㦳�ۦP�Τ�ID�����ε{���^�C
		// MODE_WORLD_READABLE�G���\�䥦���ε{��Ū�����C
		// MODE_WORLD_WRITEABLE�G���\�䥦���ε{���s�����C
		// MODE_MULTI_PROCESS�G���h�ӵ{���@�ΰ��n�]�w�ɡA�L�ר䥦�{�ǬO�_�w�g���J�o�ӳ]�w�A���n�j��i��ק�C

		settings.edit().putString("key_name", "value").commit();
		// edit()�إ�SharedPreferences���s��
		// �z�LputString()�BputInt()�BputBoolean()��Editor��k�[�J���
		// remove("setting_name")�i�H�R���Y�@�ӳ]�w
		// clear()�i�M���������]�w
		// commit()�N�]�w�Ǧ^SharedPreferences����C
	}

	private void findViews() {
		heightET = (EditText) findViewById(R.id.heightET);
		weightET = (EditText) findViewById(R.id.weightET);
		submitBTN = (Button) findViewById(R.id.submitBTN);
		bmiTV = (TextView) findViewById(R.id.bmiTV);
		intentBTN = (Button) findViewById(R.id.intentBTN);
		folderBTN = (Button) findViewById(R.id.folderBTN);
		getFileBTN = (Button) findViewById(R.id.getFileBTN);
	}

	private void init() {
		bmi = new Bmi();
		Intent intent = new Intent(MainActivity.this, ServicesEx.class);
		startService(intent);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the mBuenu; this adds items to the action bar if it is
		// present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

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
			BmiDbHelper.readAll();
			return true;
		case R.id.gallery:
			intent = new Intent(this, GalleryActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
