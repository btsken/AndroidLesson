package com.example.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import com.example.lesson1.R;

public class SimpleAdapterActivity extends ListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		SimpleAdapter adapter = new SimpleAdapter(this, getData(),
				R.layout.simple_adapter, new String[] { "title", "img" },
				new int[] { R.id.title, R.id.img });
		setListAdapter(adapter);
	}

	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "C");
		map.put("img", R.drawable.ic_launcher);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "A");
		map.put("img", R.drawable.ic_launcher);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "B");
		map.put("img", R.drawable.ic_launcher);
		list.add(map);
		return list;
	}

}
