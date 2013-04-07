package com.example.cs349_a5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class CanvasView extends View {
	public Animation animation = new Animation();
	Handler handler = new Handler();
	
	private Runnable mUpdateTimeTask = new Runnable() {
	   public void run() {
			if (animation.playing) {
				animation.currentFrame++;
				invalidate();
			}
			
			handler.postDelayed(mUpdateTimeTask, 1000 / 24);
	   }
	};

	public CanvasView(Context context) {
		super(context);
		init(null, 0);
	}

	public CanvasView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs, 0);
	}

	public CanvasView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs, defStyle);
	}

	private void init(AttributeSet attrs, int defStyle) {
		// Load attributes
		final TypedArray a = getContext().obtainStyledAttributes(attrs,
				R.styleable.CanvasView, defStyle, 0);

		a.recycle();
		
		handler.postDelayed(mUpdateTimeTask, 1000 / 24);
	}


	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		// TODO: consider storing these as member variables to reduce
		// allocations per draw cycle.
		int paddingLeft = getPaddingLeft();
		int paddingTop = getPaddingTop();
		int paddingRight = getPaddingRight();
		int paddingBottom = getPaddingBottom();

		int contentWidth = getWidth() - paddingLeft - paddingRight;
		int contentHeight = getHeight() - paddingTop - paddingBottom;
		
		if (animation != null) {
			animation.paint(canvas, contentWidth, contentHeight);
		}

	}

	void loadAsset(Activity activity) {
		AnimReader file = null;
		try {
            file = new AnimReader(new BufferedReader(new InputStreamReader(activity.getAssets().open("snowman.anim"))));
            file.serialize(animation);
            file.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
}
