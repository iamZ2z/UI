package com.an4p;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class SecActivity extends Activity {
	private static final int NOTIFICATION_ID = 1;
	private static final String MY_ACTION = "com.android.notification.MY_ACTION";  

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sec);
		
		Button btn2=(Button)this.findViewById(R.id.btn2);
		TextView tv=(TextView)this.findViewById(R.id.tv1);
		ToggleButton toggle=(ToggleButton) super.findViewById(R.id.toggle);
		final LinearLayout test=(LinearLayout) super.findViewById(R.id.test);
		Button no=(Button) findViewById(R.id.no);
		Button no2=(Button) findViewById(R.id.no2);
		
		Intent intent=getIntent();
		tv.setText(tv.getText()+intent.getStringExtra("et1")+"\n颜色:"+intent.getStringExtra("color1")+
				intent.getStringExtra("color2")+intent.getStringExtra("color3"));
		
		toggle.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{		public void onCheckedChanged(CompoundButton arg0,boolean arg1){
					if(arg1)	{test.setOrientation(1);}
					else	{test.setOrientation(0);}
			}
		});
		
		no.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent(SecActivity.this,TrdActivity.class);
				PendingIntent pi=PendingIntent.getActivity(SecActivity.this, 0,intent,0);
				Notification notify=new Notification();
				notify.icon=R.drawable.notify;
				notify.tickerText="启动其它通知";
				notify.when=System.currentTimeMillis();
				notify.defaults=Notification.DEFAULT_ALL;
				/*notify.ledARGB=Color.GREEN;
				notify.ledOffMS=1000;
				notify.ledOnMS=1000;
				notify.flags=Notification.FLAG_SHOW_LIGHTS;*/
				
				notify.setLatestEventInfo(SecActivity.this, "普通通知", "点击查看", pi);
				NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
				notificationManager.notify(NOTIFICATION_ID,notify);
			}
		});
		no2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				notificationManager.cancel(NOTIFICATION_ID);
			}
		});
		
		 btn2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent(SecActivity.this,TrdActivity.class);
				startActivity(intent);
			}
		 });
}}