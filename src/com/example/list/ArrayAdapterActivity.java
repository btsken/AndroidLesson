package com.example.list;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lesson1.R;

public class ArrayAdapterActivity extends Activity {
	private ListView listView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		listView = (ListView)findViewById(R.id.listView1);
		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, getData()));
	}

	private List<String> getData() {

		List<String> data = new ArrayList<String>();
		data.add("測試資料1");
		data.add("測試資料2");
		data.add("測試資料3");
		data.add("測試資料4");

		return data;
	}
}
