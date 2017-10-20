package com.android.longc.mobileplayer.pager;


import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.android.longc.mobileplayer.base.BasePager;

/**
 * 网络音乐界面
 * @author longc
 *
 */
public class NetVideoPager extends BasePager {
	
	private TextView textView;

	public NetVideoPager(Context context) {
		super(context);
	}

	@Override
	public View initView() {
		textView=new TextView(context);
		textView.setTextColor(Color.RED);
		textView.setTextSize(30);
		textView.setGravity(Gravity.CENTER);
		return textView;
	}
	
	@Override
	public void initData() {
		super.initData();
		textView.setText("网络音乐界面");
	}

}
