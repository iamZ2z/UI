package com.an4p;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class FouActivity extends Activity {
	private List<Fruit> flist = new ArrayList<Fruit>();
	int[] imageid = new int[] { R.drawable.panda, R.drawable.panda2 };
	int curimage = 0;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置窗口特征：启动显示进度的进度条
		requestWindowFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.four);
		Button bn1 = (Button) findViewById(R.id.pb1);
		Button bn2 = (Button) findViewById(R.id.pb2);
		Button bn3 = (Button) findViewById(R.id.pb3);

		bn1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// 显示带进度的进度条
				setProgressBarVisibility(true);
				setProgress(4500);
			}
		});
		bn2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				setProgressBarVisibility(false);
			}
		});
		bn3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(FouActivity.this, FivActivity.class);
				startActivity(intent);
				// 设置切换动画，从右边进入，左边退出
				overridePendingTransition(R.anim.in_right, R.anim.out_left);
			}
		});

		final ImageView image = (ImageView) findViewById(R.id.panda);
		final Handler myhandle = new Handler() {
			public void handleMessage(Message msg) {
				if (msg.what == 123) {
					image.setImageResource(imageid[curimage++ % imageid.length]);
				}
			}
		};
		new Timer().schedule(new TimerTask() {
			public void run() {
				myhandle.sendEmptyMessage(123);
			}
		}, 0, 1200);

		final TextView result = (TextView) findViewById(R.id.text);
		final TextView change = (TextView) super.findViewById(R.id.change);
		SeekBar seekBar = (SeekBar) findViewById(R.id.ic_launcher);
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				image.setAlpha(progress);
				result.setText("当前值:" + progress);
			}

			public void onStartTrackingTouch(SeekBar bar) {
				change.setText("正在调节");
			}

			public void onStopTrackingTouch(SeekBar bar) {
				change.setText("停止调节");
				// Toast.makeText(An43Activity.this, "停止调节",
				// Toast.LENGTH_SHORT).show();
			}
		});

		/*
		 * ListView peo=(ListView)findViewById(R.id.peo); String[]
		 * arr={"孙悟空","牛魔王"}; ArrayAdapter<String> arrayAdapter=new
		 * ArrayAdapter<String>( this,android.R.layout.simple_list_item_1,arr);
		 * peo.setAdapter(arrayAdapter);
		 */

		initFruits();
		FruitAdapter ada = new FruitAdapter(FouActivity.this, R.layout.item,
				flist);
		ListView list = (ListView) findViewById(R.id.list);
		list.setAdapter(ada);
	}

	private void initFruits() {
		Fruit clock = new Fruit("Clock", R.drawable.clock);
		flist.add(clock);
		Fruit update = new Fruit("update", R.drawable.update);
		flist.add(update);
		Fruit file = new Fruit("file", R.drawable.file);
		flist.add(file);
		Fruit weather = new Fruit("weather", R.drawable.weather);
		flist.add(weather);
		Fruit email = new Fruit("email", R.drawable.email);
		flist.add(email);
		Fruit maps = new Fruit("maps", R.drawable.maps);
		flist.add(maps);
		Fruit download = new Fruit("download", R.drawable.download);
		flist.add(download);
		Fruit note = new Fruit("note", R.drawable.note);
		flist.add(note);
		Fruit music = new Fruit("music", R.drawable.music);
		flist.add(music);
	}
}

class Fruit {
	private String name;
	private int ima;

	public Fruit(String name, int ima) {
		this.name = name;
		this.ima = ima;
	}

	public String getName() {
		return name;
	}

	public int getIma() {
		return ima;
	}
}

class FruitAdapter extends ArrayAdapter<Fruit> {
	private int resource;

	public FruitAdapter(Context context, int textViewResourceId,
			List<Fruit> objects) {
		super(context, textViewResourceId, objects);
		resource = textViewResourceId;
	}

	public View getView(int position, View ConvertView, ViewGroup parent) {
		Fruit fruit = getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(resource, null);
		ImageView fima = (ImageView) view.findViewById(R.id.fr_ima);
		TextView fname = (TextView) view.findViewById(R.id.fr_name);
		fima.setImageResource(fruit.getIma());
		fname.setText(fruit.getName());
		return view;
	}
}