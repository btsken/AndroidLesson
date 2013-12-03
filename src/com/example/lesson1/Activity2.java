package com.example.lesson1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Activity2 extends Activity{
	private TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		tv = (TextView) findViewById(R.id.textView1);
		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();	//取得Bundle
		
		if(bundle != null) {
			tv.setText(bundle.getString("bmi"));	//輸出Bundle內容
		}
		
	}
	
}
