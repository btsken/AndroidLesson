package com.example.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.lesson1.R;

public class SimpleAdapterActivity extends Activity {
	private ListView listView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		listView = (ListView)findViewById(R.id.listView1);
		SimpleAdapter adapter = new SimpleAdapter(this, getData(),
				R.layout.simple_adapter, new String[] { "title", "img" },
				new int[] { R.id.title, R.id.img });
		listView.setAdapter(adapter);
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
