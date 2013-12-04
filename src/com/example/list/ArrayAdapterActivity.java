package com.example.list;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ArrayAdapterActivity extends Activity {
	private ListView listView;

	// private List<String> data = new ArrayList<String>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		listView = new ListView(this);
		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, getData()));
		setContentView(listView);
	}

	private List<String> getData() {

		List<String> data = new ArrayList<String>();
		data.add("���ո��1");
		data.add("���ո��2");
		data.add("���ո��3");
		data.add("���ո��4");

		return data;
	}
}