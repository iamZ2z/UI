package com.an4p;

import java.util.Calendar;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.DatePicker.OnDateChangedListener;

public class TrdActivity extends Activity{
	String[] books=new String[]{"android讲义","apple手机","阿尔法狗"};
	private Spinner sp=null;
	private ArrayAdapter<CharSequence> ci=null;
	private int year,month,day;
	private Notification notification;
	private NotificationManager mNotification; 
	private static final int ID = 1;
	int[] data=new int[100];
	int hasData=0,status=0;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.trd);
		
		String service = NOTIFICATION_SERVICE;  
		mNotification = (NotificationManager)getSystemService(service); 
		notification = new Notification();  
		long when = System.currentTimeMillis(); 
        mNotification.notify(ID, notification); 
        
		Intent intent2=getIntent();
		Chronometer time=(Chronometer) findViewById(R.id.time);
		ArrayAdapter<String> aa=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,books);
		AutoCompleteTextView actv=(AutoCompleteTextView) this.findViewById(R.id.auto);
		actv.setAdapter(aa);
		sp=(Spinner)this.findViewById(R.id.sp);
		TextView sp2=(TextView)this.findViewById(R.id.sp2);
		DatePicker da=(DatePicker) findViewById(R.id.da);
		
		time.setBase(SystemClock.elapsedRealtime());
		time.start();
		time.setOnChronometerTickListener(new OnChronometerTickListener()
		{
			public void onChronometerTick(Chronometer time){
				if(SystemClock.elapsedRealtime()-time.getBase()>20*1000)
					time.stop();
				}
			});
		
		sp.setPrompt("选择运动");
		ci=ArrayAdapter.createFromResource(this,R.array.yd , android.R.layout.simple_spinner_item);
		sp.setAdapter(ci);
		
		Calendar c=Calendar.getInstance();
		year=c.get(Calendar.YEAR);
		month=c.get(Calendar.MONTH);
		day=c.get(Calendar.DAY_OF_MONTH);
	
		da.init(year,month,day,new OnDateChangedListener(){
			public void onDateChanged(DatePicker arg0,int year,int month,int day){
				TrdActivity.this.year=year;
				TrdActivity.this.month=month;
				TrdActivity.this.day=day;
			}
		});
		
		
		final ProgressBar pgb1=(ProgressBar)findViewById(R.id.bar1);
		//setProgressBarIndeterminateVisibility(true);
		final Handler mHandler=new Handler(){
			public void handleMessage(Message msg){
				if (msg.what == 0x111)  
                    pgb1.setProgress(status);  
			}
		};
		new Thread() {  
            public void run() {  
                while (status < 100) {  
                    // 获取耗时的完成百分比  
                    status = doWork();  
                    Message m = new Message();  
                    m.what = 0x111;  
                    mHandler.sendMessage(m);  
                }  
            }  
        }.start();  
    }   
    //模拟一个耗时的操作  
    private int doWork() {  
        data[hasData++] = (int) (Math.random() * 100);  
        try {  
            Thread.sleep(100);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return hasData;  
    }    
    public void startTitleProgressBar(View v){ 
        Intent intent=new Intent(TrdActivity.this,FouActivity.class);  
        startActivity(intent);  
    }    
} 