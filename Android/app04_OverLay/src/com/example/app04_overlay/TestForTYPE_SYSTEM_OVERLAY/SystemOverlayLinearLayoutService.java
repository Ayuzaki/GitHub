package com.example.app04_overlay.TestForTYPE_SYSTEM_OVERLAY;

import com.example.app04_overlay.widget.SystemOverlayLinearLayout;

import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.os.IBinder;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DigitalClock;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class SystemOverlayLinearLayoutService extends Service implements OnClickListener, AnimationListener{
	
	View view;
    WindowManager wm;
    DigitalClock clock;
    ImageButton btn_open, btn_close;
    LinearLayout layout_clock_on;
    RelativeLayout layout_clock_off;
    public Typeface font;
    private Context mContext;
	Animation in_from_right, in_from_top, out_to_right, out_to_top;
	boolean open_flg = true;

	@Override
	public void onStart(Intent intent, int startId) {

        in_from_right    = AnimationUtils.loadAnimation(this, R.anim.in_from_right);
        out_to_right     = AnimationUtils.loadAnimation(this, R.anim.out_to_right);
        in_from_right.setAnimationListener(this);
        out_to_right.setAnimationListener(this);
		
		// View����C���t���[�^���쐬����
        LayoutInflater layoutInflater = LayoutInflater.from(this);

        // �d�ˍ��킹����View�̐ݒ���s��
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        	params.x = 0;
        	params.y = 0;
        	params.width   = WindowManager.LayoutParams.WRAP_CONTENT;  
        	params.height  = WindowManager.LayoutParams.WRAP_CONTENT; 
        	params.gravity = Gravity.TOP | Gravity.RIGHT;
        	params.type    = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
            params.flags   = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
            params.format  = PixelFormat.TRANSLUCENT;
         
        // WindowManager���擾����
        wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
         
        // ���C�A�E�g�t�@�C������d�ˍ��킹����View���쐬����
        view = layoutInflater.inflate(R.layout.overlay, null);
        
        clock = (DigitalClock)view.findViewById(R.id.digital_clock);
        

        layout_clock_on  = (LinearLayout)view.findViewById(R.id.layout_digital_clock_on);
        layout_clock_off = (RelativeLayout)view.findViewById(R.id.layout_digital_clock_off);
        layout_clock_off.setVisibility(View.GONE);
        
        btn_open = (ImageButton)view.findViewById(R.id.btn_open);
        btn_open.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO �����������ꂽ���\�b�h�E�X�^�u
				layout_clock_off.setVisibility(View.GONE);
				layout_clock_on.startAnimation(in_from_right);
				open_flg = true;
			}
        });
        
        //btn_close = (ImageButton)view.findViewById(R.id.btn_close);
        layout_clock_on.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO �����������ꂽ���\�b�h�E�X�^�u
				layout_clock_on.startAnimation(out_to_right);
				open_flg = false;
			}
        });

        //font�ύX
        font = Typeface.createFromAsset(getAssets(), getString(R.string.font));
        clock.setTextColor(Color.WHITE);
        clock.setTypeface(font);
 
        // View����ʏ�ɏd�ˍ��킹����
        wm.addView(view, params);   
    }
	
	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		
	}

 
    @Override
    public void onCreate() {
        super.onCreate();
    }
 
    @Override
    public void onDestroy() {
        super.onDestroy();
         
        // �T�[�r�X���j�������Ƃ��ɂ͏d�ˍ��킹���Ă���View���폜����
        wm.removeView(view);
    }
 
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }


	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		if (layout_clock_on.getVisibility() == View.VISIBLE && open_flg == false) {
			layout_clock_on.setVisibility(View.GONE);
		} else {
			layout_clock_on.setVisibility(View.VISIBLE);
		}
		
		if (layout_clock_off.getVisibility() == View.GONE && open_flg == false) {
			layout_clock_off.setVisibility(View.VISIBLE);
		} else {
			layout_clock_off.setVisibility(View.GONE);
		}
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		layout_clock_off.setVisibility(View.GONE);
		layout_clock_on.setVisibility(View.VISIBLE);
	}
}