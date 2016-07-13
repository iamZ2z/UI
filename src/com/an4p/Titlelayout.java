package com.an4p;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

public class Titlelayout extends LinearLayout {
	public Titlelayout(Context context,AttributeSet attrs) {
		super(context,attrs);
		LayoutInflater.from(context).inflate(R.layout.title,this);
		Button back=(Button) findViewById(R.id.back);
		Button edit=(Button) findViewById(R.id.edit);
		back.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				((Activity)getContext()).finish();
			}
		});
		edit.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				Intent intent=new Intent("com.SEC");
				((Activity)getContext()).startActivity(intent);
			}	
		});
	}
}