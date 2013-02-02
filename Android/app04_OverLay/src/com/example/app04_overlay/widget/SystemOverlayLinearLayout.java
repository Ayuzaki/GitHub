package com.example.app04_overlay.widget;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class SystemOverlayLinearLayout extends LinearLayout {

	private Context mContext;
	public SystemOverlayLinearLayout(Context context) {
		super(context);
		mContext = context;
	}
	public SystemOverlayLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
	}
	
	public void addWindow() {
		WindowManager.LayoutParams params = new WindowManager.LayoutParams(
				100,
                600,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);
		WindowManager wm = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
		wm.addView(getRootView(), params);
	}
	
	public void removeWindow() {
		WindowManager wm = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
		wm.removeView(getRootView());
	}
	
	@Override
	public boolean dispatchTouchEvent (MotionEvent ev) {
		return super.dispatchTouchEvent(ev);
	}
	


}