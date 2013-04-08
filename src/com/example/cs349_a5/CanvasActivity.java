package com.example.cs349_a5;

import com.example.cs349_a5.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class CanvasActivity extends Activity {
	CanvasView canvas;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_canvas);

		canvas = ((CanvasView)findViewById(R.id.canvasView1));
		
		ToggleButton me = (ToggleButton)findViewById(R.id.toggleButton1); 
		me.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				canvas.animation.playing = arg1;
				if (arg1) {
					canvas.animation.currentFrame = 0;
				}
				
			}
		});
		
		final Activity activity = this;
		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(activity, SettingsActivity.class);
				activity.startActivity(intent);
			}
		});
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		
		canvas.loadAsset(this);
	}

}
