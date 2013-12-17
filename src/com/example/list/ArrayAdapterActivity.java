package com.example.list;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lesson1.Bmi;
import com.example.lesson1.R;
import com.example.sqlitehelper.BmiDbHelper;

public class ArrayAdapterActivity extends Activity {
	private ListView listView;
	private BmiDbHelper bmiDbHelper;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		init();
		findViews();
		setViews();
	}

	private void init() {
		bmiDbHelper = new BmiDbHelper(this);
	}

	private void findViews() {
		listView = (ListView) findViewById(R.id.listView1);
	}

	private void setViews() {
		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1,
				getData(bmiDbHelper.readAll())));
	}

	private List<String> getData(List<Bmi> bmiList) {
		List<String> data = new ArrayList<String>();
		for (Bmi bmi : bmiList) {
			data.add(String.valueOf(bmi.value));
		}

		return data;
	}
}
