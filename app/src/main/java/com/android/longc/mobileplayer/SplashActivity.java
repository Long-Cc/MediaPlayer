package com.android.longc.mobileplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;

public class SplashActivity extends Activity {
	
	private Handler handler=new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		/**
		 * 两秒后进入主页面
		 */
		handler.postDelayed(new Runnable() {
			//两秒后才执行到这里
			//执行在主线程中
			@Override
			public void run() {
				startMainActivity();
			}
		}, 2000);
	}
	private boolean isStartMain=false;
	/**
     * 跳转到主页面，并且把当前页面关闭掉
     */
	private void startMainActivity() {
		if(!isStartMain){
			isStartMain=true;
			Intent intent=new Intent(this, MainActivity.class);
			startActivity(intent);
			finish();
		}
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		startMainActivity();
		return super.onTouchEvent(event);
	}
	@Override
	protected void onDestroy() {
		// 把所有的消息和回调移除
		handler.removeCallbacksAndMessages(null);
//		Log.e("TAG", "removeCallbacksAndMessages()");
		super.onDestroy();
	}
}
