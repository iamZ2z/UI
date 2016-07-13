package com.an4p;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.*;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.*;

public class An4pActivity extends Activity {
	private TextView mt;
	private Gallery mg;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        //getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title);
        
        final EditText et1=(EditText)this.findViewById(R.id.et1);
        RadioGroup gender=(RadioGroup)findViewById(R.id.gender);
        final RadioButton male=(RadioButton)findViewById(R.id.male);
        final RadioButton female=(RadioButton)findViewById(R.id.female);
        final CheckBox red=(CheckBox)findViewById(R.id.red);
        final CheckBox blue=(CheckBox)findViewById(R.id.blue);
        final CheckBox green=(CheckBox)findViewById(R.id.green);
        ImageButton btn1=(ImageButton)this.findViewById(R.id.btn1);
     
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(female.getId()==checkedId){
					Toast.makeText(An4pActivity.this,"female",Toast.LENGTH_SHORT).show();
				}else if (male.getId()==checkedId) {
					Toast.makeText(An4pActivity.this,"male",Toast.LENGTH_SHORT).show();
				}
			}
		});
        red.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					System.out.println("red checked ");
				}
				else {
					System.out.println("red unchecked ");
				}
			}
		});
        blue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					System.out.println("blue checked ");
				}
				else {
					System.out.println("blue unchecked ");
				}
			}
		});
        green.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					System.out.println("green checked ");
				}
				else {
					System.out.println("green unchecked ");
				}
			}
		});
        
        btn1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent(An4pActivity.this,SecActivity.class);
				intent.putExtra("et1", et1.getText().toString());
				if(red.isChecked())
					intent.putExtra("color1",red.getText());
				else
					intent.putExtra("color1","");
				if(blue.isChecked())
					intent.putExtra("color2",blue.getText());
				else
					intent.putExtra("color2","");
				if(green.isChecked())
					intent.putExtra("color3",green.getText());
				else
					intent.putExtra("color3","");
				startActivity(intent);
			}
		});
        
        mg=(Gallery) findViewById(R.id.ga);
        mg.setAdapter(new MyImageAdapter(this));
    }
    protected void dialog() {
    	AlertDialog.Builder builder = new Builder(An4pActivity.this);
    	builder.setMessage("确认退出吗？");
    	builder.setTitle("提示");
    	builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int which) {
    			dialog.dismiss();
    			An4pActivity.this.finish();
    		}
    	});
    	builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int which) {
    			dialog.dismiss();
    		}
    	});
    	builder.create().show();
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
    		dialog();
    		}
    	return false;
    }
    
    public class MyImageAdapter extends BaseAdapter{
    	private Context context;
    	private int[] myImageIds={
    			R.drawable.a1,R.drawable.a2,R.drawable.a3,R.drawable.a4,R.drawable.a5};
    	public MyImageAdapter(Context context){
    		super();
    		this.context=context;
    	}
		public int getCount() {
			return myImageIds.length;
		}
		public Object getItem(int position) {
			return position;
		}
		public long getItemId(int position) {
			return position;
		}
		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imageView=new ImageView(context);
			imageView.setImageResource(myImageIds[position]);
			imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
			imageView.setLayoutParams(new Gallery.LayoutParams(200,200));
			return imageView;
		}  	
    }
}